package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.TODOTheme
import com.example.todo.ui.TodoAppScreen
import com.example.todoapp.screens.TooDooScreens.TooDoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val tooDoViewModel: TooDoViewModel = viewModel()
                    // Load the initial data
                    LaunchedEffect(Unit) {
                        // Load the list from file when the app starts
                        tooDoViewModel.load(this@MainActivity)
                    }

                    TodoAppScreen()
                }
            }
        }
    }
}

