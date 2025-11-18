package com.example.rickandmorty.ui.home

sealed interface HomeEvent {
  data object CharactersClick : HomeEvent
  data object LocationsClick : HomeEvent
  data object EpisodesClick : HomeEvent
}