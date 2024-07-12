package com.example.todo.ui.screens

import androidx.lifecycle.ViewModel
import com.example.todo.ui.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class TooDoViewModel : ViewModel() {
    private val _newCard = MutableStateFlow(false)
    val newCard: StateFlow<Boolean> = _newCard.asStateFlow()
    private val _isEditin = MutableStateFlow(false)
    val isEditin: StateFlow<Boolean> = _isEditin.asStateFlow()

    fun Change(){
        _newCard.value = !_newCard.value
    }



    var tooDos = MutableStateFlow(mutableListOf(
        Todo(title = "Prova de arquitetura de computadores", description = null),
        Todo(title = "Casamento da vizinha", description = "Comprar o presente para o casamento da vizinha até o dia 10/02"),
        Todo(title = "Strabalho da faculdade", description = "realizar pesquisas doo jetpack compose"),
        Todo(title = "Sair com minha esposa", description = null),
        Todo(title = "Sair com o cachorro", description = null),
        Todo(title = "Arrumar o banheiro", description = null),
    )
    )

    fun addNewTooDo(title: String, description: String?){
        val newTodo = Todo(title = title, description = description)
        tooDos.value.add(newTodo)
    }

    fun deleteTooDo(todo: Todo) {
        val currentList = tooDos.value.toMutableList()
        currentList.remove(todo)
        println(currentList)
        tooDos.value = currentList
    }


    fun isOnEditin(){
        _isEditin.value = !_isEditin.value
    }

}