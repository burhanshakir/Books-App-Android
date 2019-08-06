package booksapp.burhanshakir.com.details


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import booksapp.burhanshakir.com.R
import booksapp.burhanshakir.com.network.Book
import kotlinx.android.synthetic.main.fragment_book_details.*
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar


class BookDetailsFragment : Fragment(), BookDetailsView
{
    lateinit var presenter: BookDetailsPresenter
    var twoPaned : Boolean = false

    companion object
    {
        fun newInstance(bookId : Int, twoPaned : Boolean) : BookDetailsFragment
        {
            val fragment = BookDetailsFragment()
            val args = Bundle()
            args.putInt("id", bookId)
            args.putBoolean("twoPaned",twoPaned)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Retain this fragment across configuration changes.
        retainInstance = !twoPaned

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        twoPaned = arguments!!.getBoolean("twoPaned")

        if(!twoPaned)
            setUpViews()

        presenter = BookDetailsPresenterImpl(this)
        presenter.setView(this, arguments!!.getInt("id"))

    }

    // UI Functions

    private fun setUpViews()
    {
        // Setting up collapsing toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar_fragment as Toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = null

    }

    override fun getDetails(book: Book)
    {
        if(!twoPaned)
            toolbar_layout.title = book.title
        else
            tvName.text = book.title

        tvDescription.text = book.description
    }

    override fun onError(e: Throwable?)
    {
        error_layout.visibility = View.VISIBLE
    }

    override fun onLoadingStarted()
    {
        progress_bar.show()
    }

    override fun onLoadinFinished()
    {
        progress_bar.hide()
    }

}
