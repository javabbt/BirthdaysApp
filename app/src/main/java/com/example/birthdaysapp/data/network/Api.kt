package com.example.birthdaysapp.data.network

import com.example.birthdaysapp.data.models.Birthday
import com.example.birthdaysapp.data.models.BirthdayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/api")
    suspend fun getBirthdays(
        @Query("results")results:Int=7,
        @Query("seed")seed:String="chalkboard",
        @Query("inc")inc:String="name,dob"
    ) : Response<BirthdayResponse>
}