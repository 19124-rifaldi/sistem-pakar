package com.example.sistempakarikannila.di

import com.example.sistempakarikannila.model.repository.GejalaRepository
import com.example.sistempakarikannila.model.repository.GejalaRepositoryImpl
import com.example.sistempakarikannila.model.repository.PenyakitRepository
import com.example.sistempakarikannila.model.repository.PenyakitRepositoryImpl
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ModuleApp {
    @Provides
    @Singleton
    fun provideFirebaseRealtime(): FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }
    @Provides
    @Singleton
    fun provideRepositoryDisease(firebaseDatabase: FirebaseDatabase):PenyakitRepository{
        return PenyakitRepositoryImpl(firebaseDatabase)
    }
    @Provides
    @Singleton
    fun provideRepositorySymptom(firebaseDatabase: FirebaseDatabase):GejalaRepository{
        return GejalaRepositoryImpl(firebaseDatabase)
    }
}