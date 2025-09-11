package com.example.rickandmorty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
  fun provideHttpClient(
    json: Json
  ): HttpClient {
    return HttpClient {
      install(ContentNegotiation) {
        json(
          json = json,
          contentType = ContentType.Any
        )
      }
      install(DefaultRequest) {
        url(urlString = "https://rickandmortyapi.com/api/")
        contentType(ContentType.Application.Json)
      }
    }
  }

  @Provides
  fun provideJson(): Json {
    return Json {
      ignoreUnknownKeys = true
      isLenient = true
    }
  }
}