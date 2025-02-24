package com.example.frequenc.tuning

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TuningScreen(
    viewModel: TuningViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val isValidParams by viewModel.isValidParams

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { viewModel.sendTuningCommand() },
            enabled = isValidParams
        ) {
            Text("Tune Frequency")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ Animated Success Message
        AnimatedVisibility(visible = uiState.showSuccess) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Green.copy(alpha = 0.1f)), // ✅ Fixed color
                border = BorderStroke(1.dp, Color.Green),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "✓ Tuning Successful",
                    color = Color.Green,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // ✅ Animated Error Message
        AnimatedVisibility(visible = uiState.showError) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.1f)), // ✅ Fixed color
                border = BorderStroke(1.dp, Color.Red),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "✗ ${uiState.message.orEmpty()}",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // ✅ Loading Indicator
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
    }
}