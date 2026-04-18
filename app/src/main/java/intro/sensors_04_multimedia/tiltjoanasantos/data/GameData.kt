package intro.sensors_04_multimedia.tiltjoanasantos.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Public

object GameRepo {
    val categories = listOf(
        WordCategory(
            1, 
            "Animais", 
            listOf("Cão", "Gato", "Leão", "Elefante", "Zebra"), 
            0xFF4CAF50, 
            Icons.Default.Pets
        ),
        WordCategory(
            2, 
            "Filmes", 
            listOf("Avatar", "Titanic", "Shrek", "Matrix", "Gladiador"), 
            0xFF2196F3, 
            Icons.Default.Movie
        ),
        WordCategory(
            3, 
            "Países", 
            listOf("Portugal", "Brasil", "Espanha", "França", "Japão"), 
            0xFFFF9800, 
            Icons.Default.Public
        )
    )
}
