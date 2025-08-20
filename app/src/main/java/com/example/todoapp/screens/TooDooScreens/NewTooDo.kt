package com.example.todo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.TODOTheme
import com.example.todoapp.screens.TooDooScreens.TooDoViewModel

@Composable
fun NewTooDo(
    tooDotitle: String? = null,
    tooDoDescription: String? = null,
    onDismissRequest: () -> Unit,
    onConfirmation: suspend (title: String?, descriprion: String?) -> Unit
) {
    var title by remember { mutableStateOf(tooDotitle) }
    var description by remember { mutableStateOf(tooDoDescription) }
    var confirmButton by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text(text = "Adicionar nova Atividade") },
        text = {
            Column {
                OutlinedTextField(
                    value = if (title != null) {
                        title!!
                    } else {
                        ""
                    },
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = if (description != null) {
                        description!!
                    } else {
                        ""
                    },
                    onValueChange = { description = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    confirmButton = true
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("Cancel")
            }
        }
    )
    LaunchedEffect(confirmButton) {
        if (confirmButton) {
            onConfirmation(title, description)
            onDismissRequest()
        }
    }
}


//@Preview
//@Composable
//fun NewTooDoPreview(modifier: Modifier = Modifier) {
//    TODOTheme {
//        val tooDoViewModel: TooDoViewModel = viewModel()
//
//        NewTooDo(onDismissRequest = {}, onConfirmation = { _, _ -> })
//    }
//}