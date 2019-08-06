package booksapp.burhanshakir.com.details

import booksapp.burhanshakir.com.network.Book
import booksapp.burhanshakir.com.network.BooksApi
import booksapp.burhanshakir.com.network.RetrofitClient
import io.reactivex.Observable

class BookDetailsInteractorImpl : BookDetailsInteractor
{
    override fun getBookDetails(bookId : Int): Observable<Book>
    {
        val api : BooksApi = RetrofitClient.getBooksApi()
        return api.getBookDetails(bookId)
    }
}