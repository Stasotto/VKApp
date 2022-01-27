package com.example.vkapp.domain

import com.example.vkapp.domain.repository.Repository

class GetNewsFeedUseCase(private val repository: Repository) {

    suspend fun execute(): Boolean {
        TODO()
    }
}