package com.example.vkapp.domain

import com.example.vkapp.domain.repository.Repository

class AuthorizeUserUseCase(private val repository: Repository) {

    suspend fun execute(): Boolean {
        TODO()
    }
}