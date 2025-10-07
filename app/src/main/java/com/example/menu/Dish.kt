package com.example.menu

// 6. Use TypeScript variables to store data (translated to Kotlin's data class)
data class Dish(
    val id: Int, // Unique identifier
    val name: String,
    val description: String,
    val course: Course, // e.g., Starter, Main, Dessert
    val price: Double
)

// Requirement 2: App must have a predefined set of courses (Starters, Mains, Desserts)
enum class Course {
    STARTER,
    MAIN,
    DESSERT
}