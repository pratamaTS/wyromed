package com.example.wyromed.Base

import com.example.wyromed.Data.Model.BasePagingResp
import com.example.wyromed.Data.Model.BaseResp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.ConnectException
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
abstract class BaseWyromedPresenter<V: BaseView<*>> : BasePresenter {

    protected var view: V? = null
    protected var cd: CompositeDisposable? = null

    override fun start() { }

    override fun attachView(mView: Any?) {
        if(mView != null) {
            this.view = mView as V
        }
        if(cd == null) cd = CompositeDisposable()
    }

    override fun detachView() {
        this.view = null
    }

    override fun cleanUp() {
        cd?.dispose()
    }

    override fun onBackPressed() {
        view?.pressBack()
    }

    protected inline fun <reified D> safeCall(obs: Single<BaseResp<D>>, listener: Listener<D>, requestConfiguration: RequestConfiguration = RequestConfiguration()) {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                cd?.add(obs
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<BaseResp<D>>() {
                        override fun onError(e: Throwable?) {
                            if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                            if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)
                            if(requestConfiguration.showErrorMessage) view?.showErrorMessage(if(e is ConnectException) "tidak dapat terhubung ke server" else "error")

                            listener.onFailed(e?.message)
                        }

                        override fun onSuccess(t: BaseResp<D>?) {
                            if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                            if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)

                            listener.onSuccess(t?.data)
                        }
                    }))
            }
    }

    protected inline fun <reified D> safeCallPayment(obs: Single<BaseResp<D>>, listener: Listener<D>, requestConfiguration: RequestConfiguration = RequestConfiguration()) {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    cd?.add(obs
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<BaseResp<D>>() {
                                override fun onError(e: Throwable?) {
                                    if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                                    if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)
                                    if(requestConfiguration.showErrorMessage) view?.showErrorMessage(if(e is ConnectException) "tidak dapat terhubung ke server" else "your payment is different with the invoice amount")

                                    listener.onFailed(e?.message)
                                }

                                override fun onSuccess(t: BaseResp<D>?) {
                                    if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                                    if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)

                                    listener.onSuccess(t?.data)
                                }
                            }))
                }
    }

    protected inline fun <reified D> safeCallPaging(obs: Single<BasePagingResp<D>>, listener: Listener<List<D>>, requestConfiguration: RequestConfiguration = RequestConfiguration()) {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                cd?.add(obs
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<BasePagingResp<D>>() {
                        override fun onError(e: Throwable?) {
                            if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                            if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)
                            if(requestConfiguration.showErrorMessage) view?.showErrorMessage(if(e is ConnectException) "tidak dapat terhubung ke server" else "error")

                            listener.onFailed(e?.message)
                        }

                        override fun onSuccess(t: BasePagingResp<D>?) {
                            if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                            if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)

                            listener.onSuccess(t?.data)
                        }
                    })
                )
            }

    }

    interface Listener<T> {

        fun onSuccess(data: T?)
        fun onFailed(message: String?) { }

    }

    data class RequestConfiguration (
        val showErrorMessage: Boolean = true,
        val updateLoadingIndicator: Boolean = true,
        val updateLoadingContentIndicator: Boolean = true
    )

}