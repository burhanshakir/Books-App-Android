package booksapp.burhanshakir.com.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient
{
    companion object{

        private fun getBaseUrl(): String
        {
            return "http://tpbookserver.herokuapp.com/"
        }

        fun getBooksApi() : BooksApi
        {
            val okhttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(getBaseUrl())
                .client(okhttpClient)
                .build()

            return retrofit.create(BooksApi::class.java)
        }

    }


}