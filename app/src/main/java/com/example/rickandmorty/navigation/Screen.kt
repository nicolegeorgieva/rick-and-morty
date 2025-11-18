package com.example.rickandmorty.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
  @Serializable
  data object Home : Screen

  @Serializable
  data object Characters : Screen

  @Serializable
  data class Character(val id: Int) : Screen
}