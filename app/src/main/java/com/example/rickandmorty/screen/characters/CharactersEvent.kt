package com.example.rickandmorty.screen.characters

sealed interface CharactersEvent {
  data object BackClick : CharactersEvent
}