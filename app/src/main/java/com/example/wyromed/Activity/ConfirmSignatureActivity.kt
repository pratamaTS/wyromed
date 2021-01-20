package com.example.wyromed.Activity

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_signature)

        //INIT VIEW
        signaturePad = findViewById(R.id.signature_pad)
        btnSave = findViewById(R.id.btn_save_signature)
        btnClear = findViewById(R.id.btn_clear_signature)
        back = findViewById(R.id.ic_back)
        user?.token_type = intent.getStringExtra("token_type")
        user?.token = intent.getStringExtra("token")
        id = intent.getIntExtra("id", 0)
        confirm = intent.getBooleanExtra("confirm", false)

        signaturePad?.setOnSignedListener(object : SignaturePad.OnSignedListener {
            override fun onStartSigning() {
                Toast.makeText(this@ConfirmSignatureActivity, "OnStartSigning", Toast.LENGTH_SHORT)
                    .show()
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
            if (addJpgSignatureToGallery(signatureBitmap)) {
                updateStatusBooking()
            } else {
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
        val status: String = "2"
        UpdateStatusBookingPresenter(this).updateStatusBooking(
            user?.token_type,
            user?.token,
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
            if (Platform.isAndroid()) {
                val permission = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )

                if (permission != PackageManager.PERMISSION_GRANTED) {
                    toast("Try again to request the permission")
                }else {
                    val resolver = this.contentResolver

                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/Wyromed")
                    }

                    val uri = resolver.insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        contentValues
                    )

                    resolver.openOutputStream(uri!!).use {
                        MediaStore.Images.Media.insertImage(
                            contentResolver,
                            signature,
                            String.format(
                                "Nurse_Confirm_Signature_%d",
                                System.currentTimeMillis()
                            ),
                            "Nurse Confirmation Signature"
                        )
                        result = true
                    }
                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
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
        val intent = Intent(applicationContext, HandoverActivity::class.java)
        Intent.FLAG_ACTIVITY_CLEAR_TOP

        startActivity<HandoverActivity>(
            HandoverActivity.TAGS.TOKENTYPE to user?.token_type,
            HandoverActivity.TAGS.TOKEN to user?.token,
            HandoverActivity.TAGS.MESSAGE to msg,
            HandoverActivity.TAGS.ID to id,
            HandoverActivity.TAGS.CONFIRM to confirm
        )
    }

    override fun onErrorUpdateStatusBooking(msg: String?) {
        toast("Failed to update status booking")
    }
}