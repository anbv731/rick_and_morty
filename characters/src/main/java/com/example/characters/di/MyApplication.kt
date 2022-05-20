package com.example.characters.di

import android.app.Application
import com.example.characters.di.CharactersComponent
import com.example.characters.di.DaggerCharactersComponent

public open class MyApplication : Application() {
    val charactersComponent: CharactersComponent by lazy {
        DaggerCharactersComponent.factory().create(applicationContext)
    }

//        val appComponent: CharactersComponent by lazy {
//            // Creates an instance of AppComponent using its Factory constructor
//            // We pass the applicationContext that will be used as Context in the graph
//            DaggerChara.factory().create(applicationContext)
//        }
//
//        open val userManager by lazy {
//            UserManager(SharedPreferencesStorage(this))
//        }
//    }
}