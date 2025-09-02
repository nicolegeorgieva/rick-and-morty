package com.example.rickandmorty.navigation

sealed interface NavigationEvent {
  data class GoTo(val screen: Screen) : NavigationEvent
  data object Back : NavigationEvent
}