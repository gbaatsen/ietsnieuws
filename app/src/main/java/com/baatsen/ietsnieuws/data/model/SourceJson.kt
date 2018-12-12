package com.baatsen.ietsnieuws.data.model

data class SourceJson(
    val id: String,
    val name: String,
    val description: String?,
    val url: String?,
    val category: String?,
    val language: String?,
    val country: String?
)
