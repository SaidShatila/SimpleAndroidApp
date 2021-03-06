package com.example.simpleandroidapp.ui.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleandroidapp.data.datastore.PrefStoreImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val prefStoreImplementation: PrefStoreImplementation) :
    ViewModel() {

    fun saveState(state : Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            prefStoreImplementation.saveRememberMeState(state)
        }
    }
    fun saveLoginDate(date : String){
        viewModelScope.launch(Dispatchers.IO) {
            prefStoreImplementation.setUserDateLogin(date)
        }
    }
}