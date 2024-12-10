package com.example.proyectoapizelda

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color

@Composable
fun BottomBarComponent(navController: NavController) {
    BottomAppBar(
        containerColor = Color.Black,
        contentColor = Color.White,
        content = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {
                        navController.navigate("Character")
                    }) {

                        androidx.compose.foundation.Image(
                            painter = painterResource(id = R.drawable.brutal),
                            contentDescription = "Character",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(text = "Character")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {
                        navController.navigate("Game")
                    }) {

                        androidx.compose.foundation.Image(
                            painter = painterResource(id = R.drawable.gamepad),
                            contentDescription = "Game",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(text = "Game")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {
                        navController.navigate("Bosses")
                    }) {

                        androidx.compose.foundation.Image(
                            painter = painterResource(id = R.drawable.boss),
                            contentDescription = "Bosses",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(text = "Bosses")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {
                        navController.navigate("Dungeons")
                    }) {

                        androidx.compose.foundation.Image(
                            painter = painterResource(id = R.drawable.dungeons),
                            contentDescription = "Dungeons",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(text = "Dungeons")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {
                        navController.navigate("Monster")
                    }) {

                        androidx.compose.foundation.Image(
                            painter = painterResource(id = R.drawable.monster),
                            contentDescription = "Monster",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(text = "Monster")
                }
            }
        }
    )
}