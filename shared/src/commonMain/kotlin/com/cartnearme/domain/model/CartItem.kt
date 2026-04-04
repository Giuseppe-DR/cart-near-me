package com.cartnearme.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val id: String,
    val name: String,
    val category: String, // OSM Tag
    val quantity: String,
    val isCompleted: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
