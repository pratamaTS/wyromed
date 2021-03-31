package com.example.wyromed.Base

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.wyromed.Data.Connection.SessionManager
import com.example.wyromed.R

abstract class BaseWyromedActivity<P: BasePresenter> : AppCompatActivity(), BaseView<P> {

    @get:LayoutRes
    protected abstract val layoutId: Int

    @get:IdRes
    protected open var contentContainerId: Int = -1

    @get:IdRes
    protected open var emptyPlaceholderId: Int = -1

    @get:IdRes
    protected open var btnBackId: Int = -1

    protected val sessionManager by lazy {
        SessionManager(this)
    }

    private var btnBack: View? = null
    private var loadingContent: View? = null
    private var contentContainer: View? = null
    private var emptyPlaceHolder: View? = null

    private lateinit var loadingDialog: AlertDialog

    protected open fun setupAdapter() { }

    protected open fun setupWidgets() {
        btnBack?.setOnClickListener { presenter.onBackPressed() }
    }

    override fun init() { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        btnBack = findViewById(btnBackId)
        loadingContent = findViewById(R.id.container_loading_content)
        contentContainer = findViewById(contentContainerId)
        emptyPlaceHolder = findViewById(emptyPlaceholderId)

        init()
        setupWidgets()
        setupAdapter()

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                .setView(R.layout.layout_loading)
                .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)
        }

        presenter.attachView(this)
        presenter.start()
    }

    override fun onDestroy() {
        presenter.cleanUp()
        presenter.detachView()
        super.onDestroy()
    }

    override fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            if(lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                loadingDialog.show()
            }
        } else {
            if(lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                if(loadingDialog.isShowing) {
                    loadingDialog.dismiss()
                }
            }
        }
    }

    override fun onPause() {
        if(loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
        super.onPause()
    }

    override fun showLoadingContent(isLoading: Boolean) {
        if(isLoading) {
            contentContainer?.visibility = View.GONE
            loadingContent?.visibility = View.VISIBLE
        } else {
            loadingContent?.visibility = View.GONE
            contentContainer?.visibility = View.VISIBLE
        }
    }

    override fun showEmptyPlaceHolder(isEmpty: Boolean) {
        if(isEmpty) {
            contentContainer?.visibility = View.GONE
            emptyPlaceHolder?.visibility = View.VISIBLE
        } else {
            emptyPlaceHolder?.visibility = View.GONE
            contentContainer?.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?, duration: BaseView.ToastDuration) {
        Toast.makeText(this, message, if(duration == BaseView.ToastDuration.SHORT) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun pressBack() {
        finish()
    }

    override fun closeView() {
        finish()
    }
}