package com.example.rickandmorty.data.characters

import arrow.core.Either
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class CharactersDataSource @Inject constructor(
  private val httpClient: HttpClient
) {
  suspend fun fetchCharacters(page: Int?): Either<Throwable, CharactersDto> {
    return Either.catch {
      httpClient.get("character") {
        if (page != null) {
          parameter("page", page)
        }
      }.body<CharactersDto>()
    }
  }

  suspend fun fetchCharacter(id: Int): Either<Throwable, CharacterDto> {
    return Either.catch {
      httpClient.get("character/$id").body<CharacterDto>()
    }
  }
}