package com.code.myapplication.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    @GET("viewed/30.json")
    suspend fun getMostView(@Query("api-key") apiKey: String = "NdhRJJXseHeiDAsAf5LeTEod2nekXQpS"): Response<NewYorkTimesMostViewResponse>
}
