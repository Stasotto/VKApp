package com.example.vkapp.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapp.domain.usecase.GetNewsFeedUseCase
import kotlinx.coroutines.launch

class NewsFragViewModel(private val getNewsFeedUseCase: GetNewsFeedUseCase) : ViewModel() {

    private val _newsForRecycler = MutableLiveData<Boolean>()
    val newsForRecycler: LiveData<Boolean> get() = _newsForRecycler

    init {
        getNewsFeed()
    }

    private fun getNewsFeed() {
        viewModelScope.launch {
            _newsForRecycler.value = getNewsFeedUseCase.execute()
        }
    }
}