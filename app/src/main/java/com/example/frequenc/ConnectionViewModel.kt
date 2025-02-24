package com.example.frequenc

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ConnectionViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ConnectionUiState())
    val state: StateFlow<ConnectionUiState> = _state

    fun retry(){
        _state.value = ConnectionUiState(connectionState = ConnectionState.CONNECTING)
    }

    data class ConnectionUiState(
        val connectionState: ConnectionState = ConnectionState.ERROR,
        val errorMessage: String? = null
    )
}