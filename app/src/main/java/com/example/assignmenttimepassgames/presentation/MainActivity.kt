package com.example.assignmenttimepassgames.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignmenttimepassgames.presentation.screens.gallery.RecentDogsScreen
import com.example.assignmenttimepassgames.presentation.screens.generate.GenerateDogScreen
import com.example.assignmenttimepassgames.presentation.screens.home.HomeScreen
import com.example.assignmenttimepassgames.ui.theme.AssignmentTimepassGamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentTimepassGamesTheme {
                DogApp()
            }
        }
    }
}

@Composable
fun DogApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController = navController)
            }
            composable("generate") {
                GenerateDogScreen()
            }
            composable("gallery") {
                RecentDogsScreen()
            }
        }
    }
}