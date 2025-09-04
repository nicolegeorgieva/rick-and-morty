package com.example.rickandmorty.screen.home

sealed interface HomeEvent {
  data object CharactersClick : HomeEvent
  data object LocationsClick : HomeEvent
  data object EpisodesClick : HomeEvent
}