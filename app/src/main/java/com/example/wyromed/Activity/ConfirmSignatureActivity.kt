package com.example.wyromed.Activity

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wyromed.Activity.Interface.UpdateStatusBookingInterface
import com.example.wyromed.Activity.Presenter.UpdateStatusBookingPresenter
import com.example.wyromed.R
import com.github.gcacace.signaturepad.views.SignaturePad
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.internal.platform.Platform
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.*


class ConfirmSignatureActivity : BaseActivity(), UpdateStatusBookingInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val ID = "id"
        val CONFIRM = "confirm"
    }

    private var signaturePad: SignaturePad? = null
    private var btnSave: FloatingActionButton? = null
    private var btnClear: Button? = null
    var id: Int = 0
    var back: ImageButton? = null
    var confirm: Boolean = false
    var uri: Uri? = null
    var stringUrl: String? = null
    var sign: Int? = 0
    var urlPhoto: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_signature)

        //INIT VIEW
        signaturePad = findViewById(R.id.signature_pad)
        btnSave = findViewById(R.id.btn_save_signature)
        btnClear = findViewById(R.id.btn_clear_signature)
        back = findViewById(R.id.ic_back)

        id = intent.getIntExtra("id", 0)
        confirm = intent.getBooleanExtra("confirm", false)
        if(intent.hasExtra("sign")) {
            sign = intent.getIntExtra("sign", 0)
        }
        signaturePad?.setOnSignedListener(object : SignaturePad.OnSignedListener {
            override fun onStartSigning() {
            }

            override fun onSigned() {
                btnSave?.isEnabled = true
                btnClear?.isEnabled = true
            }

            override fun onClear() {
                btnSave?.isEnabled = false
                btnClear?.isEnabled = false
            }
        })

        verifyStoragePermissions(this)
        initActionButton()
    }

    private fun initActionButton(){
        back?.onClick { finish() }
        btnSave?.onClick {
            val signatureBitmap = signaturePad!!.signatureBitmap
            btnSave?.isEnabled = false
            if (addJpgSignatureToGallery(signatureBitmap)) {
                when(sign) {
                    1-> {
                        startActivity<AcceptSignatureActivity>(
                            AcceptSignatureActivity.TAGS.URLDOCTOR to urlPhoto
                        )
                        finish()
                    }
                    2-> {
                        startActivity<AcceptSignatureActivity>(
                            AcceptSignatureActivity.TAGS.URLNURSE to urlPhoto
                        )
                        finish()
                    }
                    3-> {
                        startActivity<AcceptSignatureActivity>(
                            AcceptSignatureActivity.TAGS.URLSALES to urlPhoto
                        )
                        finish()
                    }
                    else -> {
                        updateStatusBooking()
                        finish()
                    }

                }
            } else {
                btnSave?.isEnabled = true
                Toast.makeText(
                    this@ConfirmSignatureActivity,
                    "Unable to store the signature",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnClear?.onClick { signaturePad!!.clear() }
    }

    private fun updateStatusBooking(){
        btnSave?.isEnabled = true

        val status: String = "2"
        UpdateStatusBookingPresenter(this).updateStatusBooking(
            this,
            id.toString(),
            status
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
                if (grantResults.size <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this@ConfirmSignatureActivity,
                        "Cannot write images to external storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    @Throws(IOException::class)
    fun saveBitmapToJPG(bitmap: Bitmap, photo: File?) {
        val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        val stream = FileOutputStream(photo)
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        stream.close()
    }

    fun addJpgSignatureToGallery(signature: Bitmap): Boolean {
        var result = false
        try {
            val permission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            if (permission != PackageManager.PERMISSION_GRANTED) {
                toast("Try again to request the permission")
            }else {
                val resolver = this.contentResolver

                val contentValues = ContentValues().apply {
                    put(
                        Images.Media.TITLE, String.format(
                            "Nurse_Confirm_Signature_%d",
                            System.currentTimeMillis()
                        )
                    )
                    put(
                        Images.Media.DISPLAY_NAME, String.format(
                            "Nurse_Confirm_Signature_%d",
                            System.currentTimeMillis()
                        )
                    )
                    put(Images.Media.DESCRIPTION, "Nurse Confirmation Signature")
                    put(Images.Media.MIME_TYPE, "image/jpeg")
                    // Add the date meta data to ensure the image is added at the front of the gallery
                    // Add the date meta data to ensure the image is added at the front of the gallery
                    put(Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
                    put(Images.Media.DATE_TAKEN, System.currentTimeMillis())
                    put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/Wyromed/Signature")
                }

                try {
                     uri = resolver.insert(
                         MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                         contentValues
                     )
                    if (signature != null) {
                        val imageOut: OutputStream = resolver.openOutputStream(uri!!)!!
                        try {
                            signature.compress(Bitmap.CompressFormat.JPEG, 80, imageOut)
                            if(Build.VERSION.SDK_INT == 30){
                                var titlePic: String = ""
                                when(sign){
                                    1 -> {
                                        titlePic = String.format(
                                            "Doctor_Signature_%d.jpg",
                                            System.currentTimeMillis()
                                        )
                                    }

                                    2 -> {
                                        titlePic = String.format(
                                            "Nurse_Chief_Signature_%d.jpg",
                                            System.currentTimeMillis()
                                        )
                                    }

                                    3 -> {
                                        titlePic = String.format(
                                            "Sales_Signature_%d.jpg",
                                            System.currentTimeMillis()
                                        )
                                    }
                                    else -> {
                                        titlePic = String.format(
                                            "Nurse_Confirm_Signature_%d.jpg",
                                            System.currentTimeMillis()
                                        )
                                    }
                                }

                               MediaStore.Images.Media.insertImage(
                                   contentResolver,
                                    signature,
                                    titlePic,
                                    "Signature"
                                )
                            }else {
                                var titlePic: String = ""
                                when(sign){
                                    1 -> {
                                        titlePic = String.format(
                                            "Doctor_Signature_%d.jpg",
                                            System.currentTimeMillis()
                                        )
                                    }

                                    2 -> {
                                        titlePic = String.format(
                                            "Nurse_Chief_Signature_%d.jpg",
                                            System.currentTimeMillis()
                                        )
                                    }

                                    3 -> {
                                        titlePic = String.format(
                                            "Sales_Signature_%d.jpg",
                                            System.currentTimeMillis()
                                        )
                                    }
                                    else -> {
                                        titlePic = String.format(
                                            "Nurse_Confirm_Signature_%d.jpg",
                                            System.currentTimeMillis()
                                        )
                                    }
                                }

                                val photo: File = File(
                                    getAlbumStorageDir("Wyromed/Signature"),
                                    titlePic
                                )

                                urlPhoto = Environment.DIRECTORY_PICTURES + "Wyromed/Signature/" + titlePic

                                saveBitmapToJPG(signature, photo)
                                scanMediaFile(photo)
                            }
                        } finally {
                            imageOut.close()
                            result = true
                        }
                        val id = ContentUris.parseId(uri!!)
                        // Wait until MINI_KIND thumbnail is generated.
                        val miniThumb = Images.Thumbnails.getThumbnail(
                            resolver,
                            id,
                            Images.Thumbnails.MINI_KIND,
                            null
                        )
                        // This is for backward compatibility.
                        storeThumbnail(
                            resolver,
                            miniThumb,
                            id,
                            50f,
                            50f,
                            Images.Thumbnails.MICRO_KIND
                        )
                    } else {
                        resolver.delete(uri!!, null, null)
                        uri = null
                    }
                } catch (e: Exception) {
                    if (uri != null) {
                        resolver.delete(uri!!, null, null)
                        uri = null
                    }
                }

                if (uri != null) {
                    stringUrl = uri.toString()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    private fun storeThumbnail(
        cr: ContentResolver,
        source: Bitmap,
        id: Long,
        width: Float,
        height: Float,
        kind: Int
    ): Bitmap? {

        // create the matrix to scale it
        val matrix = Matrix()
        val scaleX = width / source.width
        val scaleY = height / source.height
        matrix.setScale(scaleX, scaleY)
        val thumb = Bitmap.createBitmap(
            source, 0, 0,
            source.width,
            source.height, matrix,
            true
        )
        val values = ContentValues(4)
        values.put(Images.Thumbnails.KIND, kind)
        values.put(Images.Thumbnails.IMAGE_ID, id.toInt())
        values.put(Images.Thumbnails.HEIGHT, thumb.height)
        values.put(Images.Thumbnails.WIDTH, thumb.width)
        val url = cr.insert(Images.Thumbnails.EXTERNAL_CONTENT_URI, values)
        return try {
            val thumbOut = cr.openOutputStream(url!!)
            thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut)
            thumbOut!!.close()
            thumb
        } catch (ex: FileNotFoundException) {
            null
        } catch (ex: IOException) {
            null
        }
    }

    fun getAlbumStorageDir(albumName: String?): File? {
        //Get the directory for the user's public picture directory.
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            albumName
        )
        if (!file.mkdirs()) {
            file.mkdirs()
        }
        return file
    }

        private fun scanMediaFile(photo: File) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val contentUri = Uri.fromFile(photo)
        mediaScanIntent.data = contentUri
        this@ConfirmSignatureActivity.sendBroadcast(mediaScanIntent)
    }

    companion object {
        private const val REQUEST_EXTERNAL_STORAGE = 1
        private val PERMISSIONS_STORAGE = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        fun verifyStoragePermissions(activity: Activity?) {
            //Check if we have write permission
            val permission = ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                //We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                )
            }
        }
    }

    override fun onSuccessUpdateStatusBooking(msg: String?) {
        confirm = true

        startActivity<HandoverActivity>(
            HandoverActivity.TAGS.TOKENTYPE to user?.token_type,
            HandoverActivity.TAGS.TOKEN to user?.token,
            HandoverActivity.TAGS.MESSAGE to msg,
            HandoverActivity.TAGS.ID to id,
            HandoverActivity.TAGS.CONFIRM to confirm
        )
        finish()
    }

    override fun onErrorUpdateStatusBooking(msg: String?) {
        toast("Failed to update status booking")
    }
}