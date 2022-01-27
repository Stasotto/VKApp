package com.example.vkapp.model

data class Profile(
    val bdate: String,
    val can_access_closed: Boolean,
    val deactivated: String,
    val first_name: String,
    val id: Int,
    val is_closed: Boolean,
    val last_name: String
)