# 🚀 Cart-Near-Me: Development Roadmap

> [!abstract] Mission
> Build a privacy-first, open-source category-based reminder app using KMP and Ktor.

## 📍 Phase 1: Foundation (Completed: 2026-04-04)
> [!check] Foundation Solid
> The KMP module tree is successfully populated. The offline-first local SQLDelight databases and Koin Expect/Actual DI graphs are officially sync-ready across the server and mobile targets.

- [x] **1.1 Dependency Management**: Configure `gradle/libs.versions.toml` with Kotlin 2.1, Ktor 3.4, Compose Multiplatform, and Koin 4.0. (2026-04-04)
- [x] **1.2 Gradle Wiring**: Connect `:shared`, `:server`, and `:composeApp` in `settings.gradle.kts` and root `build.gradle.kts`. (2026-04-04)
- [x] **1.3 Data Contracts**: Define shared `CartItem` and `LocationIntent` models in `shared/commonMain` using `Template_Domain.md`. (2026-04-04)
- [x] **1.4 Local DB**: Implement SQLDelight schema for offline-first carts. (2026-04-04)
- [x] **1.5 DI Foundation**: Basic Koin module setup for all three platforms. (2026-04-04)
- [x] **1.6 Frontend Telemetry**: Implement `PrivacyAnalyticsClient` and PostHog integration with strict PII scrubbing.
- [x] **1.7 Backend Observability**: Setup Ktor Micrometer/Prometheus with Logback PII masking.
- [ ] **1.8 Crash Reporting**: Setup Sentry for both the KMP shared module and the Ktor server.
- [ ] **1.9 Sentry Config**: Update placeholders with actual Sentry DSNs for production.

## 🌐 Phase 2: The Spatial Engine (Backend)
- [ ] **2.1 PostGIS Setup**: Create `docker-compose.yml` in `/deploy` for Postgres + PostGIS.
- [ ] **2.2 OSM Integration**: Ktor service to fetch POIs by category tags using the "Fuzzy Grid" privacy logic.
- [ ] **2.3 Health & Metrics**: Implement `/health` and `/metrics` endpoints for the Load Balancer.

## 📱 Phase 3: Mobile UI & Geofencing
- [ ] **3.1 Tracelet Integration**: Android/iOS background geofence lifecycle logic.
- [ ] **3.2 Compose UI**: Build the "Quick-Add" cart screen and "Nearby Alerts" view.
