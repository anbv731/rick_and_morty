package com.example.rickandmorty.main

import android.app.Application
import android.content.Context
import com.example.characters.di.CharactersComponent
import com.example.characters.di.CharactersComponentProvider


class MyApplication : Application(),CharactersComponentProvider {

  val appComponent = DaggerApplicationComponent.create()
  override fun provideCharactersComponent(): CharactersComponent {
   return appComponent.charactersComponent().create(applicationContext)
  }

    init {
        instance = this
    }
    companion object {
        private var instance: MyApplication? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        val context: Context = MyApplication.applicationContext()
    }
//    val appComponent: CharactersComponent by lazy {
//        DaggerCharactersComponent.factory().create(this)
//    }
}


//
//        open val userManager by lazy {
//            UserManager(SharedPreferencesStorage(this))
//        }
//    }
