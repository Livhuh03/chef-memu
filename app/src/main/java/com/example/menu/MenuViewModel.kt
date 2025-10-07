package com.example.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel



class MenuViewModel : ViewModel() {
    // Mutable state to hold the list of menu items.
    // 6. Use TypeScript variables to store data (using Kotlin State Flow)
    private var _menuItems by mutableStateOf(listOf<Dish>())
    val menuItems: List<Dish> get() = _menuItems

    // Helper to generate unique IDs
    private var nextDishId = 1

    /**
     * Adds a new dish to the menu list.
     * Requirement 5: For Part 2 only, on the homepage, the chef can add the menu items to the list.
     */
    // 7. Handle button presses and 9. Use if statement to solve programming problems
    // 8. Use TypeScript to perform actions according to user input.
    fun addDish(name: String, description: String, course: Course, price: String) {
        // Simple input validation (using an 'if' for problem-solving)
        val validatedPrice = price.toDoubleOrNull()
        if (name.isBlank() || description.isBlank() || validatedPrice == null || validatedPrice <= 0) {
            // In a real app, you'd show an error message
            println("Error: Invalid input for adding a dish.")
            return
        }

        val newDish = Dish(
            id = nextDishId++,
            name = name,
            description = description,
            course = course,
            price = validatedPrice
        )
        // Add the new dish to the list
        _menuItems = _menuItems + newDish
        println("Dish added: $newDish")
    }

    /**
     * Calculates the total number of items on the menu.
     * Requirement 4: The home screen must display the total number of menu items to select from.
     */
    fun getTotalItems(): Int {
        return _menuItems.size
    }
}