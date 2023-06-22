package com.example.acronyms.retrofit

import com.example.acronyms.model.MeaningsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is ApiInterface, which provides Retrofit Client to call web API.
 */
interface ApiInterface {
    @GET("dictionary.py")
    suspend fun getMeaningsData(
        @Query("sf") sortForm: String
    ): Response<MeaningsDto>
}