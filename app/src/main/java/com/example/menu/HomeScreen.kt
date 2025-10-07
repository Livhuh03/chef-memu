package com.example.menu

import android.R.attr.text
import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: MenuViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menu Manager") },
                // 4. The home screen must display the total number of menu items
                actions = {
                    Text(
                        text = "Total Items: ${viewModel.getTotalItems()}",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            )
        },
        // 7. Handle button presses (Floating Action Button to navigate)
        floatingActionButton = {
            // 5. ...the chef can add the menu items to the list.
            FloatingActionButton(onClick = { navController.navigate(Screen.AddDish.route) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Dish")
            }
        }
    ) { paddingValues ->
        // 1. Use core components and 2. Use layouts to position components
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 3. The home screen must display the menu that the chef has prepared.
            items(viewModel.menuItems) { dish ->
                DishCard(dish = dish)
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun DishCard(dish: Dish) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display details
            Text(
                text = dish.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(4.dp))
            Text(text = "Price: $${String.format("%.2f", dish.price)}", style = MaterialTheme.typography.titleMedium)
            text
        }
    }
}