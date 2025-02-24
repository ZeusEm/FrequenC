package com.example.frequenc

sealed class ConnectionState {
    object CONNECTING : ConnectionState()
    object CONNECTED : ConnectionState()
    object ERROR : ConnectionState()
}