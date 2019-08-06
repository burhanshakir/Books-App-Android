package booksapp.burhanshakir.com.listing


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import booksapp.burhanshakir.com.R
import booksapp.burhanshakir.com.network.Book
import kotlinx.android.synthetic.main.fragment_books_listing.*

class BooksListingFragment : Fragment(), BooksListingView
{
    lateinit var presenter: BooksListingPresenter
    lateinit var activityCallback: ActivityCallback

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Retain this fragment across configuration changes.
        retainInstance = true

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books_listing, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activityCallback = context as ActivityCallback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()

        presenter = BooksListingPresenterImpl(this)
        presenter.setView(this)

    }

    // UI Functions
    private fun setUpViews()
    {
        val layoutManager = LinearLayoutManager(context)
        rv_book_list.layoutManager = layoutManager
        rv_book_list.adapter = BooksAdapter(this)
    }

    // Implemented methods from view
    override fun showBooks(books: List<Book>) {
        (rv_book_list.adapter as BooksAdapter).addBooks(books)
    }

    override fun onError(e: Throwable?) {
        error_msg_layout.visibility = View.VISIBLE
    }

    override fun loadingStarted() {
        progress_circular.show()
    }

    override fun loadingFinished() {
        progress_circular.hide()
    }

    override fun onBookClicked(book : Book) {
        activityCallback.onBookClicked(book)
    }

    interface ActivityCallback
    {
        fun onBookClicked(book : Book)
    }

}
