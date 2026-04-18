package intro.sensors_04_multimedia.tiltjoanasantos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun CountdownScreen(categoryId: Int, navController: NavController) {
    var secondsLeft by remember { mutableIntStateOf(5) }

    LaunchedEffect(Unit) {
        while (secondsLeft > 0) {
            delay(1000L)
            secondsLeft--
        }
        navController.navigate("game/$categoryId") {
            popUpTo("countdown/$categoryId") { inclusive = true }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = if (secondsLeft > 0) secondsLeft.toString() else "JÁ!",
            fontSize = 120.sp,
            fontWeight = FontWeight.Black
        )
    }
}