package intro.sensors_04_multimedia.tiltjoanasantos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import intro.sensors_04_multimedia.tiltjoanasantos.viewmodel.GameViewModel

@Composable
fun ResultsScreen(navController: NavController, viewModel: GameViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FIM DA RONDA",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${viewModel.score}",
            fontSize = 80.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "palavras acertadas",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                viewModel.gameState = "MENU"
                navController.navigate("menu") {
                    popUpTo("menu") { inclusive = true }
                }
            },
            modifier = Modifier.width(200.dp).height(50.dp)
        ) {
            Text("JOGAR NOVAMENTE")
        }
    }
}