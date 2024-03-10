package com.example.myapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.model.Todo
import com.example.myapp.model.TodosApi
import kotlinx.coroutines.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TodoViewModel : ViewModel() {
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        getTodosList()
    }

    private fun getTodosList() {
        viewModelScope.launch {
            var todosApi: TodosApi? = null
            try {
                todosApi = TodosApi.getInstance()
                _todos.value = todosApi.getTodos() // Update the LiveData
            } catch (e: Exception) {
                Log.e("TODOVIEWMODEL", e.message.toString())
            }
        }
    }
}