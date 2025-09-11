package com.example.rickandmorty.screen.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.rickandmorty.ComposeViewModel
import com.example.rickandmorty.data.ErrorResponse
import com.example.rickandmorty.data.characters.CharactersRepository
import com.example.rickandmorty.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
  private val navigator: Navigator,
  private val charactersRepository: CharactersRepository,
) : ComposeViewModel<CharactersState, CharactersEvent>() {
  private var charactersRes by mutableStateOf<Either<ErrorResponse, CharactersUi>?>(null)
  private var charactersPage by mutableStateOf<Int?>(null)

  @Composable
  override fun uiState(): CharactersState {
    LaunchedEffect(Unit) {
      fetchCharacters()
    }

    return when (val characters = charactersRes) {
      null -> CharactersState.Loading
      is Either.Left<ErrorResponse> -> CharactersState.Error(message = characters.value)
      is Either.Right<CharactersUi> -> CharactersState.Success(characters.value)
    }
  }

  override fun onEvent(event: CharactersEvent) {
    when (event) {
      CharactersEvent.BackClick -> handleBackClick()
    }
  }

  private fun handleBackClick() {
    viewModelScope.launch {
      navigator.back()
    }
  }

  private fun fetchCharacters() {
    viewModelScope.launch {
      charactersRes = charactersRepository.fetchCharacters(page = charactersPage)
        .map { characters ->
          CharactersUi(
            info = CharactersInfoUi(pages = characters.info.pages),
            results = characters.results.map { character ->
              CharacterUi(
                id = character.id,
                name = character.name,
                status = character.status,
                species = character.species,
                type = character.type,
                gender = character.gender,
                origin = OriginUi(
                  name = character.origin.name,
                  url = character.origin.url,
                ),
                location = LocationUi(
                  name = character.location.name,
                  url = character.location.url,
                ),
                image = character.image,
                episode = character.episode.toImmutableList(),
                created = ""
              )
            },
          )
        }
    }
  }
}