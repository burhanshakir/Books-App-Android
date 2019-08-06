package booksapp.burhanshakir.com.details

import android.util.Log
import booksapp.burhanshakir.com.network.Book
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BookDetailsPresenterImpl(private var view: BookDetailsView?) : BookDetailsPresenter
{
    lateinit var interactor : BookDetailsInteractor
    override fun setView(view: BookDetailsView, bookId : Int)
    {
        this.view = view
        interactor = BookDetailsInteractorImpl()
        getBookDetails(bookId)

    }

    private fun getBookDetails(bookId : Int)
    {
        view?.onLoadingStarted()
        interactor.getBookDetails(bookId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                { booksResponse -> onGetBookSuccess(booksResponse)},
                { e -> onGetBookFailure(e)}
            )
    }

    private fun onGetBookFailure(e: Throwable?)
    {
        view?.onLoadinFinished()
        view?.onError(e)
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetBookSuccess(booksResponse: Book) {
        view?.onLoadinFinished()
        view?.getDetails(booksResponse)
    }

}