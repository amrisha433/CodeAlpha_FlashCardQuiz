package com.example.flashcardquizapp.Navigation


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Flashcard : Screen("flashcard")
    object End : Screen("end")
}