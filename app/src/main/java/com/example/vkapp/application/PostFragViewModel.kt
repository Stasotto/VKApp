package com.example.vkapp.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.GetPostDetailsUseCase
import kotlinx.coroutines.launch

class PostFragViewModel(private val getPostDetailsUseCase: GetPostDetailsUseCase) : ViewModel() {


    private val _postDetails = MutableLiveData<Boolean>()
    val postDetails: LiveData<Boolean> get() = _postDetails

    fun loadPostDetails() {
        viewModelScope.launch {
            _postDetails.value = getPostDetailsUseCase.execute()

        }
    }
}