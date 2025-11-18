package com.example.rickandmorty.ui.characters.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Loading(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
  ) {
    LinearProgressIndicator()
  }
}