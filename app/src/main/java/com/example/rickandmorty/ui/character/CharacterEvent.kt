package com.example.rickandmorty.ui.character

sealed interface CharacterEvent {
  data object BackClick : CharacterEvent
}