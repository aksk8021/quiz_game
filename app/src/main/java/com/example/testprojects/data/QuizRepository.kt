package com.example.testprojects.data

import com.example.testprojects.data.model.Question
import com.example.testprojects.data.remote.RetrofitInstance
import retrofit2.Response

class QuizRepository {
    suspend fun getQuestions(): Response<List<Question>> {
        return RetrofitInstance.api.getQuestions()
    }
}