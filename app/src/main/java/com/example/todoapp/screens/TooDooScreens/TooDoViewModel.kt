package com.example.todoapp.screens.TooDooScreens

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.todo.ui.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


class TooDoViewModel : ViewModel() {

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


    suspend fun saveTodoListToFile(context: Context, todoList: List<Todo>, fileName: String) {
        withContext(Dispatchers.IO) {
            val jsonString = Json.encodeToString(todoList)
            val file = File(context.filesDir, fileName)
            file.writeText(jsonString)
        }
    }
    suspend fun readTodoListFromFile(context: Context, fileName: String): List<Todo>? {
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