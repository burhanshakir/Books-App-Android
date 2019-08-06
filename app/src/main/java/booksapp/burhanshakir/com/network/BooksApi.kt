package booksapp.burhanshakir.com.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi
{
    @GET("/books")
    fun getBooks(): Observable<List<Book>>

    @GET("/book/{id}")
    fun getBookDetails(@Path("id") bookId : Int): Observable<Book>
}