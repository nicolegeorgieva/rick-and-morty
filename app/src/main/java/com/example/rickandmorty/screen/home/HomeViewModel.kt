package com.example.rickandmorty.screen.home

import androidx.compose.runtime.Composable
import com.example.rickandmorty.ComposeViewModel

class HomeViewModel() : ComposeViewModel<HomeState, HomeEvent>() {
  @Composable
  override fun uiState(): HomeState {
    return HomeState()
  }

  override fun onEvent(event: HomeEvent) {
    when (event) {
      HomeEvent.CharactersClick -> handleCharactersClick()
      HomeEvent.LocationsClick -> handleLocationsClick()
      HomeEvent.EpisodesClick -> handleEpisodesClick()
    }
  }

  private fun handleCharactersClick() {
    // TODO
  }

  private fun handleLocationsClick() {
    // TODO
  }

  private fun handleEpisodesClick() {
    // TODO
  }
}