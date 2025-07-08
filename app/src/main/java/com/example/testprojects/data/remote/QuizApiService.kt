package com.example.testprojects.data.remote

import com.example.testprojects.data.model.Question
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {
    @GET("53846277a8fcb034e482906ccc0d12b2/raw")
    suspend fun getQuestions(): Response<List<Question>>
}