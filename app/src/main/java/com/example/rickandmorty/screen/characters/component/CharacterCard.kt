package com.example.rickandmorty.screen.characters.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun CharacterCard(
  imageUrl: String,
  modifier: Modifier = Modifier,
) {
  Card(modifier = modifier) {
    Row {
      AsyncImage(
        model = imageUrl,
        contentDescription = null,
      )
    }
  }
}