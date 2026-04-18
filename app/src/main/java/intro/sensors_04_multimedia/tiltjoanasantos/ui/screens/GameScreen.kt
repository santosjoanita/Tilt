package intro.sensors_04_multimedia.tiltjoanasantos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import intro.sensors_04_multimedia.tiltjoanasantos.data.GameData
import intro.sensors_04_multimedia.tiltjoanasantos.sensors.TiltManager
import intro.sensors_04_multimedia.tiltjoanasantos.viewmodel.GameViewModel

@Composable
fun GameScreen(categoryId: Int, navController: NavController, viewModel: GameViewModel) {
    val context = LocalContext.current

    // CORREÇÃO: Agora retorna um objeto WordCategory em vez de uma Lista como fallback
    val category = remember(categoryId) {
        GameData.categories.find { it.id == categoryId } ?: GameData.categories.first()
    }

    val tiltManager = remember {
        TiltManager(context).apply {
            onTiltUp = { viewModel.onCorrectAnswer() }
            onTiltDown = { viewModel.onSkipAnswer() }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setupGame(category)
        viewModel.startTimer()
        tiltManager.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            tiltManager.stop()
        }
    }

    if (viewModel.gameState == "RESULT") {
        LaunchedEffect(Unit) {
            navController.navigate("results") {
                popUpTo("game/$categoryId") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(category.color)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = viewModel.timeLeft.toString(),
                fontSize = 48.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = viewModel.currentWord.uppercase(),
                fontSize = 72.sp,
                color = Color.White,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Pontos: ${viewModel.score}",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
