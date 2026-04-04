---
date: 2026-04-04
status: Accepted
tags: [adr, decision, architecture, database, kmp]
---

# ADR-005: Local Persistence using SQLDelight for KMP

> [!INFO] Meta
> **Deciders:** Lead Architect
> **Date:** 2026-04-04

## 1. Context and Problem Statement
Cart-Near-Me requires an offline-first architecture. A user must be able to add, complete, and update items in their cart—and modify background tracking tags (Location Intents)—even without cellular service. We need a unified database solution that functions synchronously across Android, iOS, and the potential future JVM server configurations inside our Kotlin Multiplatform monorepo.

## 2. Decision Drivers
* Must offer native, high-performance database execution across iOS (CoreData/SQLite) and Android (Room/SQLite).
* Must provide strict type-safety directly from our Kotlin business logic.
* Must keep the schema simple to handle primitive syncing strategies (Last-Writer-Wins) seamlessly.

## 3. Considered Options
* **Room (KMP)**: Promising, but still maturing its Multiplatform guarantees and may inject additional kapt/ksp build complexities.
* **Realm (MongoDB)**: Solid feature set, but too heavy for our minimal cart schema and locks us into an ecosystem external to the standard JetBrains/CashApp lineage. 
* **SQLDelight**: Time-tested on KMP. Offers raw SQL schema definitions while generating purely native type-safe Kotlin models on compilation.

## 4. Decision Outcome
**Chosen option:** "SQLDelight", because it keeps the schema directly exposed as raw SQL (reducing abstraction obfuscation) while granting full Kotlin compile-time verification across all platforms. 

> [!SUCCESS] Positive Consequences
> * Total type-safety: our `CREATE TABLE` directly dictates our Kotlin database entities.
> * Lightweight footprint that prevents bloated APKs or IPA bundles.
> * Simple primitive conversions (e.g., treating SQLite `INTEGER` as a Kotlin `Boolean` natively, and handling timestamps purely as `Long` via `INTEGER`).

> [!WARNING] Negative Consequences
> * We must write raw SQL for every query rather than relying on an automated ORM method structure.
> * Database migrations require strict `.sqm` file handling.

## 5. Links
* **Relevant Models:** [[Domain-CartItem]], [[Domain-LocationIntent]]
* **Project Overview:** [[Project-Manifesto]]
