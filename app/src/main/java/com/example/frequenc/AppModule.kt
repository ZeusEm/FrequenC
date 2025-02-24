package com.example.frequenc.di

import android.content.Context
import com.example.frequenc.serial.SerialManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    @ViewModelScoped
    fun provideSerialManager(@ApplicationContext context: Context): SerialManager {
        return SerialManager(context)
    }
}