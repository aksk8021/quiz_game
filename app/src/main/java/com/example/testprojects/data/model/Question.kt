package com.example.testprojects.data.model

data class Question(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctOptionIndex: Int
)
