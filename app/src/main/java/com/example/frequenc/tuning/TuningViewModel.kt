package com.example.frequenc.tuning

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frequenc.serial.SerialManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// Data class for tuning parameters
data class FrequencyParams(
    val startFreq: Float = 100.0f,
    val stopFreq: Float = 200.0f
)

// UI state for tuning screen
data class TuningState(
    val isLoading: Boolean = false,
    val showSuccess: Boolean = false,
    val showError: Boolean = false,
    val message: String = ""
)

@HiltViewModel
class TuningViewModel @Inject constructor(
    private val serialManager: SerialManager
) : ViewModel() {

    // Compose state holding the frequency parameters.
    private val _frequencyParams = mutableStateOf(FrequencyParams())
    val frequencyParams: State<FrequencyParams> = _frequencyParams

    // Compose state holding the UI state.
    private val _uiState = mutableStateOf(TuningState())
    val uiState: State<TuningState> = _uiState

    // Real-time validation using derived state.
    val isValidParams = derivedStateOf {
        _frequencyParams.value.startFreq in 1000f..5000f &&
                _frequencyParams.value.stopFreq > _frequencyParams.value.startFreq
    }

    fun updateStartFreq(value: Float) {
        _frequencyParams.value = _frequencyParams.value.copy(startFreq = value)
    }

    fun updateStopFreq(value: Float) {
        _frequencyParams.value = _frequencyParams.value.copy(stopFreq = value)
    }

    fun sendTuningCommand() {
        viewModelScope.launch {
            if (!isValidParams.value) {
                _uiState.value = TuningState(showError = true, message = "Invalid parameters")
                return@launch
            }
            _uiState.value = _uiState.value.copy(isLoading = true)
            val command = buildCommand()
            // Use the built-in Kotlin Result API
            val result = serialManager.sendCommand(command)
            if (result.isSuccess) {
                _uiState.value = TuningState(showSuccess = true, message = "Frequency tuned successfully!")
            } else {
                _uiState.value = TuningState(showError = true, message = "Failed to send command")
            }
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    private fun buildCommand() =
        "AF B ${_frequencyParams.value.startFreq} ${_frequencyParams.value.stopFreq} v"
}