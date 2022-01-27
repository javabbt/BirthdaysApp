package com.example.birthdaysapp.data.network

import com.example.birthdaysapp.data.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/api")
    suspend fun getBirthdays(
        @Query("results")results:Int=10,
        @Query("seed")seed:String="chalkboard",
        @Query("inc")inc:String="name,dob"
    ) : List<ApiResponse>
}