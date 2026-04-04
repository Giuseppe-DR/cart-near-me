---
type: Domain Model
module: shared/commonMain
tags: [model, kmp, shared-logic]
---

# Domain Model: [Model Name]

> [!abstract] Purpose
> [Explain what this model represents in the real world. e.g., "Represents a single item in a user's shopping cart, tied to an intent rather than a specific store."]

## 🧬 Structure (Kotlin)
```kotlin
// Paste the Kotlin data class here
data class ModelName(
    val id: String
)
```

## 📏 Business Rules & Validation
* [Rule 1: e.g., "The `id` must be a valid UUIDv4 generated locally."]
* [Rule 2: e.g., "An item cannot have an empty `category` array."]

## 🔄 Lifecycle & Sync
* **Local Storage:** [How is it saved? e.g., "Stored in SQLDelight `cart_items` table."]
* **Sync Strategy:** [e.g., "Uses Last-Writer-Wins based on the `updatedAt` timestamp."]

## 🔗 Related Links
* **API Endpoints:** [[API-Endpoint-Name]]
* **Database Schema:** [[Schema-Reference]]