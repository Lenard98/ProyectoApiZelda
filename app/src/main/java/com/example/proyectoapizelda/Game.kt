package com.example.proyectoapizelda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextOverflow
import com.example.proyectoapizelda.Modelos.Game
import com.example.proyectoapizelda.ViewModel.GameViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(viewModel: GameViewModel) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val games by viewModel.games.collectAsState(initial = emptyList())
    val errorMessage by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(43, 114, 24))
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar juegos", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))


        errorMessage?.let {
            Snackbar(
                modifier = Modifier.padding(8.dp),
                content = { Text(text = it, color = Color.Black) },
                containerColor = Color.Yellow
            )
        }


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            val filteredGames = games.filter {
                it.name.contains(searchQuery.text, ignoreCase = true)
            }

            items(filteredGames) { game ->
                GameItem(game)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchGames()
    }
}

@Composable
fun GameItem(game: Game) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(196, 175, 109))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = game.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                overflow = TextOverflow.Visible
            )
            Text(
                text = "Desarrollador: ${game.developer}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
            Text(
                text = "Publicado por: ${game.publisher}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
            Text(
                text = "Fecha de lanzamiento: ${game.released_date}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = game.description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                overflow = TextOverflow.Visible
            )
        }
    }
}
