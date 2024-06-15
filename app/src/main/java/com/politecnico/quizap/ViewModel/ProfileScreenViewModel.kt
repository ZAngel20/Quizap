package com.politecnico.quizap.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.GameRepository
import com.politecnico.quizap.data.Model.Profile
import com.politecnico.quizap.data.Model.Ranking
import kotlinx.coroutines.launch

class ProfileScreenViewModel private constructor() : ViewModel() {
    private val _username = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private val _ranking = MutableLiveData<Int?>()

    val username: LiveData<String>  = _username
    val email: LiveData<String>  = _email
    val score: LiveData<Int>  = _score
    val ranking: LiveData<Int?>  = _ranking

        fun setProfile(profile : Profile) {
            _username.value = profile.name
            _email.value = profile.email
    }
        fun updateScore(context : Context) {
            val gameRepository: GameRepository by lazy { GameRepository(MiAPI.instance) }
            viewModelScope.launch {
                val result : Result<Int> = gameRepository.getScore(context)
                try {

                    val newscore : Int = result.getOrDefault(0)
                    if (newscore != 0) {
                        Log.d("NewScore" , newscore.toString())
                        _score.value = newscore
                        Log.d("Se ha cambiado?",_score.value.toString())
                        Log.d("Resultado:",result.toString())
                    } else {
                        Log.d("Resultado:",result.toString())
                    }
                } catch (e: Exception) {
                    Log.d("Resultado:",result.toString())
                }
            }

        }
        fun updateRanking(context : Context, idUser : String ) {
            val gameRepository: GameRepository by lazy { GameRepository(MiAPI.instance) }
            viewModelScope.launch{
                val result : Result<List<Ranking>> = gameRepository.getRanking(context)
                try {
                    val listRanking : List<Ranking> = result.getOrDefault(emptyList())
                    if (listRanking.isNotEmpty()) {
                        var i = 1
                        var userPosition : Int = 0
                        listRanking.forEach { ranking ->
                            if (ranking.idUser == idUser)
                            { userPosition = i} else {
                                i++
                            }
                        }
                        //val userPosition = listRanking.indexOfFirst { it.idUser.equals(idUser)}
                        Log.d("UserPosition", userPosition.toString())
                        if (userPosition == 0) {
                            null
                        } else {
                            userPosition
                        }
                        _ranking.value = userPosition
                        Log.d("Resultado:",result.toString())
                    } else {
                        Log.d("Resultado:",result.toString())
                    }
                } catch (e: Exception) {
                    Log.d("Resultado:",result.toString())
                }
            }

        }
    companion object {
        private var instance: ProfileScreenViewModel? = null

        fun getInstance(): ProfileScreenViewModel {
            if (instance == null) {
                instance = ProfileScreenViewModel()
            }
            return instance!!
        }
    }
}