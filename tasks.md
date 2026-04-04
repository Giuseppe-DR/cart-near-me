# 🚀 Cart-Near-Me: Development Roadmap

> [!abstract] Mission
> Build a privacy-first, open-source category-based reminder app using KMP and Ktor.

## 📍 Phase 1: Foundation (Current)
- [x] **1.1 Dependency Management**: Configure `gradle/libs.versions.toml` with Kotlin 2.1, Ktor 3.4, Compose Multiplatform, and Koin 4.0.
- [x] **1.2 Gradle Wiring**: Connect `:shared`, `:server`, and `:composeApp` in `settings.gradle.kts` and root `build.gradle.kts`.
- [x] **1.3 Data Contracts**: Define shared `CartItem` and `LocationIntent` models in `shared/commonMain` using `Template_Domain.md`.
- [x] **1.4 Local DB**: Implement SQLDelight schema for offline-first carts.
- [x] **1.5 DI Foundation**: Basic Koin module setup for all three platforms.

## 🌐 Phase 2: The Spatial Engine (Backend)
- [ ] **2.1 PostGIS Setup**: Create `docker-compose.yml` in `/deploy` for Postgres + PostGIS.
- [ ] **2.2 OSM Integration**: Ktor service to fetch POIs by category tags using the "Fuzzy Grid" privacy logic.
- [ ] **2.3 Health & Metrics**: Implement `/health` and `/metrics` endpoints for the Load Balancer.

## 📱 Phase 3: Mobile UI & Geofencing
- [ ] **3.1 Tracelet Integration**: Android/iOS background geofence lifecycle logic.
- [ ] **3.2 Compose UI**: Build the "Quick-Add" cart screen and "Nearby Alerts" view.
