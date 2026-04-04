---
type: Domain Model
module: shared/commonMain
tags: [model, kmp, shared-logic]
---

# Domain Model: CartItem

> [!abstract] Purpose
> Represents a single item in a user's shopping cart, mapped to a generalized OpenStreetMap category rather than a specific physical store.

## 🧬 Structure (Kotlin)
```kotlin
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
```

## 📏 Business Rules & Validation
* The `id` must be a valid UUIDv4 string generated locally on the device.
* The `category` MUST directly map to valid OpenStreetMap tags (e.g., `shop=supermarket` mapped internally to `supermarket`).
* The item requires valid `createdAt` and `updatedAt` Unix timestamps in milliseconds for offline-first sync.

## 🔄 Lifecycle & Sync
* **Local Storage:** Stored locally via SQLDelight in the shared KMP module.
* **Sync Strategy:** Uses "Last-Writer-Wins" based on the `updatedAt` timestamp to resolve offline merge conflicts with the backend.

## 🔗 Related Links
* **Related Model:** [[Domain-LocationIntent]]
