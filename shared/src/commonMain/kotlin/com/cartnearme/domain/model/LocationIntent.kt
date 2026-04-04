package com.cartnearme.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationIntent(
    val category: String, // OSM Tag
    val radius: Double,
    val isActive: Boolean
)
