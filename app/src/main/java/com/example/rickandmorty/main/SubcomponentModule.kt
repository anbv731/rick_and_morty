package com.example.rickandmorty.main

import com.example.characters.di.CharactersComponent
import dagger.Module

@Module(subcomponents = [CharactersComponent::class])
class SubcomponentsModule {}