package com.example.vkapp.domain.repositories

interface Repository {

    suspend fun authorizeUser(): Boolean

    suspend fun getNewsFeed()

    suspend fun getSumOfLikes()
}