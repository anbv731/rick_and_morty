package com.example.characters.presentation

//class CharactersViewModelFactory constructor(
//    context: Context,
//) : ViewModelProvider.Factory {
//    private val charactersRepository = CharactersRepositoryImpl(context)
//    private val refreshCharactersUseCase = RefreshCharactersUseCase(charactersRepository)
//    private val getDBChratctersUseCase = GetDBCharactersUseCase(charactersRepository)
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
//            CharactersViewModel(refreshCharactersUseCase, getDBChratctersUseCase) as T
//        } else {
//            throw IllegalArgumentException("ViewModel Not Found")
//        }
//    }
//
//}

