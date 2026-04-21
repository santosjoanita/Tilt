package intro.sensors_04_multimedia.tiltjoanasantos.ui.screens

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import intro.sensors_04_multimedia.tiltjoanasantos.data.GameRepo
import intro.sensors_04_multimedia.tiltjoanasantos.sensors.TiltSensorManager
import intro.sensors_04_multimedia.tiltjoanasantos.viewmodel.GameViewModel

@Composable
fun GameScreen(categoryId: Int, navController: NavController, viewModel: GameViewModel) {
    val context = LocalContext.current
    val activity = context as? Activity

    DisposableEffect(Unit) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    val category = remember(categoryId) {
        GameRepo.categories.find { it.id == categoryId } ?: GameRepo.categories.first()
    }

    val tiltManager = remember {
        TiltSensorManager(context).apply {
            onTiltUp = { viewModel.onCorrectAnswer(context) }
            onTiltDown = { viewModel.onSkipAnswer(context) }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setupGame(category)
        viewModel.startTimer()
        tiltManager.start()
    }

    DisposableEffect(Unit) {
        onDispose { tiltManager.stop() }
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
            .background(viewModel.backgroundColor)
    ) {

        Button(
            onClick = {
                viewModel.gameState = "MENU"
                navController.navigate("menu") {
                    popUpTo("menu") { inclusive = true }
                }
            },
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomEnd),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White.copy(alpha = 0.3f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text("MENU", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = viewModel.timeLeft.toString(), fontSize = 48.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = viewModel.currentWord.uppercase(), fontSize = 72.sp, color = Color.White, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Pontos: ${viewModel.score}", fontSize = 24.sp, color = Color.White)
        }
    }
}
