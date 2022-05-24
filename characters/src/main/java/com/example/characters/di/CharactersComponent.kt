package com.example.characters.di

import android.content.Context
import com.example.characters.domain.usecases.GetDBCharactersUseCase
import com.example.characters.presentation.CharactersDetailFragment
import com.example.characters.presentation.CharactersFragment
import com.example.characters.presentation.CharactersViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton


@Subcomponent(modules = [CharactersDomainModule::class])
 interface CharactersComponent {

 @Subcomponent.Factory
   interface Factory {
       fun create(@BindsInstance context: Context): CharactersComponent
   }

    fun injectCharactersFragment(target: CharactersFragment)
    fun injectCharactersDetailFragment(target:CharactersDetailFragment)
}