package com.example.proyectoapizelda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.proyectoapizelda.Modelos.Monster
import com.example.proyectoapizelda.ViewModel.GameViewModel
import com.example.proyectoapizelda.ViewModel.MonsterViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonsterScreen(viewModel: MonsterViewModel, gameViewModel: GameViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val monsters by viewModel.monsters.collectAsState(initial = emptyList())
    val errorMessage by viewModel.error.collectAsState()
    val games by gameViewModel.games.collectAsState(initial = emptyList())


    val gameUrlToNameMap = games.associateBy { it.id }.mapValues { it.value.name }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(43, 114, 24)) // Color de fondo verde
        .padding(16.dp)
    ) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar monstruos", color = Color.Black) },
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
            val filteredMonsters = monsters.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }

            items(filteredMonsters) { monster ->

                val gameNames = monster.appearances.mapNotNull { url ->
                    val gameId = url.split("/").last()
                    gameUrlToNameMap[gameId]
                }

                MonsterItem(monster.copy(gameNames = gameNames))
            }
        }
    }


    LaunchedEffect(Unit) {
        viewModel.fetchMonsters()
        gameViewModel.fetchGames()
    }
}

@Composable
fun MonsterItem(monster: Monster) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(196, 175, 109))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = monster.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                overflow = TextOverflow.Visible
            )


            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = monster.description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                overflow = TextOverflow.Visible
            )


            Spacer(modifier = Modifier.height(8.dp))
            monster.gameNames.forEach { gameName ->
                Text(
                    text = "Aparece en: $gameName",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}