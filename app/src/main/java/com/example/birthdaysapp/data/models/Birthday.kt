package com.example.birthdaysapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Birthday(
    @SerializedName("dob")
    val dob: Dob,
    @SerializedName("name")
    val name: Name
):Serializable