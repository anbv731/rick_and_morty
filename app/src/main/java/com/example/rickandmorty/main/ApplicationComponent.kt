package com.example.rickandmorty.main

import com.example.characters.di.CharactersComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SubcomponentsModule::class])
interface ApplicationComponent {

    fun charactersComponent(): CharactersComponent.Factory
}