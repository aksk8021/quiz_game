package com.example.testprojects.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprojects.data.QuizRepository
import com.example.testprojects.data.model.Question
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private val repository = QuizRepository()

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    var currentIndex = MutableLiveData(0)
    var correctAnswers = 0
    var skippedQuestions = 0
    var streak = 0
    var longestStreak = 0

    fun fetchQuestions() {
        viewModelScope.launch {
            try {
                val response = repository.getQuestions()
                if (response.isSuccessful && response.body() != null) {
                    _questions.value = response.body()
                } else {
                    _error.value = "Error fetching questions"
                }
            } catch (e: Exception) {
                _error.value = "Network error: ${e.message}"
            }
        }
    }

    fun goToNextQuestion() {
        val next = currentIndex.value?.plus(1) ?: 0
        currentIndex.value = next
    }

    fun resetQuiz() {
        currentIndex.value = 0
        correctAnswers = 0
        skippedQuestions = 0
        streak = 0
        longestStreak = 0
        _error.value = null
    }

    fun submitAnswer(userAnswer: String) {
        val index = currentIndex.value ?: return
        val currentQuestion = _questions.value?.getOrNull(index) ?: return
        val correctAnswer = currentQuestion.options.getOrNull(currentQuestion.correctOptionIndex)

        if (userAnswer == correctAnswer) {
            correctAnswers++
            streak++
            if (streak > longestStreak) longestStreak = streak
        } else {
            streak = 0
        }
    }

    fun skipQuestion() {
        skippedQuestions++
        streak = 0
    }
}