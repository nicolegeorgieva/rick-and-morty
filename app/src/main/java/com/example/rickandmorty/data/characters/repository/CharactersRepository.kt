package com.example.rickandmorty.data.characters.repository

import arrow.core.Either
import com.example.rickandmorty.data.ErrorMapper
import com.example.rickandmorty.data.ErrorResponse
import com.example.rickandmorty.data.characters.datasource.CharactersDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRepository @Inject constructor(
  private val charactersDataSource: CharactersDataSource,
  private val errorMapper: ErrorMapper,
  private val characterMapper: CharacterMapper,
) {
  private val characterMap = mutableMapOf<Int, Character>()

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
            val characterDtoToDomain = characterMapper.map(characterDto)
            characterMap[characterDto.id] = characterDtoToDomain
            characterDtoToDomain
          }
        )
      }
  }

  suspend fun fetchCharacter(id: Int): Either<ErrorResponse, Character> {
    val character = characterMap[id]
    return if (character != null) {
      Either.Right(character)
    } else {
      charactersDataSource.fetchCharacter(id)
        .mapLeft(errorMapper::mapError)
        .map { characterDto ->
          characterMapper.map(characterDto)
        }
    }
  }
}