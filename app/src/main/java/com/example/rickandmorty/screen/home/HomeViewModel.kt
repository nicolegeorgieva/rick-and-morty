package com.example.rickandmorty.screen.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.ComposeViewModel
import com.example.rickandmorty.navigation.Navigator
import com.example.rickandmorty.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val navigator: Navigator,
) : ComposeViewModel<HomeState, HomeEvent>() {
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
    viewModelScope.launch {
      navigator.navigateTo(Screen.Characters)
    }
  }

  private fun handleLocationsClick() {
    // TODO
  }

  private fun handleEpisodesClick() {
    // TODO
  }
}