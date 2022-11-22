package com.example.gm.model
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    val phoneNumber: MutableMap<String, String>,
    val email: MutableMap<String, String>,
    val photoUrl: String


) : java.io.Serializable

