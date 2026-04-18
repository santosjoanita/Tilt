package intro.sensors_04_multimedia.tiltjoanasantos.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import intro.sensors_04_multimedia.tiltjoanasantos.R
import intro.sensors_04_multimedia.tiltjoanasantos.data.WordCategory
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    var score by mutableIntStateOf(0)
    var timeLeft by mutableIntStateOf(45)
    var currentWord by mutableStateOf("")
    var gameState by mutableStateOf("MENU") // MENU, PLAYING, RESULT

    private var wordsList = mutableListOf<String>()
    private var timerJob: Job? = null

    fun setupGame(category: WordCategory) {
        wordsList = category.words.shuffled().toMutableList()
        score = 0
        timeLeft = 45
        gameState = "PLAYING"
        nextWord()
    }

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            gameState = "RESULT"
        }
    }
    fun playSound(context: Context, isCorrect: Boolean) {
        val resId = if (isCorrect) R.raw.correct else R.raw.wrong
        MediaPlayer.create(context, resId).start()
    }
    fun nextWord() {
        if (wordsList.isNotEmpty()) {
            currentWord = wordsList.removeAt(0)
        } else {
            gameState = "RESULT"
            timerJob?.cancel()
        }
    }

    fun onCorrectAnswer() {
        if (gameState == "PLAYING") {
            score++
            nextWord()
            gameState = "WAITING"
            viewModelScope.launch {
                delay(1000)
                gameState = "PLAYING"
            }
        }
    }

    fun onSkipAnswer() {
        if (gameState == "PLAYING") {
            nextWord()
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}