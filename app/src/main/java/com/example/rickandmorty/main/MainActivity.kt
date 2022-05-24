package com.example.rickandmorty.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.characters.presentation.CharactersDetailFragment
import com.example.characters.presentation.CharactersFragment
import com.example.rickandmorty.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CharactersDetailFragment(), null)
            .commit()
    }
}
