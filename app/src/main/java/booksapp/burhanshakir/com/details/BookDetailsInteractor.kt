package booksapp.burhanshakir.com.details

import booksapp.burhanshakir.com.network.Book
import io.reactivex.Observable

interface BookDetailsInteractor
{
    fun getBookDetails(bookId : Int) : Observable<Book>
}