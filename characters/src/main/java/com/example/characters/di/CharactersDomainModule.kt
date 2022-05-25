package com.example.characters.di

import com.example.characters.data.CharactersRepositoryImpl
import com.example.characters.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module

@Module
abstract class CharactersDomainModule {
    @Binds
    abstract fun getCharactersRepository(repository: CharactersRepositoryImpl): CharactersRepository

}