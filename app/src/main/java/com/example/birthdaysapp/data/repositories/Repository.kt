package com.example.birthdaysapp.data.repositories

import com.example.birthdaysapp.data.network.Api
import javax.inject.Inject

class Repository @Inject constructor(private val api : Api) {
    suspend fun getBirthDays() = api.getBirthdays()
}