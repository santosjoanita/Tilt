package intro.sensors_04_multimedia.tiltjoanasantos.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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

data class GameResult(val word: String, val isCorrect: Boolean)

class GameViewModel : ViewModel() {
    var score by mutableIntStateOf(0)
    var wrongAnswers by mutableIntStateOf(0)
    var timeLeft by mutableIntStateOf(45)
    var currentWord by mutableStateOf("")
    var gameState by mutableStateOf("MENU")
    var currentCategory: WordCategory? by mutableStateOf(null)

    var backgroundColor by mutableStateOf(Color(0xFF2196F3))
    private var defaultColor = Color(0xFF2196F3)

    private var wordsList = mutableListOf<String>()
    private var timerJob: Job? = null

    val gameHistory = mutableStateListOf<GameResult>()

    fun setupGame(category: WordCategory) {
        currentCategory = category
        wordsList = category.words.shuffled().toMutableList()
        score = 0
        wrongAnswers = 0
        timeLeft = 45
        gameState = "PLAYING"
        gameHistory.clear()
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
            finishGame()
        }
    }

    private fun nextWord() {
        if (wordsList.isNotEmpty()) {
            currentWord = wordsList.removeAt(0)
        } else {
            finishGame()
        }
    }

    private fun finishGame() {
        gameState = "RESULT"
        timerJob?.cancel()
    }

    fun onCorrectAnswer(context: Context) {
        if (gameState == "PLAYING") {
            gameHistory.add(GameResult(currentWord, true))
            score++
            viewModelScope.launch {
                backgroundColor = Color.Green
                vibrate(context, 100)
                playSound(context, R.raw.correct)
                nextWord()
                delay(500)
                backgroundColor = defaultColor
            }
        }
    }

    fun onSkipAnswer(context: Context) {
        if (gameState == "PLAYING") {
            gameHistory.add(GameResult(currentWord, false))
            wrongAnswers++
            viewModelScope.launch {
                backgroundColor = Color.Red
                vibrate(context, 300) // Vibração mais longa para erro
                playSound(context, R.raw.wrong)
                nextWord()
                delay(500)
                backgroundColor = defaultColor
            }
        }
    }

    private fun vibrate(context: Context, duration: Long) {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(duration)
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

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
