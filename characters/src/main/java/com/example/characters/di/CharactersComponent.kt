package com.example.characters.di

import android.content.Context
import com.example.characters.presentation.CharactersDetailFragment
import com.example.characters.presentation.CharactersFragment
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [CharactersDomainModule::class])
interface CharactersComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CharactersComponent
    }

    fun injectCharactersFragment(target: CharactersFragment)
    fun injectCharactersDetailFragment(target: CharactersDetailFragment)
}