package com.example.acronyms.repository

import com.example.acronyms.model.MeaningsDto
import com.example.acronyms.NetworkState
import com.example.acronyms.retrofit.ApiInterface
import javax.inject.Inject


/**
 * This is Repository class used for get data from web API.
 */
class MainRepository @Inject constructor(private val retrofitClient: ApiInterface){

    suspend fun getMeaningsData(sortForm: String): NetworkState<MeaningsDto> {
        val response = retrofitClient.getMeaningsData(sortForm)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}