package com.example.developershortcut.screen.fourscreen.quotes.data.repository.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuotesDbClient {

    private val retrofit = Retrofit
        .Builder()
        .baseUrl("https://my-json-server.typicode.com/carlossala/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: QuotesApiClient = retrofit.create(QuotesApiClient::class.java)

}