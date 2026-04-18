package intro.sensors_04_multimedia.tiltjoanasantos.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
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
    var gameState by mutableStateOf("MENU")

    var backgroundColor by mutableStateOf(Color(0xFF2196F3))
    private var defaultColor = Color(0xFF2196F3)

    private var wordsList = mutableListOf<String>()
    private var timerJob: Job? = null

    fun setupGame(category: WordCategory) {
        wordsList = category.words.shuffled().toMutableList()
        score = 0
        timeLeft = 45
        gameState = "PLAYING"
        defaultColor = Color(category.color)
        backgroundColor = defaultColor
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

    fun nextWord() {
        if (wordsList.isNotEmpty()) {
            currentWord = wordsList.removeAt(0)
        } else {
            gameState = "RESULT"
        }
    }

    fun onCorrectAnswer(context: Context) {
        if (gameState == "PLAYING") {
            score++
            viewModelScope.launch {
                backgroundColor = Color.Green
                playSound(context, R.raw.correct)
                nextWord()
                delay(500)
                backgroundColor = defaultColor
            }
        }
    }

    fun onSkipAnswer(context: Context) {
        if (gameState == "PLAYING") {
            viewModelScope.launch {
                backgroundColor = Color.Red
                playSound(context, R.raw.wrong)
                nextWord()
                delay(500)
                backgroundColor = defaultColor
            }
        }
    }

    private fun playSound(context: Context, resId: Int) {
        try {
            val mp = MediaPlayer.create(context, resId)
            mp.start()
            mp.setOnCompletionListener { it.release() }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}