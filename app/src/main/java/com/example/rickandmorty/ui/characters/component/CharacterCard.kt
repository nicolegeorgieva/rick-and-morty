package com.example.rickandmorty.ui.characters.component

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
import com.example.rickandmorty.ui.characters.CharacterGenderUi
import com.example.rickandmorty.ui.characters.CharacterSpeciesUi
import com.example.rickandmorty.ui.characters.CharacterStatusUi
import com.example.rickandmorty.ui.characters.CharacterUi

@Composable
fun CharacterCard(
  character: CharacterUi,
  modifier: Modifier = Modifier,
  onCharacterClick: (Int) -> Unit,
) {
  Card(
    modifier = modifier,
    onClick = {
      onCharacterClick(character.id)
    }
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      CharacterImage(url = character.image)
      CharacterInfo(character = character)
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
private fun RowScope.CharacterInfo(
  character: CharacterUi,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .weight(2f)
      .padding(16.dp)
  ) {
    CharacterName(text = character.name)
    Spacer(Modifier.height(4.dp))
    CharacterStatus(
      status = character.status,
      species = character.species,
    )
    Spacer(Modifier.height(24.dp))
    CharacterDetail(
      title = stringResource(R.string.characters_last_known_location),
      value = character.location.name,
    )
    Spacer(Modifier.height(24.dp))
    CharacterDetail(
      title = stringResource(R.string.characters_gender),
      value = stringResource(
        when (character.gender) {
          CharacterGenderUi.Female -> R.string.characters_gender_female
          CharacterGenderUi.Male -> R.string.characters_gender_male
          CharacterGenderUi.Genderless -> R.string.characters_gender_genderless
          CharacterGenderUi.Unknown -> R.string.characters_gender_unknown
        }
      ),
    )
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
  species: CharacterSpeciesUi,
  modifier: Modifier = Modifier,
) {
  val statusText = stringResource(
    when (status) {
      CharacterStatusUi.Alive -> R.string.characters_status_alive
      CharacterStatusUi.Dead -> R.string.characters_status_dead
      CharacterStatusUi.Unknown -> R.string.characters_status_unknown
    }
  )
  val characterSpecies = stringResource(
    when (species) {
      CharacterSpeciesUi.Human -> R.string.characters_species_human
      CharacterSpeciesUi.Animal -> R.string.characters_species_animal
      CharacterSpeciesUi.Alien -> R.string.characters_species_alien
      CharacterSpeciesUi.Other -> R.string.characters_species_other
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
          else -> MaterialTheme.colorScheme.secondary
        },
        shape = CircleShape,
      )
  )
}

@Composable
private fun ColumnScope.CharacterDetail(
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