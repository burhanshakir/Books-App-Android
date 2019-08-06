package booksapp.burhanshakir.com.listing

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import booksapp.burhanshakir.com.R
import booksapp.burhanshakir.com.network.Book


class BooksAdapter(private var booksListingView: BooksListingView) : RecyclerView.Adapter<BooksAdapter.ViewHolder>()
{
    private var books : List<Book> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val root = (LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false))
        return ViewHolder(root)
    }

    override fun getItemCount(): Int
    {
       return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.itemView.setOnClickListener {

            booksListingView.onBookClicked(books[position])
        }
        holder.bind(books[position])
    }



    fun addBooks(books: List<Book>?) {
        if (books != null) {
            this.books = books
            notifyDataSetChanged()
        }
    }


    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root), View.OnClickListener
    {
        val tvBookName : TextView = root.findViewById(R.id.tvBookName)
        val tvAuthor : TextView = root.findViewById(R.id.tvAuthor)
        val tvPrice : TextView = root.findViewById(R.id.tvPrice)

        fun bind(book : Book)
        {
            tvBookName.text = book.title
            tvAuthor.text = book.author
            tvPrice.text = book.getPrice()
        }

        override fun onClick(view: View?)
        {

        }
    }
}
