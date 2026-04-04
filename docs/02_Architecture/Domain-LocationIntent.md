---
type: Domain Model
module: shared/commonMain
tags: [model, kmp, shared-logic]
---

# Domain Model: LocationIntent

> [!abstract] Purpose
> Represents the spatial intent of a user. Instead of checking for a single address, the app registers this intent (e.g., "Find a Pharmacy within 1km") to process geofencing dynamically.

## 🧬 Structure (Kotlin)
```kotlin
package com.cartnearme.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationIntent(
    val category: String, // OSM Tag
    val radius: Double,
    val isActive: Boolean
)
```

## 📏 Business Rules & Validation
* The `category` references the same OSM tags used in [[Domain-CartItem]].
* `radius` dynamically adjusts the geographical bounding box (e.g., `500m` for walking, `5km` for driving) to find relevant points of interest via our Fuzzy Grid pattern. 
* `isActive` state determines if tracelet/background geofencing is currently tracking this category intent.

## 🔄 Lifecycle & Sync
* **Local Storage:** Maintained strictly within the local device memory/SQLDelight for privacy. 
* **Privacy Contract:** This object orchestrates the privacy layer. Background paths are never synced.

## 🔗 Related Links
* **Related Model:** [[Domain-CartItem]]
* **Architecture Docs:** [[Project-Manifesto]]
