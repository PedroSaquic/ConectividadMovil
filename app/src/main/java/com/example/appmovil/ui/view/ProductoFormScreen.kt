package com.example.appmovil.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appmovil.data.model.Producto
import com.example.appmovil.ui.viewmodel.ProductoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoFormScreen(
    navController: NavController,
    viewModel: ProductoViewModel,
    productoId: Long? = null
) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(productoId) {
        productoId?.let {
            val prod = viewModel.productos.value.find { it.id == productoId }
            prod?.let {
                nombre = it.nombre
                precio = it.precio.toString()
                descripcion = it.descripcion
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(if (productoId == null) "Nuevo Producto" else "Editar Producto") }) }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val prod = Producto(
                    id = productoId ?: 0L,
                    nombre = nombre,
                    precio = precio.toDoubleOrNull() ?: 0.0,
                    descripcion = descripcion
                )
                scope.launch {
                    if (productoId == null) viewModel.crearProducto(prod)
                    else viewModel.actualizarProducto(prod.id, prod)
                    navController.popBackStack()
                }
            }) {
                Text("Guardar")
            }
        }
    }
}
