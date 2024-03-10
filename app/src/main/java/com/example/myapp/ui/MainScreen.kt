package com.example.myapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapp.viewmodel.TodoViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.myapp.R
import com.example.myapp.model.Todo

@Composable
fun MainScreen(navController: NavController, todoViewModel: TodoViewModel) {
    val todos: List<Todo> by todoViewModel.todos.observeAsState(emptyList())

    Scaffold(
        topBar = { MainTopBar("Todo List Application", navController) },
        content = { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                // Inside this Column, you can use the 'todos' State
                TodoList(todos = todos)
            }
        }
    )
}

@Composable
fun TodoList(todos: List<Todo>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        itemsIndexed(todos) { index, todo ->
            Row {
                Text(
                    text = todo.title,
                    modifier = Modifier.weight(1f).padding(top = 4.dp, bottom = 4.dp)
                )
                Spacer(modifier = Modifier.width(8.dp)) // Add spacing between text and icon
                Image(
                    painter = painterResource(id = R.drawable.ic_trash_bin), // Assuming you have an image resource for the trash bin icon
                    contentDescription = "Trash Bin Icon",
                    modifier = Modifier.size(24.dp) // Adjust size as per your requirement
                )
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}