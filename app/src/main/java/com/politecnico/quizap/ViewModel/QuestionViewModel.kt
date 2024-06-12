package com.politecnico.quizap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.politecnico.quizap.data.Model.Answer
import com.politecnico.quizap.data.Model.Question

class QuestionViewModel private constructor() : ViewModel() {
    private val _listQuestion = MutableLiveData<List<Question>>()
    val listQuestion: LiveData<List<Question>> = _listQuestion


    fun setQuestions(questions : List<Question>) {
        val prueba = listOf<Question>(
            Question(1,1,"¿Que es el tomate?",0, listOf<Answer>(
                 Answer(0 ,"Si")
                ,Answer(1 ,"Fruta")
                ,Answer(2 ,"Vegetal")
                ,Answer(3 ,"Pajaro")), 1),
            Question(2,1,"¿Cuantas vocales hay?",1,listOf<Answer>(
                 Answer(0 ,"3")
                ,Answer(1 ,"6")
                ,Answer(2 ,"4")
                ,Answer(3 ,"5")), 3),
            Question(3,1,"¿Cual mes tiene exactamente 28 o 29 dias?",2,listOf<Answer>(
                 Answer(0 ,"Marzo")
                ,Answer(1 ,"Diciembre")
                ,Answer(2 ,"Febrero")
                ,Answer(3 ,"Junio")), 2),
            Question(4,1,"¿Cual es la capital de Francia?",2,listOf<Answer>(
                 Answer(0 ,"Paris")
                ,Answer(1 ,"Salamanca")
                ,Answer(2 ,"Grecia")
                ,Answer(3 ,"Polonia")), 0),
            Question(5,1,"¿Que Marca el centro del mundo?",2,listOf<Answer>(
                Answer(0 ,"Polo Sur")
                ,Answer(1 ,"Polo Norte")
                ,Answer(2 ,"Ambos")
                ,Answer(3 ,"Ecuador")), 3)
        )
        _listQuestion.value = prueba
    }

    data class Question(
        val id : Int,
        val idLevel  : Int,
        val text: String,
        val index: Int,
        val answers: List<Answer>,
        val correctAnswer: Int
    )
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