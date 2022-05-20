package com.example.characters.di

import com.example.characters.data.CharactersRepositoryImpl
import com.example.characters.domain.repository.CharactersRepository
import com.example.characters.domain.usecases.GetDBCharactersUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CharactersDomainModule {
    @Binds
    abstract fun getCharactersRepository(repository: CharactersRepositoryImpl):CharactersRepository

}