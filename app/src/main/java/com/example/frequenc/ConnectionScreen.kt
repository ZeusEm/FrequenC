package com.example.frequenc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ConnectionScreen(
    viewModel: ConnectionViewModel = hiltViewModel(),
    onConnected: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state.connectionState) {
            ConnectionState.CONNECTING -> {
                CircularProgressIndicator()
                Text("Connecting to device...")
            }
            ConnectionState.CONNECTED -> {
                Icon(Icons.Default.CheckCircle, "Connected", tint = Color.Green)
                Button(onClick = onConnected) { Text("Proceed to Tuning") }
            }
            ConnectionState.ERROR -> {
                Icon(Icons.Default.Error, "Error", tint = Color.Red)
                Text(state.errorMessage ?: "Connection failed")
                Button(onClick = viewModel::retry) { Text("Retry") }
            }
        }
    }
}