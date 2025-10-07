package com.example.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel




@Composable
fun AddDishScreen(navController: NavController, viewModel: MenuViewModel = viewModel()) {

    var dishName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var selectedCourse by remember { mutableStateOf(Course.MAIN) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 5. Handle text inputs
        // 1. Dish name
        OutlinedTextField(
            value = dishName,
            onValueChange = { dishName = it },
            label = { Text("Dish Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        // 1. Description
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        // 1. Price
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price ($)") },
            modifier = Modifier.fillMaxWidth(),
            // Allows only numbers for price input
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(Modifier.height(16.dp))

        // 1. Course selection (using a simple dropdown/spinner concept)
        CourseSelectionDropdown(
            selectedCourse = selectedCourse,
            onCourseSelected = { selectedCourse = it }
        )
        Spacer(Modifier.height(16.dp))

        // 7. Handle button presses
        Button(
            onClick = {
                viewModel.addDish(dishName, description, selectedCourse, price)
                navController.popBackStack() // Go back to the home screen
            },
            modifier = Modifier.fillMaxWidth(),
            // Only enable the button if all fields are filled
            enabled = dishName.isNotBlank() && description.isNotBlank() && price.isNotBlank()
        ) {
            Text("Add Dish to Menu")
        }
    }
}



@Composable
fun CourseSelectionDropdown(selectedCourse: Course, onCourseSelected: (Course) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    // 2. The app must have a predefined set of courses...
    val courses = Course.entries // Get all enum values (STARTER, MAIN, DESSERT)

    OutlinedButton(onClick = { expanded = true }) {
        Text("Course: ${selectedCourse.name}")
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Select Course"
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        courses.forEach { course ->
            DropdownMenuItem(
                text = { Text(course.name) },
                onClick = {
                    onCourseSelected(course)
                    expanded = false
                }
            )
        }
    }
}