package com.vicky7230.kotcor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky7230.kotcor.api.RetrofitResult
import com.vicky7230.kotcor.event.Event_
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val triggerEvent = MutableLiveData<Event_<String>>()

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>
    ): RetrofitResult<T>? {
        try {
            val response = call.invoke()

            if (response.isSuccessful) {
                /** Called for [200, 300) responses. */
                return RetrofitResult.Success(response.body()!!)

            } else {

                //Log the error body
                Timber.e(response.errorBody().toString())

                when (response.code()) {
                    401 -> {
                        return RetrofitResult.Error(IOException("HTTP ${response.code()} : Unauthorized"))
                    }

                    in 400..499 -> {
                        return RetrofitResult.Error(IOException("HTTP ${response.code()} : Client Error"))
                    }

                    in 500..599 -> {
                        return RetrofitResult.Error(IOException("HTTP ${response.code()} : Server Error"))
                    }

                    else -> {
                        return RetrofitResult.Error(IOException("HTTP ${response.code()} : Something went wrong"))
                    }
                }
            }
        } catch (e: Exception) {
            //Log exception
            Timber.e(e)
            return RetrofitResult.Error(IOException(e))
        }
    }
}