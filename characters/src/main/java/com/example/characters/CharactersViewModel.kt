package com.example.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.CharacterDomain
import com.example.domain.usecases.GetDBCharactersUseCase
import com.example.domain.usecases.RefreshCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersViewModel constructor(
    private val refreshCharactersUseCase: RefreshCharactersUseCase,
    private val getDBCharactersUseCase: GetDBCharactersUseCase
) : ViewModel() {
    val characters = MutableLiveData<List<CharacterDomain>>()
    val errorMessage = MutableLiveData<String>()

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                refreshCharactersUseCase.execute()
//                characters.postValue()

            } catch (e: Exception) {
                errorMessage.postValue("refresh data from repository " + e.toString())
            }
        }
    }

    fun getDBCharacters() {
        viewModelScope.launch(Dispatchers.IO) {  try {
            characters.postValue(getDBCharactersUseCase.execute())

        } catch (e: Exception) {
            errorMessage.postValue("getDBCharacter " + e.toString())
        }
        }


    }

//    private fun refreshDataFromRepository() {
//        viewModelScope.launch {
//            try {
//                videosRepository.refreshVideos()
//                _eventNetworkError.value = false
//                _isNetworkErrorShown.value = false
//
//            } catch (networkError: IOException) {
//                // Show a Toast error message and hide the progress bar.
//                if(playlist.value.isNullOrEmpty())
//                    _eventNetworkError.value = true
//            }
//        }
//    }

}