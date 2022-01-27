package com.example.birthdaysapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dob(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
):Serializable