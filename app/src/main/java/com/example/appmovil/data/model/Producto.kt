package com.example.appmovil.data.model

data class Producto(
    val id: Long?, //o genera el servidor al crearse
    val nombre: String,
    val precio: Double,
    val categoria: String?, //puede omitirse
    val creadoEn: String? //lo asigna el backed automaticamente
)

data class ProductoRequest(
    val nombre: String,
    val precio: Double,
    val categoria: String?
)