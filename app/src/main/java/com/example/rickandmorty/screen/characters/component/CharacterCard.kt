package com.example.rickandmorty.screen.characters.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.rickandmorty.screen.characters.CharacterStatusUi
import com.example.rickandmorty.screen.characters.CharacterUi

@Composable
fun CharacterCard(
  character: CharacterUi,
  modifier: Modifier = Modifier,
  onLocationClick: (String) -> Unit,
) {
  Card(modifier = modifier) {
    Row {
      AsyncImage(
        modifier = Modifier.weight(1f),
        model = character.image,
        contentDescription = null,
      )
      Column(modifier = Modifier.weight(2f)) {
        CharacterName(text = character.name)
        Spacer(Modifier.height(8.dp))
        CharacterStatus(
          status = character.status,
          species = character.species,
        )
        Spacer(Modifier.height(24.dp))
        CharacterInfo(
          title = "Last known location",
          value = character.location.name,
          onValueClick = {
            onLocationClick(character.location.url)
          }
        )
      }
    }
  }
}

@Composable
private fun CharacterName(
  text: String,
  modifier: Modifier = Modifier,
) {
  Text(
    modifier = modifier,
    text = text,
    style = MaterialTheme.typography.titleLarge,
  )
}

@Composable
private fun CharacterStatus(
  status: CharacterStatusUi,
  species: String,
  modifier: Modifier = Modifier,
) {
  val statusText = when (status) {
    CharacterStatusUi.Alive -> "Alive"
    CharacterStatusUi.Dead -> "Dead"
  }
  Row(modifier = modifier) {
    CharacterStatusDot(status = status)
    Text(
      modifier = Modifier,
      text = "$statusText - $species",
      style = MaterialTheme.typography.bodyMedium,
    )
  }
}

@Composable
private fun CharacterStatusDot(
  status: CharacterStatusUi,
  modifier: Modifier = Modifier
) {
  Spacer(
    modifier = modifier
      .size(6.dp)
      .background(
        color = when (status) {
          CharacterStatusUi.Alive -> Color.Green
          CharacterStatusUi.Dead -> Color.Red
        },
        shape = CircleShape,
      )
  )
}

@Composable
private fun ColumnScope.CharacterInfo(
  title: String,
  value: String,
  valueUrl: String? = null,
  onValueClick: ((String) -> Unit)? = null,
) {
  Text(
    text = title,
    style = MaterialTheme.typography.bodyMedium.copy(
      color = MaterialTheme.colorScheme.secondary,
    ),
  )
  Spacer(Modifier.height(12.dp))
  Text(
    modifier = Modifier.then(
      if (valueUrl != null && onValueClick != null) {
        Modifier.clickable(
          onClick = {
            onValueClick(valueUrl)
          }
        )
      } else {
        Modifier
      }
    ),
    text = value,
    style = MaterialTheme.typography.bodyLarge,
  )
}