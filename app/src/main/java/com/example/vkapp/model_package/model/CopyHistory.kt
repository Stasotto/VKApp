package com.example.vkapp.model_package.model

data class CopyHistory(
    val attachments: List<AttachmentX>,
    val date: Int,
    val from_id: Int,
    val id: Int,
    val owner_id: Int,
    val post_source: PostSource,
    val post_type: String,
    val text: String
)