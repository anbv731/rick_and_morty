package com.example.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.CharacterDomain
import com.example.domain.usecases.GetCharactersUseCase

class CharactersViewModel constructor(private  val getCharactersUseCase:GetCharactersUseCase):ViewModel(){
    val characters= MutableLiveData<List<CharacterDomain>>()



}