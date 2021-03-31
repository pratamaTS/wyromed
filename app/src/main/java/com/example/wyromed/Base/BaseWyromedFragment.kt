package com.example.wyromed.Base

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.wyromed.R

abstract class BaseWyromedFragment<P: BasePresenter> : Fragment(), BaseView<P> {
    @get:LayoutRes
    protected abstract val layoutId: Int

    @get:IdRes
    protected open var contentContainerId: Int = -1

    @get:IdRes
    protected open var emptyPlaceholderId: Int = -1

    abstract val TAG: String

    override fun init() { }

    private var loadingContent: View? = null
    private var contentContainer: View? = null
    private var emptyPlaceHolder: View? = null

    private lateinit var loadingDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = layoutInflater.inflate(layoutId, container, false)

        loadingContent = v.findViewById(R.id.container_loading_content)
        contentContainer = v.findViewById(contentContainerId)
        emptyPlaceHolder = v.findViewById(emptyPlaceholderId)

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(requireContext())
                .setView(R.layout.layout_loading)
                .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)

        }

        init()
        setupWidgets(v)
        setupAdapter()

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.start()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    protected open fun setupAdapter() { }

    protected open fun setupWidgets(v: View) { }

    override fun onPause() {
        if(loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
        super.onPause()
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

    override fun showLoadingContent(isLoading: Boolean) {
        if(isLoading) {
            contentContainer?.visibility = View.GONE
            loadingContent?.visibility = View.VISIBLE
        } else {
            loadingContent?.visibility = View.GONE
            contentContainer?.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String?, duration: BaseView.ToastDuration) {
        Toast.makeText(context, message, if(duration == BaseView.ToastDuration.SHORT) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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

    override fun pressBack() {
        activity?.onBackPressed()
    }

    override fun closeView() {
        activity?.finish()
    }
}