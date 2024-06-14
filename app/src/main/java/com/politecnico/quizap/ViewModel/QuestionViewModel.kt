package com.politecnico.quizap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.politecnico.quizap.data.Model.Question

class QuestionViewModel private constructor() : ViewModel() {
    private val _listQuestion = MutableLiveData<List<Question>>()
    val listQuestion: LiveData<List<Question>> = _listQuestion


    fun setQuestions(questions: List<Question>) {
        _listQuestion.value = questions
    }
    fun shuffleAnswers() {
        _listQuestion.value?.forEach { question ->
            question.answers = question.answers.shuffled()
        }
        _listQuestion.value?.shuffled()
    }
    companion object {
        private var instance: QuestionViewModel? = null

        fun getInstance(): QuestionViewModel {
            if (instance == null) {
                instance = QuestionViewModel()
            }
            return instance!!
        }
    }
}