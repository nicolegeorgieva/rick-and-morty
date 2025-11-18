package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.rickandmorty.ui.character.CharacterScreen
import com.example.rickandmorty.ui.characters.CharactersScreen
import com.example.rickandmorty.ui.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = Screen.Home
  ) {
    composable<Screen.Home> {
      HomeScreen()
    }
    composable<Screen.Characters> {
      CharactersScreen()
    }
    composable<Screen.Character> {
      CharacterScreen(
        screen = it.toRoute<Screen.Character>()
      )
    }
  }
}