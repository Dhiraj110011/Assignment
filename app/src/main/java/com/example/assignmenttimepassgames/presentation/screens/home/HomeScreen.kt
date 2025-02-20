package com.example.assignmenttimepassgames.presentation.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

val Blue = Color(66, 134, 244)

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Random Dog Generator!",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Button(
                onClick = { navController.navigate("generate") },
                colors = ButtonDefaults.buttonColors(Blue),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    "Generate Dogs",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            Button(
                onClick = { navController.navigate("gallery") },
                colors = ButtonDefaults.buttonColors(Blue),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    "My Recently Generated Dogs",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}