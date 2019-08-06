package booksapp.burhanshakir.com.listing

import booksapp.burhanshakir.com.network.Book
import booksapp.burhanshakir.com.network.BooksApi
import booksapp.burhanshakir.com.network.RetrofitClient
import io.reactivex.Observable

class BooksListingInteractorImpl : BooksListingInteractor
{
    override fun getListOfBooks(): Observable<List<Book>>
    {
        val api : BooksApi = RetrofitClient.getBooksApi()
        return api.getBooks()
    }
}