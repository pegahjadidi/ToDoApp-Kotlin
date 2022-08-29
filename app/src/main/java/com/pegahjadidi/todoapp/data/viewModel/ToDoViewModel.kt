package com.pegahjadidi.todoapp.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pegahjadidi.todoapp.data.ToDoDataBase
import com.pegahjadidi.todoapp.data.model.ToDoData
import com.pegahjadidi.todoapp.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {
    private val toDoDao = ToDoDataBase.getDataBase(application).toDoDao()
    private val repository : ToDoRepository = ToDoRepository(toDoDao)
    val sortByHighPriority : LiveData<List<ToDoData>> = repository.sortByHighPriority
    val sortByLowPriority : LiveData<List<ToDoData>> = repository.sortByLowPriority
    val getAllData : LiveData<List<ToDoData>> = repository.getAllData

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(toDoData)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }

    fun searchDataBase(searchQuery: String): LiveData<List<ToDoData>> {
        return repository.searchDataBase(searchQuery)
    }

}