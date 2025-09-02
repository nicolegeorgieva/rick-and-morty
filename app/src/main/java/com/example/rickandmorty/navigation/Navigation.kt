package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmorty.screen.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = Screen.Home
  ) {
    composable<Screen.Home> {
      HomeScreen()
    }
  }
}