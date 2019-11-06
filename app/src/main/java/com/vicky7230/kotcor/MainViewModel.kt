package com.vicky7230.kotcor

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vicky7230.kotcor.api.RetrofitApi
import com.vicky7230.kotcor.api.RetrofitResult
import com.vicky7230.kotcor.db.AppDatabase
import com.vicky7230.kotcor.event.Event_
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : BaseViewModel(application) {

    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    val db = AppDatabase.getInstance(application)

    var posts = db?.postDao()?.loadAllPosts()

    fun getPosts() {

        loading.value = true

        viewModelScope.launch {
            val response = safeApiCall(
                call = { RetrofitApi.getApiService().getPosts() }
            )

            when (response) {
                is RetrofitResult.Success -> {
                    db?.postDao()?.insertPost(response.data)
                    //posts?.value = response.data
                }
                is RetrofitResult.Error -> {
                    error.value = response.exception.message
                }
            }

            loading.value = false

            triggerEvent.value = Event_("LOG_OUT")

        }
    }
}