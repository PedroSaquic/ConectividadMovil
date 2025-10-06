package com.example.appmovil.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appmovil.ui.view.*
import com.example.appmovil.ui.viewmodel.ProductoViewModel

@Composable
fun AppNavigation(viewModel: ProductoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "producto_list") {
        composable("producto_list") {
            ProductoListScreen(navController, viewModel)
        }
        composable(
            "producto_detail/{productoId}",
            arguments = listOf(navArgument("productoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("productoId") ?: 0L
            ProductoDetailScreen(navController, viewModel, id)
        }
        composable(
            "producto_form/{productoId?}",
            arguments = listOf(navArgument("productoId") {
                type = NavType.LongType
                defaultValue = 0L
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("productoId")
            ProductoFormScreen(navController, viewModel, if (id == 0L) null else id)
        }
    }
}


