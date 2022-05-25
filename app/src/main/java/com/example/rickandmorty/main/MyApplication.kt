package com.example.rickandmorty.main

import android.app.Application
import com.example.characters.di.CharactersComponent
import com.example.characters.di.CharactersComponentProvider

class MyApplication : Application(),CharactersComponentProvider {

  private val appComponent = DaggerApplicationComponent.create()
  override fun provideCharactersComponent(): CharactersComponent {
   return appComponent.charactersComponent().create(applicationContext)
  }
}

