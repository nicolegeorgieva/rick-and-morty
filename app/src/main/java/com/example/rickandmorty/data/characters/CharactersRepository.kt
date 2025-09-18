package com.example.rickandmorty.data.characters

import arrow.core.Either
import com.example.rickandmorty.data.ErrorMapper
import com.example.rickandmorty.data.ErrorResponse
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class CharactersRepository @Inject constructor(
  private val charactersDataSource: CharactersDataSource,
  private val errorMapper: ErrorMapper,
) {
  @OptIn(ExperimentalTime::class)
  suspend fun fetchCharacters(page: Int?): Either<ErrorResponse, Characters> {
    return charactersDataSource.fetchCharacters(page)
      .map { charactersDto ->
        Characters(
          info = CharactersInfo(
            pages = charactersDto.info.pages,
            next = charactersDto.info.next
          ),
          results = charactersDto.results.map { characterDto ->
            Character(
              id = characterDto.id,
              name = characterDto.name,
              status = characterDto.status,
              species = characterDto.species,
              type = characterDto.type,
              gender = characterDto.gender,
              origin = Origin(
                name = characterDto.origin.name,
                url = characterDto.origin.url,
              ),
              location = Location(
                name = characterDto.location.name,
                url = characterDto.location.url,
              ),
              image = characterDto.image,
              episode = characterDto.episode,
              created = characterDto.created,
            )
          }
        )
      }
      .mapLeft(errorMapper::mapError)
  }

  @OptIn(ExperimentalTime::class)
  suspend fun fetchCharacter(id: String): Either<ErrorResponse, Character> {
    return charactersDataSource.fetchCharacter(id)
      .map { characterDto ->
        Character(
          id = characterDto.id,
          name = characterDto.name,
          status = characterDto.status,
          species = characterDto.species,
          type = characterDto.type,
          gender = characterDto.gender,
          origin = Origin(
            name = characterDto.origin.name,
            url = characterDto.origin.url,
          ),
          location = Location(
            name = characterDto.location.name,
            url = characterDto.location.url,
          ),
          image = characterDto.image,
          episode = characterDto.episode,
          created = characterDto.created,
        )
      }
      .mapLeft(errorMapper::mapError)
  }
}