package com.ebunnygroup.epara.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun ScreenContent(screenName: String, previousScreen: String?, onNextScreenClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen: $screenName")
        if (previousScreen != null) {
            Text(text = "Previous screen: $previousScreen")
        }
        Button(onClick = onNextScreenClick) {
            Text(text = "Go to next screen")
        }
    }
}