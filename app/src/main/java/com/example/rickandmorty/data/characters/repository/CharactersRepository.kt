package com.example.rickandmorty.data.characters.repository

import arrow.core.Either
import com.example.rickandmorty.data.ErrorMapper
import com.example.rickandmorty.data.ErrorResponse
import com.example.rickandmorty.data.characters.datasource.CharactersDataSource
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class CharactersRepository @Inject constructor(
  private val charactersDataSource: CharactersDataSource,
  private val errorMapper: ErrorMapper,
  private val characterMapper: CharacterMapper,
) {
  @OptIn(ExperimentalTime::class)
  suspend fun fetchCharacters(page: Int?): Either<ErrorResponse, Characters> {
    return charactersDataSource.fetchCharacters(page)
      .mapLeft(errorMapper::mapError)
      .map { charactersDto ->
        Characters(
          info = CharactersInfo(
            pages = charactersDto.info.pages,
            next = charactersDto.info.next
          ),
          results = charactersDto.results.map { characterDto ->
            characterMapper.map(characterDto)
          }
        )
      }
  }

  @OptIn(ExperimentalTime::class)
  suspend fun fetchCharacter(id: Int): Either<ErrorResponse, Character> {
    return charactersDataSource.fetchCharacter(id)
      .mapLeft(errorMapper::mapError)
      .map { characterDto ->
        characterMapper.map(characterDto)
      }
  }
}