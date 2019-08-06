package booksapp.burhanshakir.com.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book( val id: Int, val title: String, val isbn: String, val price: Int, val currencyCode: String,
            val author: String, val description: String) : Parcelable
{
    fun getPrice() : String
    {
        var symbol = ""
        when (currencyCode) {
            "USD" -> symbol = "$"
            "EUR" -> symbol = "€"
            else -> symbol = "£"
        }

        return symbol + price


    }

}
