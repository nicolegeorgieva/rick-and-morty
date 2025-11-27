package com.example.rickandmorty.ui.characters

sealed interface CharactersEvent {
  data object BackClick : CharactersEvent
  data class CharacterClick(val id: Int) : CharactersEvent
  data object LoadNewPage : CharactersEvent
}