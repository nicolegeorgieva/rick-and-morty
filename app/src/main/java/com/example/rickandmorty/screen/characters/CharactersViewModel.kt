package com.example.rickandmorty.screen.characters

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
import com.example.rickandmorty.data.characters.Character
import com.example.rickandmorty.data.characters.CharactersRepository
import com.example.rickandmorty.navigation.Navigator
import com.example.rickandmorty.utils.ErrorMessageMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
  private val navigator: Navigator,
  private val charactersRepository: CharactersRepository,
  private val errorMessageMapper: ErrorMessageMapper,
) : ComposeViewModel<CharactersState, CharactersEvent>() {
  private var charactersRes by mutableStateOf<Either<ErrorResponse, CharactersUi>?>(null)
  private var characterRes by mutableStateOf<Either<ErrorResponse, CharacterUi>?>(null)
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
      characterRes = charactersRepository.fetchCharacter(event.id)
        .map { character ->
          character.toCharacterUi()
        }
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
                character.toCharacterUi()
              })
            } else {
              characters.results.map { character ->
                character.toCharacterUi()
              }
            }.toImmutableList()
          )
        }
    }
  }

  private fun Character.toCharacterUi(): CharacterUi {
    return CharacterUi(
      id = this.id,
      name = this.name,
      status = when (this.status) {
        "Alive" -> CharacterStatusUi.Alive
        "Dead" -> CharacterStatusUi.Dead
        else -> CharacterStatusUi.Unknown
      },
      species = when (this.species) {
        "Human" -> CharacterSpeciesUi.Human
        "Animal" -> CharacterSpeciesUi.Animal
        "Alien" -> CharacterSpeciesUi.Alien
        else -> CharacterSpeciesUi.Other
      },
      type = this.type,
      gender = when (this.gender) {
        "Female" -> CharacterGenderUi.Female
        "Male" -> CharacterGenderUi.Male
        "Genderless" -> CharacterGenderUi.Genderless
        else -> CharacterGenderUi.Unknown
      },
      origin = OriginUi(
        name = this.origin.name,
        url = this.origin.url,
      ),
      location = LocationUi(
        name = this.location.name,
        url = this.location.url,
      ),
      image = this.image,
      episode = this.episode.toImmutableList(),
      created = ""
    )
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