package com.example.appmovil.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.appmovil.data.model.ProductRequest
import com.example.appmovil.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

@Composable
fun ProductsScreen(vm: ProductViewModel) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    // Estados del formulario de creación
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        // Barra de búsqueda
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                vm.searchByName(it)
            },
            label = { Text("Buscar producto") },
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "barra_busqueda" }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Formulario de creación
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Categoría") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val priceDouble = price.toDoubleOrNull() ?: 0.0
                    if (name.isNotBlank()) {
                        vm.create(ProductRequest(name, priceDouble, category.ifBlank { null }))
                        name = ""
                        price = ""
                        category = ""
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Crear")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Estado de carga o mensaje
        if (uiState.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        uiState.message?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de productos
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(uiState.items) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(product.name, style = MaterialTheme.typography.titleMedium)
                            Text("Precio: $${product.price}", style = MaterialTheme.typography.bodyMedium)
                            product.category?.let { Text("Categoría: $it") }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            // Botón de +1.00 al precio
                            IconButton(onClick = {
                                val newPrice = product.price + 1.0
                                vm.update(product.id!!, ProductRequest(product.name, newPrice, product.category))
                            }) {
                                Text("+1")
                            }
                            // Botón de eliminar
                            IconButton(onClick = {
                                vm.delete(product.id!!)
                            }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}