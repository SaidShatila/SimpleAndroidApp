package com.example.simpleandroidapp.ui.fragments.listview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.simpleandroidapp.data.remote.response.Todos
import com.example.simpleandroidapp.network.AppRepository
import com.example.simpleandroidapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewScreenViewModel @Inject constructor(
    private val appRepository: AppRepository,
    application: Application
) :
    AndroidViewModel(application) {
    val getTodosList: MutableLiveData<NetworkResult<List<Todos>>?> = MutableLiveData()


    fun fetchTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.fetchTodos().collect { value ->
                getTodosList.postValue(value)
            }
        }
    }
}

