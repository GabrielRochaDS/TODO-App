package com.example.todo.ui.model

import kotlinx.serialization.Serializable


//================================DATA CLASS DE CADA ITEN A SE FAZER================================
@Serializable
data class Todo(
    var title: String,
    var description: String?
)

