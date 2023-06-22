package com.example.acronyms.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronyms.model.MeaningsDto
import com.example.acronyms.repository.MainRepository
import com.example.acronyms.util.ValidationUtil
import com.example.acronyms.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

/**
 *  This is MainViewModel class, which has complete business logic for fetching full forms of acronyms.
 */
@HiltViewModel
class MainViewModel @Inject constructor (private  val mainRepository: MainRepository):ViewModel(){
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
     val largeFormList = MutableLiveData<MeaningsDto>()

    fun getMeaningsData(sortForm: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = mainRepository.getMeaningsData(sortForm)) {
                    is NetworkState.Success -> {
                        getLargeFormsList(response.data)
                    }
                    is NetworkState.Error -> {
                        onError(response.toString())
                    }
                }
            } catch (ex: UnknownHostException) {
               onError(ValidationUtil.NETWORK_ERROR_MESSAGE)
            } catch (ex: java.lang.Exception) {
                onError(ex.stackTraceToString())
            }
        }
    }
    private fun getLargeFormsList(meaningsDto: MeaningsDto) {
        if ((meaningsDto.isNotEmpty()) && (meaningsDto[0].lfs.isNotEmpty())) {
            largeFormList.postValue(meaningsDto)
        } else {
            onError(ValidationUtil.RESPONSE_ERROR_MESSAGE)
        }
    }
    private fun onError(message: String) {
        _errorMessage.postValue(message)
    }
}