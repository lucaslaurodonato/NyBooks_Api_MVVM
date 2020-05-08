package com.lucasdonato.nybooks.data

import com.lucasdonato.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "AJ0BZ0gmZa0t3JTMQBApKSRnmGyvD2j0",
        @Query("list") list: String = "paperback-nonfiction"
    ): Call<BookBodyResponse>
}