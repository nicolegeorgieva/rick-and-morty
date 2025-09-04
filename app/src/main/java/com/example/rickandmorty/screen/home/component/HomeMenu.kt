package com.example.rickandmorty.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

@Composable
fun HomeMenu(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
      .background(
        color = MaterialTheme.colorScheme.primary,
        shape = MaterialTheme.shapes.medium,
      )
      .border(
        width = 2.dp,
        color = DividerDefaults.color,
        shape = MaterialTheme.shapes.medium,
      ),
  ) {
    MenuOption(
      name = stringResource(R.string.home_characters),
      type = OptionType.First,
      onClick = {}
    )
    HorizontalDivider(thickness = 2.dp)
    MenuOption(
      name = stringResource(R.string.home_locations),
      type = OptionType.Middle,
      onClick = {}
    )
    HorizontalDivider(thickness = 2.dp)
    MenuOption(
      name = stringResource(R.string.home_episodes),
      type = OptionType.Last,
      onClick = {}
    )
  }
}

private enum class OptionType {
  First,
  Middle,
  Last,
}

@Composable
private fun MenuOption(
  name: String,
  type: OptionType,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Button(
    modifier = modifier,
    shape = when (type) {
      OptionType.First -> RoundedCornerShape(
        topStart = CornerSize(50),
        topEnd = CornerSize(50),
        bottomStart = CornerSize(0.dp),
        bottomEnd = CornerSize(0.dp),
      )

      OptionType.Middle -> RoundedCornerShape(
        topStart = CornerSize(0.dp),
        topEnd = CornerSize(0.dp),
        bottomStart = CornerSize(0.dp),
        bottomEnd = CornerSize(0.dp),
      )

      OptionType.Last -> RoundedCornerShape(
        topStart = CornerSize(0.dp),
        topEnd = CornerSize(0.dp),
        bottomStart = CornerSize(50),
        bottomEnd = CornerSize(50),
      )
    },
    onClick = onClick,
  ) {
    Text(text = name)
  }
}