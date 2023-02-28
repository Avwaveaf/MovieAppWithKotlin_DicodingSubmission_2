package com.example.movieapp.model

data class AboutMe(
    val fullName: String,
    val email: String,
    val imageUrl: String
)

fun getProfile(): AboutMe {
    return AboutMe(
        fullName = "Muhamad Afif Fadillah",
        email = "muhamad.fadillah@student.president.ac.id",
        imageUrl = "https://avatars.githubusercontent.com/u/49422146?v=4"
    )
}