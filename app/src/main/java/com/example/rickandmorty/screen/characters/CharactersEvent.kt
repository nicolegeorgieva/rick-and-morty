package com.example.rickandmorty.screen.characters

sealed interface CharactersEvent {
  data object BackClick : CharactersEvent
  data class CharacterClick(val id: Int) : CharactersEvent
  data class LocationClick(val locationUrl: String) : CharactersEvent
  data object LoadNewPage : CharactersEvent
}