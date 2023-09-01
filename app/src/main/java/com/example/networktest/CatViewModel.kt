package com.example.networktest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CatViewModel: ViewModel() {

    val result = MutableLiveData<CatData>()

    fun networkCall(){
        viewModelScope.launch(IO){
            var response = CatRepository.networkCall()
            if (response?.isSuccessful == true){
                result.postValue(response.body())
            } else {
                Log.e("NETWORK ERROR","Couldn't achieve network call")
            }
        }
    }

}