package com.example.rickandmorty.ui.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.rickandmorty.common.ComposeViewModel
import com.example.rickandmorty.data.ErrorResponse
import com.example.rickandmorty.data.characters.CharactersRepository
import com.example.rickandmorty.navigation.Navigator
import com.example.rickandmorty.navigation.Screen
import com.example.rickandmorty.ui.mapper.CharacterUiMapper
import com.example.rickandmorty.utils.ErrorMessageMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
  private val navigator: Navigator,
  private val charactersRepository: CharactersRepository,
  private val characterUiMapper: CharacterUiMapper,
  private val errorMessageMapper: ErrorMessageMapper,
) : ComposeViewModel<CharactersState, CharactersEvent>() {
  private var charactersRes by mutableStateOf<Either<ErrorResponse, CharactersUi>?>(null)
  private var loadingNewCharacters by mutableStateOf(false)
  private var charactersPage by mutableIntStateOf(1)

  @Composable
  override fun uiState(): CharactersState {
    LaunchedEffect(Unit) {
      fetchCharacters()
    }

    return when (val characters = charactersRes) {
      null -> CharactersState.Loading
      is Either.Left<ErrorResponse> -> CharactersState.Error(
        message = errorMessageMapper.transformErrorToMessage(characters.value)
      )

      is Either.Right<CharactersUi> -> CharactersState.Success(
        characters = characters.value,
        loadingNewCharacters = loadingNewCharacters,
      )
    }
  }

  override fun onEvent(event: CharactersEvent) {
    when (event) {
      CharactersEvent.BackClick -> handleBackClick()
      is CharactersEvent.CharacterClick -> handleCharacterClick(event)
      is CharactersEvent.LocationClick -> handleLocationClick(event)
      CharactersEvent.LoadNewPage -> handleLoadNewPage()
    }
  }

  private fun handleBackClick() {
    viewModelScope.launch {
      navigator.back()
    }
  }

  private fun handleCharacterClick(event: CharactersEvent.CharacterClick) {
    viewModelScope.launch {
      navigator.navigateTo(Screen.Character(event.id))
    }
  }

  private fun handleLocationClick(event: CharactersEvent.LocationClick) {
    // TODO
  }

  private fun fetchCharacters() {
    viewModelScope.launch {
      val prevCharsRes = charactersRes
      charactersRes = charactersRepository.fetchCharacters(page = charactersPage)
        .map { characters ->
          CharactersUi(
            info = CharactersInfoUi(
              pages = characters.info.pages,
              next = characters.info.next,
            ),
            results = if (prevCharsRes is Either.Right) {
              (prevCharsRes.value.results + characters.results.map { character ->
                characterUiMapper.map(character)
              })
            } else {
              characters.results.map { character ->
                characterUiMapper.map(character)
              }
            }.toImmutableList()
          )
        }
    }
  }

  private fun handleLoadNewPage() {
    charactersPage += 1
    val charsRes = charactersRes
    if (charsRes is Either.Right<CharactersUi> && charactersPage <= charsRes.value.info.pages) {
      loadingNewCharacters = true
      fetchCharacters()
      loadingNewCharacters = false
    }
  }
}