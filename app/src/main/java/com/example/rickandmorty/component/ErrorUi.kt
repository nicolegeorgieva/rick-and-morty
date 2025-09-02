package com.example.rickandmorty.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

@Composable
fun ErrorUi(
  isLoading: Boolean,
  modifier: Modifier = Modifier,
  onRetry: () -> Unit,
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(all = 24.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      text = stringResource(R.string.error_title),
      color = MaterialTheme.colorScheme.error,
      style = MaterialTheme.typography.headlineLarge,
    )
    Spacer(Modifier.height(12.dp))
    Button(
      onClick = onRetry,
      enabled = !isLoading,
    ) {
      if (isLoading) {
        CircularProgressIndicator(
          modifier = Modifier.size(24.dp),
          color = MaterialTheme.colorScheme.onPrimary,
          strokeWidth = 2.dp
        )
      } else {
        Text(text = stringResource(R.string.error_retry))
      }
    }
  }
}