package com.example.characters

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.CharactersRepositoryImpl
import com.example.domain.usecases.GetCharactersUseCase

class CharactersViewModelFactory constructor(
    context: Context,
) : ViewModelProvider.Factory {
    private val charactersRepository = CharactersRepositoryImpl(context)
    private val getChratctersUseCase = GetCharactersUseCase(charactersRepository)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            CharactersViewModel(getChratctersUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
