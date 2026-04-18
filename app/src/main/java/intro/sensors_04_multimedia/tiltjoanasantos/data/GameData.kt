package intro.sensors_04_multimedia.tiltjoanasantos.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Work

object GameRepo {
    val categories = listOf(
        WordCategory(1, "Animais", listOf(
            "Cão", "Gato", "Leão", "Elefante", "Zebra", "Girafa", "Tigre", "Pinguim",
            "Baleia", "Golfinho", "Canguru", "Panda", "Gorila", "Coelho", "Tartaruga",
            "Águia", "Papagaio", "Cobra", "Tubarão", "Cavalo", "Vaca", "Ovelha", "Rato", "Formiga", "Abelha"
        ), 0xFF4CAF50, Icons.Default.Pets),

        WordCategory(2, "Filmes", listOf(
            "Avatar", "Titanic", "Shrek", "Matrix", "Gladiador", "Star Wars", "Harry Potter", "Joker",
            "Inception", "Toy Story", "Rei Leão", "Vingadores", "Batman", "Spider-Man", "Jurassic Park",
            "Frozen", "Moana", "Jaws", "Alien", "Psycho", "Casablanca", "Grease", "Up", "Coco", "Cars"
        ), 0xFF2196F3, Icons.Default.Movie),

        WordCategory(3, "Países", listOf(
            "Portugal", "Brasil", "Espanha", "França", "Japão", "Angola", "Itália", "Alemanha",
            "China", "Índia", "EUA", "Canadá", "México", "Argentina", "Egito", "Marrocos",
            "Grécia", "Rússia", "Austrália", "Suécia", "Noruega", "Suíça", "Bélgica", "Holanda", "Turquia"
        ), 0xFFFF9800, Icons.Default.Public),

        WordCategory(4, "Objetos", listOf(
            "Cadeira", "Mesa", "Telemóvel", "Computador", "Caneta", "Livro", "Relógio", "Óculos",
            "Garrafa", "Chave", "Carteira", "Mochila", "Janela", "Porta", "Lâmpada", "Espelho",
            "Escova", "Martelo", "Tesoura", "Colher", "Garfo", "Faca", "Prato", "Copo", "Almofada"
        ), 0xFF9C27B0, Icons.Default.Category),

        WordCategory(5, "Profissões", listOf(
            "Médico", "Enfermeiro", "Professor", "Engenheiro", "Polícia", "Bombeiro", "Cozinheiro", "Padeiro",
            "Pintor", "Músico", "Atleta", "Programador", "Advogado", "Juiz", "Arquiteto", "Dentista",
            "Veterinário", "Jornalista", "Fotógrafo", "Carpinteiro", "Mecânico", "Eletricista", "Piloto", "Marinheiro", "Astronauta"
        ), 0xFFE91E63, Icons.Default.Work)
    )
}