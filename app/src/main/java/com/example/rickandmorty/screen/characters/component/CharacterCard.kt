package com.example.rickandmorty.screen.characters.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.screen.characters.CharacterStatusUi
import com.example.rickandmorty.screen.characters.CharacterUi

@Composable
fun CharacterCard(
  character: CharacterUi,
  modifier: Modifier = Modifier,
  onLocationClick: (String) -> Unit,
) {
  Card(modifier = modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      CharacterImage(url = character.image)
      Spacer(Modifier.width(16.dp))
      Column(
        modifier = Modifier
          .weight(2f)
          .padding(vertical = 12.dp)
      ) {
        CharacterName(text = character.name)
        Spacer(Modifier.height(4.dp))
        CharacterStatus(
          status = character.status,
          species = character.species,
        )
        Spacer(Modifier.height(24.dp))
        CharacterInfo(
          title = stringResource(R.string.characters_last_known_location),
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
private fun RowScope.CharacterImage(
  url: String,
  modifier: Modifier = Modifier,
) {
  AsyncImage(
    modifier = modifier
      .weight(1.5f)
      .aspectRatio(0.8f),
    contentScale = ContentScale.FillHeight,
    model = url,
    contentDescription = null,
  )
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
  val statusText = stringResource(
    when (status) {
      CharacterStatusUi.Alive -> R.string.characters_status_alive
      CharacterStatusUi.Dead -> R.string.characters_status_dead
    }
  )
  val characterSpecies = stringResource(
    when (species) {
      "Human" -> R.string.characters_type_human
      "Animal" -> R.string.characters_type_animal
      "Alien" -> R.string.characters_type_alien
      else -> R.string.characters_type_other
    }
  )

  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    CharacterStatusDot(status = status)
    Spacer(Modifier.width(4.dp))
    Text(
      modifier = Modifier,
      text = "$statusText - $characterSpecies",
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
  Spacer(Modifier.height(4.dp))
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .then(
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