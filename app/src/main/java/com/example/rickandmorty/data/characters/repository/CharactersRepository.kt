package com.example.rickandmorty.data.characters.repository

import arrow.core.Either
import arrow.core.right
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
      .mapLeft(errorMapper::map)
      .map { charactersDto ->
        Characters(
          info = CharactersInfo(
            pages = charactersDto.info.pages,
            next = charactersDto.info.next
          ),
          results = charactersDto.results.map { characterDto ->
            characterMapper.map(characterDto)
              .also(::cacheCharacter)
          }
        )
      }
  }

  suspend fun fetchCharacter(id: Int): Either<ErrorResponse, Character> {
    return characterMap[id]?.right()
      ?: charactersDataSource.fetchCharacter(id)
        .mapLeft(errorMapper::map)
        .map(characterMapper::map)
        .onRight(::cacheCharacter)
  }

  private fun cacheCharacter(character: Character) {
    characterMap[character.id] = character
  }
}