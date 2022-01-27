package com.example.birthdaysapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.birthdaysapp.data.models.Birthday
import com.example.birthdaysapp.data.repositories.Repository
import com.example.birthdaysapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    val birthdaysLiveData = flow {
        emit(ApiResult.Loading(null, isLoading = true))   // 1. Loading State
        val response = repository.getBirthDays()
        if (response.isSuccessful) {
            val sortList = sortList(response.body()!!.results)
            emit(ApiResult.Success(sortList))   // 2. Success State
        } else {
            val errorMsg = response.errorBody()?.string()
            response.errorBody()
                ?.close()  // remember to close it after getting the stream of error body
            emit(ApiResult.Error(errorMsg ?: "Some error occured"))  // 3. Error State
        }
    }
        .asLiveData()

    private fun sortList(results: List<Birthday>) :  List<Birthday>{
        Collections.sort(results , Comparator { o1, o2 ->
             o1.dob.date.compareTo(o2.dob.date)
        })
        return  results
    }

}