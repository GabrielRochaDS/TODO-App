package com.example.todo.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.TODOTheme
import com.example.todo.ui.screens.NewTooDo
import com.example.todo.ui.screens.TooDoCard
import com.example.todo.ui.screens.TooDoViewModel


//================================INICIO DO APP================================
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoAppScreen(modifier: Modifier = Modifier, tooDoViewModel: TooDoViewModel = viewModel()) {
        val newCard by tooDoViewModel.newCard.collectAsState()
        val toodos = tooDoViewModel.tooDos.collectAsState()
        Scaffold(
        //================TopAppBar and BottonAppBar================
        topBar = {
        TodoTopAppBar()
        },
        bottomBar = {
        TodoBottonAppBar(modifier, tooDoViewModel)          //   Bad Use View Model
        }
        ) { innerPadding ->
        LazyColumn(
        contentPadding = innerPadding
        ) {
        //================Exibir Cada iten da lista tooDoos================
        items(toodos.value){
        TooDoCard(todo = it , modifier = modifier.padding(10.dp), tooDoViewModel = tooDoViewModel)
        }

        }
        }
        if (newCard){
        NewTooDo(tooDoViewModel = tooDoViewModel)
        }
        }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopAppBar(modifier: Modifier = Modifier) {
        CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
        Row(
        verticalAlignment = Alignment.CenterVertically
        ) {
        Icon(
        Icons.Filled.Check,
        contentDescription = null,
        modifier = Modifier.padding(end = 5.dp)
        )
        Text(
        text = "Todo App",
        fontWeight = FontWeight.Bold,
        )
        }

        },
        modifier = modifier
        )
        }
@Composable
fun TodoBottonAppBar(modifier: Modifier = Modifier, tooDoViewModel: TooDoViewModel){         //   Bad Use View Model
        BottomAppBar(
        modifier = modifier.height(60.dp) ,
        actions = {
        IconButton(onClick = { tooDoViewModel.isOnEditin() }) {
        Icon(
        Icons.Filled.Edit,
        contentDescription = "Localized description",
        )
        }
        },
        floatingActionButton = {
        FloatingActionButton(
        modifier = modifier
        .height(40.dp)
        .width(40.dp),
        onClick = { tooDoViewModel.Change() },
        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
        ) {
        Icon(Icons.Filled.Add, "Localized description")
        }
        }
        )
        }


@Preview(
        showSystemUi = true
)
@Composable
fun HomeScreenPreview(){
        TODOTheme {
        TodoAppScreen()
        }
        }