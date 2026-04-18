package intro.sensors_04_multimedia.tiltjoanasantos.data

data class WordCategory(
    val id: Int,
    val name: String,
    val words: List<String>,
    val color: Long
)

object GameData {
    val categories = listOf(
        WordCategory(1, "Animais", listOf("Leão", "Elefante", "Zebra", "Girafa", "Cão"), 0xFF4CAF50),
        WordCategory(2, "Filmes", listOf("Matrix", "Avatar", "Titanic", "Gladiador", "Shrek"), 0xFF2196F3),
        WordCategory(3, "Países", listOf("Portugal", "França", "Japão", "Brasil", "Angola"), 0xFFFF9800)
    )
}