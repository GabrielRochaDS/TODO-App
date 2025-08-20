package com.example.todo.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.TODOTheme
import com.example.todo.ui.screens.NewTooDo
import com.example.todo.ui.screens.TooDoCard
import com.example.todoapp.screens.TooDooScreens.TooDoViewModel


//================================INICIO DO APP================================
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoAppScreen(modifier: Modifier = Modifier, tooDoViewModel: TooDoViewModel = viewModel()) {
    val context = LocalContext.current
    var newCard by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    val toodos = tooDoViewModel.tooDos.collectAsState()
    var searchText by remember { mutableStateOf("") }
    Scaffold(
        //================TopAppBar and BottonAppBar================
        topBar = {
            TodoTopAppBar()
        }, bottomBar = {
            TodoBottonAppBar(modifier,
                newCard = { newCard = true },
                isEditing = { isEditing = !isEditing })
        }) { innerPadding ->
        //================Column que contem o campo de pesquisa e a lista de tooDoos================
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                label = { Text("Pesquisar Card") },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(MaterialTheme.colorScheme.background)
            )
            LazyColumn {
                //================Exibir Cada iten da lista tooDoos================
                items(toodos.value.filter { it.title.contains(searchText, ignoreCase = true) }) {
                    TooDoCard(
                        todo = it,
                        modifier = modifier.padding(16.dp),
                        tooDoViewModel = tooDoViewModel,
                        isEditin = isEditing
                    )
                }

            }

        }
    }
    if (newCard) {
        NewTooDo(
            onDismissRequest = { newCard = false },
            onConfirmation = { title, description ->
                if (title != null) {
                    tooDoViewModel.addNewTooDo(title = title, description = description, context = context)
                }
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Todo App",
                    fontWeight = FontWeight.Bold,
                )
            }

        }, modifier = modifier
    )
}

@Composable
fun TodoBottonAppBar(
    modifier: Modifier = Modifier, isEditing: () -> Unit, newCard: () -> Unit
) {
    BottomAppBar(modifier = modifier.height(60.dp), actions = {
        IconButton(onClick = { isEditing() }) {
            Icon(
                Icons.Filled.Edit,
                contentDescription = "Localized description",
            )
        }
    }, floatingActionButton = {
        FloatingActionButton(
            modifier = modifier
                .height(40.dp)
                .width(40.dp),
            onClick = { newCard() },
            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
        ) {
            Icon(Icons.Filled.Add, "Localized description")
        }
    })
}


//@Preview(
//    showSystemUi = true
//)
//@Composable
//fun HomeScreenPreview() {
//    TODOTheme {
//        TodoAppScreen()
//    }
//}