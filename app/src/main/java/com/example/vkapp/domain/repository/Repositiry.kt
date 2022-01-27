package com.example.vkapp.domain.repository

interface Repository {

    suspend fun authorizeUser(): Boolean

    suspend fun getNewsFeed()

    suspend fun getSumOfLikes()
}