# Backend Initialization & Runtime Guide

This document details the critical Gradle configurations and structural implementations required to run the Cart-Near-Me backend and observability stack in this environment.

## 1. Environment & Versions

The project is aligned with the following futuristic toolchain required by the environment's cached libraries:

- **Kotlin**: `2.3.0`
- **Gradle**: `8.11.1` (Wrapper correctly initialized for KMP/Ktor Shadow compatibility)
- **Java/JVM**: `17` (Locked via `jvmToolchain(17)`)

## 2. Mandatory Gradle Configuration

### Compiler Options (Kotlin 2.0+)
In Kotlin 2.3.0, the legacy `kotlinOptions` DSL is an error. All modules successfully utilize the new `compilerOptions` DSL:
```kotlin
compilerOptions {
    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
}
```

### Critical Plugins
- `io.ktor.plugin`: Manages the Ktor server and generates the fat JAR.
- `application`: Explicitly applied to enable the `:server:run` task.
- `org.jetbrains.kotlin.plugin.compose`: Required for Compose Multiplatform when using Kotlin 2.0+.

## 3. Database Layer (`:shared`)

The project uses SQLDelight for cross-platform persistence. To bridge the KMP module and the JVM server, a `jvm()` target and implementation were added.

### DatabaseDriverFactory
An `expect class` in `commonMain` handles the platform-specific SQLite driver creation:
- **JVM (`shared/src/jvmMain`)**: Uses `JdbcSqliteDriver` targeting a local `cart.db` file.
- **Android (`shared/src/androidMain`)**: Uses `AndroidSqliteDriver`.

### SQLDelight Boolean Workaround
Due to a generation bug in some SQLDelight versions when paired with newer Kotlin versions, the schema uses `kotlin.Boolean` explicitly:
```sql
isCompleted INTEGER AS kotlin.Boolean NOT NULL DEFAULT 0
```

## 4. Dependency Injection (Koin)

The server initializes Koin and provides the platform-specific `DatabaseDriverFactory` required by the shared module:

```kotlin
// server/src/main/kotlin/com/cartnearme/server/Application.kt
install(Koin) {
    modules(commonModule, module {
        single { DatabaseDriverFactory() }
    })
}
```

## 5. Running & Testing

### How to Run
Use the Gradle wrapper to ensure the correct version is utilized:
```bash
./gradlew :server:run
```

### Metrics Validation
The observability stack is ready for verification:
1.  Start the backend (as shown above).
2.  Visit `http://localhost:8085/metrics`.
3.  Deploy the stack: `cd deploy && docker-compose up -d`.

---

> [!IMPORTANT]
> **Schema Changes**: If you modify `CartDatabase.sq`, run `./gradlew generateCommonMainCartDatabaseInterface` to regenerate the interfaces for all targets.

> [!TIP]
> **Shadow JAR**: For production deployments, use `./gradlew :server:shadowJar` to generate a standalone executable JAR in `server/build/libs`.
