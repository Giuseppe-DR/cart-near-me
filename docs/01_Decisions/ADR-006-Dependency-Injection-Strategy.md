---
date: 2026-04-04
status: Accepted
tags: [adr, decision, architecture, di, kmp]
---

# ADR-006: Koin for Kotlin Multiplatform Dependency Injection

> [!INFO] Meta
> **Deciders:** Lead Architect
> **Date:** 2026-04-04

## 1. Context and Problem Statement
In a Kotlin Multiplatform project spanning Android, iOS, and a JVM Backend, we require a robust Dependency Injection (DI) mechanism. We must safely supply platform-specific dependencies (such as SQLite drivers for SQLDelight) without polluting the shared business logic.

## 2. Decision Drivers
* Must strictly support multiplatform architectures (Android, iOS, JVM).
* Must have minimal overhead or compiler delays (avoiding kapt/KSP reflection where possible).
* Must integrate seamlessly with Ktor backend and Compose Multiplatform.

## 3. Considered Options
* **Dagger/Hilt**: The industry standard for Android. Extremely robust, compile-time safe, but fundamentally tied to the Android/JVM ecosystem (relies heavily on Java code generation and `kapt`/`ksp`), making it exceptionally difficult to use reliably for iOS/native targets without massive workarounds.
* **Kotlin-Inject**: A great KSP-based compile-time DI framework for KMP, but the community ecosystem and Ktor integration tooling isn't as mature as Koin.
* **Koin**: A pragmatic, lightweight DSL for Kotlin. It does not use reflection or code generation (no kapt/ksp overhead), and provides official extensions for multiplatform, Ktor, and Compose.

## 4. Decision Outcome
**Chosen option:** "Koin". It avoids the massive multiplatform generation headaches caused by Dagger/Hilt and natively bridges Ktor routing, Compose scopes, and pure Kotlin modules together in our monorepo.

> [!SUCCESS] Positive Consequences
> * Zero code generation, ensuring extremely quick localized build times.
> * "Platform-Specific Injection" behaves seamlessly: we define `expect` classes in `commonMain` (like `DatabaseDriverFactory`), and use Koin's `module {}` DSL to inject the `actual` platform implementations at runtime.
> * A shared `initKoin {}` entry point allows our clients to wrap their entire application state in one standard KMP function.

> [!WARNING] Negative Consequences
> * Graph validation happens at runtime rather than during the compile sequence. We must rely on strict module declarations and testing (e.g., `koin.checkModules()`) to prevent `NoBeanDefFoundException` crashes in production.

## 5. Links
* **Relevant Feature Setup:** [[ADR-005-Local-Persistence-Schema]]
* **Architecture Rules:** [[Project-Manifesto]]
