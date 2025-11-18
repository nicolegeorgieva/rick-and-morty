package com.example.rickandmorty.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.ui.home.component.HomeMenu
import com.example.rickandmorty.ui.home.component.HomeTitle

@Composable
fun HomeScreen() {
  val viewModel: HomeViewModel = hiltViewModel()

  HomeUi(onEvent = viewModel::onEvent)
}

@Composable
fun HomeUi(
  onEvent: (HomeEvent) -> Unit
) {
  Scaffold(
    content = { padding ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(padding)
          .padding(24.dp),
        verticalArrangement = Arrangement.Center,
      ) {
        HomeTitle(modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(32.dp))
        HomeMenu(
          onCharactersClick = {
            onEvent(HomeEvent.CharactersClick)
          },
          onLocationsClick = {
            onEvent(HomeEvent.LocationsClick)
          },
          onEpisodesClick = {
            onEvent(HomeEvent.EpisodesClick)
          }
        )
      }
    }
  )
}