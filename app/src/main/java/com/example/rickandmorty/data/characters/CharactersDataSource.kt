package com.example.rickandmorty.data.characters

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class CharactersDataSource @Inject constructor(
  private val httpClient: HttpClient
) {
  suspend fun fetchCharacters(page: Int?) {
    try {
      httpClient.get(urlString = "character") {
        if (page != null) {
          parameter("page", page)
        }
      }
    } catch (e: Exception) {

    }
  }

  suspend fun fetchCharacter(id: String) {
    try {
      httpClient.get(urlString = "character/$id")
    } catch (e: Exception) {

    }
  }
}