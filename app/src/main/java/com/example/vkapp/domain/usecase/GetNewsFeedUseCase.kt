package com.example.vkapp.domain.usecase

import com.example.vkapp.domain.repositories.Repository

class GetNewsFeedUseCase(private val repository: Repository) {

    suspend fun execute(): Boolean {
        TODO()
    }
}