package com.example.rickandmorty.utils

import com.example.rickandmorty.R
import com.example.rickandmorty.common.ResourceProvider
import com.example.rickandmorty.data.ErrorResponse
import javax.inject.Inject

class ErrorMessageMapper @Inject constructor(
  private val resourceProvider: ResourceProvider,
) {
  fun transformErrorToMessage(error: ErrorResponse): String {
    return when (error) {
      ErrorResponse.NoInternet -> resourceProvider.getString(R.string.error_message_no_internet)
      ErrorResponse.Other -> resourceProvider.getString(R.string.error_message_other)
    }
  }
}