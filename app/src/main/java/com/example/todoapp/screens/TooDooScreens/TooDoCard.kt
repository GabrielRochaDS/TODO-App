package com.example.todo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import com.example.todo.ui.model.Todo
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.auxilyarisolution.ui.ConfirmatorDialog
import com.example.compose.TODOTheme
import com.example.todoapp.screens.TooDooScreens.TooDoViewModel

//================================ RESPONSAVEL PELO COMPOSABLE DO TOODOO================================

@Composable
fun TooDoCard(
    modifier: Modifier = Modifier,
    todo: Todo,
    tooDoViewModel: TooDoViewModel,
    isEditin: Boolean
) {
    //================Variaveis que guardam o maximo de linhas e se a tela ta espaandida================
    val context = androidx.compose.ui.platform.LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val maxLinesTitle = if (!expanded) 1 else 10
    var showDeleteUi by remember { mutableStateOf(false) }
    var showTodooCard by remember { mutableStateOf(false) }
    var deleteTodo by remember { mutableStateOf(false) }


    Card(
        modifier = modifier
            .clickable {
                if (!isEditin) {
                    expanded = !expanded
                } else {
                    showTodooCard = true
                }
            }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = todo.title,
                fontSize = 20.sp,
                maxLines = maxLinesTitle,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                overflow = TextOverflow.Ellipsis,
            )
            if (expanded) {
                Text(
                    text = todo.description ?: "Texto não possui descrição",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            if (!isEditin) {
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(25.dp)
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
        if (isEditin) {
            IconButton(
                onClick = {
                    showDeleteUi = true
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(25.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        }
        if (showDeleteUi) {
            ConfirmatorDialog(
                onDismissRequest = { showDeleteUi = false },
                onConfirmation = {
                    showDeleteUi = false
                    deleteTodo = true
                },
                icon = { },
                principalText = { Text(text = "Atenção!") },
                secondaryText = { Text(text = "Tem certeza que deseja deletar o card? \n Essa ação não poderá ser desfeita!!") })
        }
        if (showTodooCard) {
            NewTooDo(
                tooDotitle = todo.title,
                tooDoDescription = todo.description,
                onDismissRequest = { showTodooCard = false },
                onConfirmation = { title, description ->
                    if (title != null) {
                        tooDoViewModel.tooDos.value.forEach {
                            if (it.title == todo.title) {
                                it.title = title
                                it.description = description
                            }
                        }
                    }
                }
            )
        }
        if (deleteTodo) {
            LaunchedEffect(Unit) {
                tooDoViewModel.deleteTooDo(todo, context)
            }
            deleteTodo = false
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
fun HomeScreemPreview() {
    val tooDoViewModel: TooDoViewModel = viewModel()
    TODOTheme {
        TooDoCard(
            todo = Todo("TooDoo Preview", "TooDoo Preview"),
            tooDoViewModel = tooDoViewModel,
            isEditin = false
        )
    }
}