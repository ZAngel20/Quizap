package com.politecnico.quizap.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.politecnico.quizap.data.Model.API.MiAPI
import com.politecnico.quizap.data.Model.GameRepository
import com.politecnico.quizap.data.Model.Ranking
import kotlinx.coroutines.launch

class RankingScreenViewModel private constructor() : ViewModel() {
    private val _listRanking = MutableLiveData<List<Ranking>>()
    val listRanking: LiveData<List<Ranking>> = _listRanking


    fun setRanking(context: Context) {
        val gameRepository: GameRepository by lazy { GameRepository(MiAPI.instance) }
            viewModelScope.launch {
                val result: Result<List<Ranking>> = gameRepository.getRanking(context)
                try {
                    Log.d("Resultado Bueno:", result.toString())
                    val listRankings: List<Ranking>? = result.getOrNull()
                    if (listRankings != null) {
                        _listRanking.value = listRankings!!
                    }
                } catch (e: Exception) {
                    Log.d("Resultado Malo:", result.toString())
                    _listRanking.value = emptyList<Ranking>()

                }
            }
    }
    companion object {
        private var instance: RankingScreenViewModel? = null

        fun getInstance(): RankingScreenViewModel {
            if (instance == null) {
                instance = RankingScreenViewModel()
            }
            return instance!!
        }
    }
}