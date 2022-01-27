package com.example.birthdaysapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.birthdaysapp.data.repositories.Repository
import com.example.birthdaysapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    val birthdaysLiveData = flow {
        emit(ApiResult.Loading(null , isLoading = true))   // 1. Loading State
        val response = repository.getBirthDays()
        if (response.isSuccessful) {
            emit(ApiResult.Success(response.body()))   // 2. Success State
        } else {
            val errorMsg = response.errorBody()?.string()
            response.errorBody()
                ?.close()  // remember to close it after getting the stream of error body
            emit(ApiResult.Error(errorMsg ?: "Some error occured"))  // 3. Error State
        }
    }.asLiveData()

}