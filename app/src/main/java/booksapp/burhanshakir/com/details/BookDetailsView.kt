package booksapp.burhanshakir.com.details

import booksapp.burhanshakir.com.network.Book

interface BookDetailsView
{
    fun getDetails(book : Book)
    fun onError(e : Throwable?)
    fun onLoadingStarted()
    fun onLoadinFinished()

}