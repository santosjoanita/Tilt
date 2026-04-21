package intro.sensors_04_multimedia.tiltjoanasantos.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicVideo
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SportsBasketball

object GameRepo {
    val categories = listOf(
        WordCategory(1, "Animais", listOf(
            "Cão", "Gato", "Leão", "Elefante", "Zebra", "Girafa", "Tigre", "Pinguim",
            "Baleia", "Golfinho", "Canguru", "Panda", "Gorila", "Coelho", "Tartaruga",
            "Águia", "Papagaio", "Cobra", "Tubarão", "Cavalo"
        ), 0xFF4CAF50, Icons.Default.Pets),

        WordCategory(2, "Filmes", listOf(
            "Avatar", "Titanic", "Shrek", "Matrix", "Gladiador", "Star Wars", "Harry Potter", "Joker",
            "Inception", "Toy Story", "Rei Leão", "Vingadores", "Batman", "Spider-Man", "Jurassic Park"
        ), 0xFF2196F3, Icons.Default.Movie),

        WordCategory(3, "Países", listOf(
            "Portugal", "Brasil", "Espanha", "França", "Japão", "Angola", "Itália", "Alemanha",
            "China", "Índia", "EUA", "Canadá", "México", "Argentina", "Egito"
        ), 0xFFFF9800, Icons.Default.Public),

        WordCategory(4, "Comida", listOf(
            "Pizza", "Hambúrguer", "Sushi", "Lasanha", "Bacalhau", "Arroz", "Feijão", "Ovo",
            "Pão", "Queijo", "Fiambre", "Sopa", "Salada", "Bife", "Batatas Fritas",
            "Maçã", "Banana", "Morango", "Chocolate", "Gelado", "Bolo"
        ), 0xFFF44336, Icons.Default.Restaurant),

        WordCategory(5, "Música", listOf(
            "Rock", "Pop", "Jazz", "Fado", "Hip Hop", "Reggae", "Clássica", "Metal",
            "Funk", "Samba", "Eletrónica", "Country", "Blues", "Soul", "Rap",
            "Punk", "Disco", "Techno", "K-Pop", "Latina"
        ), 0xFF9C27B0, Icons.Default.MusicNote),

        WordCategory(6, "Desporto", listOf(
            "Futebol", "Basquetebol", "Ténis", "Natação", "Voleibol", "Andebol", "Rugby", "Golfe",
            "Surf", "Skate", "Boxe", "Ciclismo", "Atletismo", "Ginástica", "Karaté",
            "Judo", "Yoga", "Ping Pong", "Padel", "Hóquei"
        ), 0xFF00BCD4, Icons.Default.SportsBasketball),

        WordCategory(7, "Instrumentos", listOf(
            "Guitarra", "Piano", "Bateria", "Violino", "Flauta", "Saxofone", "Trompete", "Baixo",
            "Harpa", "Violoncelo", "Clarinete", "Acordeão", "Ukelele", "Gaita de Foles", "Pandeireta"
        ), 0xFFFFC107, Icons.Default.MusicVideo),

        WordCategory(8, "Transporte", listOf(
            "Carro", "Mota", "Autocarro", "Comboio", "Avião", "Barco", "Bicicleta", "Metro",
            "Camião", "Helicóptero", "Trator", "Trotinete", "Submarino", "Foguete", "Canoa"
        ), 0xFF795548, Icons.Default.DirectionsBus)
    )
}
