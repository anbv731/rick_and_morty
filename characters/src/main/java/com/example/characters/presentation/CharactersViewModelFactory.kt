package com.example.characters.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.characters.data.CharactersRepositoryImpl
import com.example.characters.domain.usecases.GetDBCharactersUseCase
import com.example.characters.domain.usecases.RefreshCharactersUseCase

class CharactersViewModelFactory constructor(
    context: Context,
) : ViewModelProvider.Factory {
    private val charactersRepository = CharactersRepositoryImpl(context)
    private val refreshCharactersUseCase = RefreshCharactersUseCase(charactersRepository)
    private val getDBChratctersUseCase = GetDBCharactersUseCase(charactersRepository)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            CharactersViewModel(refreshCharactersUseCase, getDBChratctersUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}

