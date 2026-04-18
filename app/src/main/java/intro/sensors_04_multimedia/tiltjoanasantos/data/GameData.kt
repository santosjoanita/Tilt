package intro.sensors_04_multimedia.tiltjoanasantos.data

// Estrutura básica para uma categoria
data class Category(
    val name: String,
    val words: List<String>,
    val color: Long
)

object GameRepo {
    val categories = listOf(
        Category("Animais", listOf("Cão", "Gato", "Elefante", "Leão", "Girafa"), 0xFF4CAF50),
        Category("Filmes", listOf("Titanic", "Avatar", "Shrek", "Batman", "Matrix"), 0xFF2196F3),
        Category("Países", listOf("Portugal", "Brasil", "Espanha", "França", "Itália"), 0xFFFF9800)
    )
}