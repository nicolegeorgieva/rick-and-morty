package com.example.rickandmorty.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
  @Serializable
  data object Home : Screen
}