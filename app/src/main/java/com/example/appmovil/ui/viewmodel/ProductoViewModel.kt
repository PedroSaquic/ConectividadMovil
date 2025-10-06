package com.example.appmovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovil.data.model.Producto
import com.example.appmovil.data.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoViewModel(private val repository: ProductoRepository = ProductoRepository()
): ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _productos.value = repository.getProductos()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun crearProducto(producto: Producto) {
        viewModelScope.launch {
            try {
                repository.crear(producto)
                cargarProductos() // Refresca la lista
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun actualizarProducto(id: Long, producto: Producto) {
        viewModelScope.launch {
            try {
                repository.actualizar(id, producto)
                cargarProductos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun eliminarProducto(id: Long) {
        viewModelScope.launch {
            try {
                repository.eliminar(id)
                cargarProductos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}