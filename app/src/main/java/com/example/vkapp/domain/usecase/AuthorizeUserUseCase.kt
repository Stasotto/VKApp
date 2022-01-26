package com.example.vkapp.domain.usecase

import com.example.vkapp.domain.repositories.Repository

class AuthorizeUserUseCase(private val repository: Repository) {

    suspend fun execute(): Boolean {
        TODO()
    }
}