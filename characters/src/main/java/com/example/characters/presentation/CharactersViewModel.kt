package com.example.characters.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characters.domain.CharacterDomain
import com.example.characters.domain.usecases.GetDBCharactersUseCase
import com.example.characters.domain.usecases.RefreshCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val refreshCharactersUseCase: RefreshCharactersUseCase,
    private val getDBCharactersUseCase: GetDBCharactersUseCase,
) : ViewModel() {

    val characters = MutableLiveData<List<CharacterDomain>>()
    val errorMessage = MutableLiveData<String>()

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                refreshCharactersUseCase.execute()
                characters.postValue(getDBCharactersUseCase.execute())

            } catch (e: Exception) {
                errorMessage.postValue("refresh data from repository " + e.toString())

            }
        }
    }
}