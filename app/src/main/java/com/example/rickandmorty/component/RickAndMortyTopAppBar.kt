package com.example.rickandmorty.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyTopAppBar(
  text: String,
  modifier: Modifier = Modifier,
  navigateBack: Boolean = false,
  onBackClick: (() -> Unit)? = null,
) {
  CenterAlignedTopAppBar(
    title = {
      Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Black,
        style = MaterialTheme.typography.titleLarge,
      )
    },
    navigationIcon = if (navigateBack && onBackClick != null) {
      { BackButton(onBackClick = onBackClick) }
    } else {
      {}
    }
  )
}

@Composable
private fun BackButton(
  modifier: Modifier = Modifier,
  onBackClick: () -> Unit,
) {
  IconButton(
    modifier = modifier,
    onClick = onBackClick,
  ) {
    Icon(
      imageVector = Icons.Filled.ArrowBack,
      contentDescription = null,
    )
  }
}