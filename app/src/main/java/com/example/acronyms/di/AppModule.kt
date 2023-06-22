package com.example.acronyms.di

import com.example.acronyms.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
/**
 * This is DI  module class provides instance for retrofit.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesMeaningsApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}