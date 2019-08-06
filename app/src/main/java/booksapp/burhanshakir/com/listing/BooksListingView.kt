package booksapp.burhanshakir.com.listing

import booksapp.burhanshakir.com.network.Book

interface BooksListingView
{
    fun showBooks(books : List<Book>)
    fun onError(e : Throwable?)
    fun loadingStarted()
    fun loadingFinished()
    fun onBookClicked(book : Book)
}