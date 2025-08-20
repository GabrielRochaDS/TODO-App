package com.example.todoapp.screens.TooDooScreens

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.todo.ui.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


class TooDoViewModel : ViewModel() {

    var tooDos = MutableStateFlow<MutableList<Todo>>(mutableListOf())

    suspend fun addNewTooDo(title: String, description: String?, context: Context){
        val newTodo = Todo(title = title, description = description)
        tooDos.value = (tooDos.value + newTodo).toMutableList()
        saveTodoListToFile(context = context, todoList = tooDos.value, fileName = "todo.json")
    }

    suspend fun deleteTooDo(todo: Todo, context: Context) {
        tooDos.value = (tooDos.value - todo).toMutableList()
        saveTodoListToFile(context = context, todoList = tooDos.value, fileName = "todo.json")
    }

    suspend fun load(context: Context) {
        val loadedList = readTodoListFromFile(context, "todo.json") ?: emptyList()
        tooDos.value = loadedList.toMutableList()
    }


    private suspend fun saveTodoListToFile(context: Context, todoList: List<Todo>, fileName: String) {
        withContext(Dispatchers.IO) {
            val jsonString = Json.encodeToString(todoList)
            val file = File(context.filesDir, fileName)
            file.writeText(jsonString)
        }
    }
    private suspend fun readTodoListFromFile(context: Context, fileName: String): List<Todo>? {
        return withContext(Dispatchers.IO) {
            val file = File(context.filesDir, fileName)

            if (file.exists()) {
                val jsonString = file.readText()  // Lê o conteúdo do arquivo
                return@withContext Json.decodeFromString<List<Todo>>(jsonString)  // Desserializa o JSON para uma lista de Todo
            } else {
                null  // Se o arquivo não existir, retorna null
            }
        }
    }
}