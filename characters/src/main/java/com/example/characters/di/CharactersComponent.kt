package com.example.characters.di

import android.content.Context
import com.example.characters.domain.usecases.GetDBCharactersUseCase
import com.example.characters.presentation.CharactersFragment
import com.example.characters.presentation.CharactersViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [CharactersDomainModule::class,CharactersDataModule::class])
public interface CharactersComponent {

   @Component.Factory
   public interface Factory {
       fun create(@BindsInstance context: Context): CharactersComponent
   }

    fun inject(target: CharactersFragment)
}