package com.example.rickandmorty.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R

@Composable
fun HomeScreen() {
  val viewModel: HomeViewModel = hiltViewModel()

  HomeUi(
    uiState = viewModel.uiState(),
    onEvent = viewModel::onEvent,
  )
}

@Composable
fun HomeUi(
  uiState: HomeState,
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
        HomeMenu()
      }
    }
  )
}

@Composable
private fun HomeTitle(modifier: Modifier = Modifier) {
  Text(
    modifier = modifier,
    text = stringResource(R.string.home_title),
    fontWeight = FontWeight.Black,
    textAlign = TextAlign.Center,
    style = MaterialTheme.typography.headlineLarge,
  )
}

@Composable
private fun HomeMenu(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
      .background(
        color = MaterialTheme.colorScheme.primary,
        shape = MaterialTheme.shapes.medium,
      )
      .border(
        width = 2.dp,
        color = DividerDefaults.color,
        shape = MaterialTheme.shapes.medium,
      ),
  ) {
    MenuOption(
      name = stringResource(R.string.home_characters),
      onClick = {}
    )
    HorizontalDivider(thickness = 2.dp)
    MenuOption(
      name = stringResource(R.string.home_locations),
      onClick = {}
    )
    HorizontalDivider(thickness = 2.dp)
    MenuOption(
      name = stringResource(R.string.home_episodes),
      onClick = {}
    )
  }
}

@Composable
private fun MenuOption(
  name: String,
  onClick: () -> Unit,
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(
        onClick = onClick
      )
      .padding(top = 12.dp, bottom = 12.dp),
    text = name,
    textAlign = TextAlign.Center,
    color = MaterialTheme.colorScheme.onPrimary,
    style = MaterialTheme.typography.bodyLarge,
  )
}