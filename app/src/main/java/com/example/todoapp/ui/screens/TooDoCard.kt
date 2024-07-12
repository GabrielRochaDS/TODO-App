package com.example.todo.ui.screens
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
import com.example.compose.TODOTheme

//================================ RESPONSAVEL PELO COMPOSABLE DO TOODOO================================

@Composable
fun TooDoCard(modifier: Modifier = Modifier, todo: Todo, tooDoViewModel: TooDoViewModel) {
        //================Variaveis que guardam o maximo de linhas e se a tela ta espaandida================
        var expanded by remember { mutableStateOf(false) }
        val maxLinesTitle = if (!expanded) 1 else 10
        val isEditin by tooDoViewModel.isEditin.collectAsState()


        Card(modifier = modifier.fillMaxWidth()) {
        Column(
        modifier = modifier
        .fillMaxWidth()
        .padding(10.dp))
        {
        Text(
        text = todo.title,
        fontSize = 20.sp,
        maxLines = maxLinesTitle,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(bottom = 15.dp),
        overflow = TextOverflow.Ellipsis,
        )
        if (expanded){
        Text(
        text =if(todo.description!=null){
        todo.description} else "Has no Description",
        style = MaterialTheme.typography.bodyLarge,

        )
        }
        }
        if(!isEditin) {
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
        if(isEditin){
        IconButton(
        onClick = {
        tooDoViewModel.isOnEditin()
        tooDoViewModel.deleteTooDo(todo) },
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
        }
        }


@Preview(
        showBackground = true
)
@Composable
fun HomeScreemPreview(){
        val tooDoViewModel: TooDoViewModel = viewModel()
        TODOTheme {
        TooDoCard(todo = Todo("asdasads","asddasdas"), tooDoViewModel = tooDoViewModel  )
        }
        }