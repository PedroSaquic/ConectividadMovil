package com.example.appmovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovil.data.model.Product
import com.example.appmovil.data.model.ProductRequest
import com.example.appmovil.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Estado de UI para productos
data class ProductUiState(
    val loading: Boolean = false,
    val items: List<Product> = emptyList(),
    val message: String? = null
)

class ProductViewModel(
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState

    // Cargar todos los productos
    fun loadAll() {
        _uiState.value = _uiState.value.copy(loading = true)
        viewModelScope.launch {
            runCatching { repository.listAll() }
                .onSuccess { response ->
                    if (response.isSuccessful) {
                        _uiState.value = _uiState.value.copy(
                            items = response.body() ?: emptyList(),
                            loading = false,
                            message = null
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            loading = false,
                            message = "Error: ${response.code()}"
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.value = _uiState.value.copy(
                        loading = false,
                        message = throwable.message
                    )
                }
        }
    }

    // Buscar productos por nombre
    fun searchByName(name: String) {
        _uiState.value = _uiState.value.copy(loading = true)
        viewModelScope.launch {
            runCatching { repository.search(name) }
                .onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        items = response.body() ?: emptyList(),
                        loading = false,
                        message = null
                    )
                }
                .onFailure { throwable ->
                    _uiState.value = _uiState.value.copy(
                        loading = false,
                        message = throwable.message
                    )
                }
        }
    }

    // Crear un producto
    fun create(request: ProductRequest) {
        viewModelScope.launch {
            runCatching { repository.create(request) }
                .onSuccess { loadAll() } // recarga la lista después de crear
                .onFailure { throwable ->
                    _uiState.value = _uiState.value.copy(message = throwable.message)
                }
        }
    }

    // Actualizar un producto
    fun update(id: Long, request: ProductRequest) {
        viewModelScope.launch {
            runCatching { repository.update(id, request) }
                .onSuccess { loadAll() } // recarga la lista después de actualizar
                .onFailure { throwable ->
                    _uiState.value = _uiState.value.copy(message = throwable.message)
                }
        }
    }

    // Eliminar un producto
    fun delete(id: Long) {
        viewModelScope.launch {
            runCatching { repository.delete(id) }
                .onSuccess { loadAll() } // recarga la lista después de eliminar
                .onFailure { throwable ->
                    _uiState.value = _uiState.value.copy(message = throwable.message)
                }
        }
    }
}
