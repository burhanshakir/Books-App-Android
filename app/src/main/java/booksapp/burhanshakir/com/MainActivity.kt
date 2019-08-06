package booksapp.burhanshakir.com

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import booksapp.burhanshakir.com.details.BookDetailsFragment
import booksapp.burhanshakir.com.listing.BooksListingFragment
import booksapp.burhanshakir.com.network.Book
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BooksListingFragment.ActivityCallback
{
    // variable to check if the layout is single or double paned
    private var twoPaned : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViews()

        if(savedInstanceState == null)
            setListingFragment()
    }

    private fun setViews()
    {

        if(details_container == null)
            twoPaned = false

        setSupportActionBar(toolbar_layout as Toolbar)

    }

    private fun setListingFragment()
    {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, BooksListingFragment(), BooksListingFragment::class.simpleName)
            .commit()
    }

    private fun setDetailsFragment(book: Book)
    {
        var fragmentLayout : Int = R.id.container

        if(twoPaned)
            fragmentLayout = R.id.details_container


        supportFragmentManager
            .beginTransaction()
            .replace(fragmentLayout, BookDetailsFragment.newInstance(book.id, twoPaned), BookDetailsFragment::class.simpleName)
            .addToBackStack("Listing Fragment")
            .commit()

    }

    // Callback from Listing fragment
    override fun onBookClicked(book: Book)
    {
        setDetailsFragment(book)
    }

    // Handling back press from details fragment
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

}
