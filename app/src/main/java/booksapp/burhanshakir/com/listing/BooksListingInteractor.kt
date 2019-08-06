package booksapp.burhanshakir.com.listing

import booksapp.burhanshakir.com.network.Book
import io.reactivex.Observable

interface BooksListingInteractor {
    fun getListOfBooks() : Observable<List<Book>>
}