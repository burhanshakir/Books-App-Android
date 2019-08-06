package booksapp.burhanshakir.com.listing

import android.util.Log
import booksapp.burhanshakir.com.network.Book
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BooksListingPresenterImpl(private var view: BooksListingView?) : BooksListingPresenter
{
    lateinit var interactor : BooksListingInteractor
    override fun setView(view: BooksListingView)
    {
        this.view = view
        interactor = BooksListingInteractorImpl()
        getListOfBooks()

    }

    private fun getListOfBooks()
    {
        view?.loadingStarted()
        interactor.getListOfBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                { booksResponse -> onGetBooksSuccess(booksResponse)},
                { e -> onGetBooksFailure(e)}
            )
    }

    private fun onGetBooksFailure(e: Throwable?)
    {
        view?.loadingFinished()
        view?.onError(e)
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetBooksSuccess(booksResponse: List<Book>) {
        view?.loadingFinished()
        view?.showBooks(booksResponse)
    }

}