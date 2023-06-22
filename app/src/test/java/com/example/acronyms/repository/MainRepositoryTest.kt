package com.example.acronyms.repository

import com.example.acronyms.model.MeaningsDto
import com.example.acronyms.retrofit.ApiInterface
import com.example.acronyms.NetworkState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class MainRepositoryTest {

    private lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiInterface: ApiInterface

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainRepository = MainRepository(apiInterface)
    }

    @Test
    fun getMeanings() {
        runBlocking {
            val meaningsData = MeaningsDto()
            Mockito.`when`(apiInterface.getMeaningsData("sf")).thenReturn(
                Response.success(meaningsData)
            )

            val response = mainRepository.getMeaningsData("sf")
            assertEquals(NetworkState.Success(meaningsData), response)
        }
    }
}