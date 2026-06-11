package com.example.flashcardquizapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcardquizapp.EndScreen
import com.example.flashcardquizapp.FlashCardScreen
import com.example.flashcardquizapp.HomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                onStartClick = {
                    navController.navigate(Screen.Flashcard.route)
                }
            )
        }

        composable(Screen.Flashcard.route) {
            FlashCardScreen(
                onFinish = {
                    navController.navigate(Screen.End.route)
                }
            )
        }

        composable(Screen.End.route) {
            EndScreen(
                onRestartClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}