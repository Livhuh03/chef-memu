package com.example.menu


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.menu.ui.theme.MenuTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuTheme {
                MenuAppNavigation()
            }
        }
    }
}


// Defines the routes/screens for your app
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddDish : Screen("add_dish")

}

@Composable
fun MenuAppNavigation() {
    // 10. Navigate between different screens in an app.
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.AddDish.route) {
            AddDishScreen(navController = navController)
        }
    }
}