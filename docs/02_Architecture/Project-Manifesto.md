---
status: Active
tags: [meta, architecture, vision]
---

# Cart-Near-Me: Project Manifesto

## 🎯 The Core Vision
An intent-based reminder app. Unlike standard "Pin-based" reminders, this app uses OpenStreetMap (OSM) categories. The user adds "Milk" to "Grocery," and the app triggers when near ANY grocery store.

## 🛠️ Technical Decisions (ADR Summary)
- **Stack:** KMP (Compose Multiplatform) + Ktor 3.4 + PostGIS.
- **Patterns:** Clean Architecture + UDF + Repository Pattern.
- **Privacy:** Fuzzy Grid logic (backend never sees precise GPS).
- **License:** GNU AGPLv3 (Open Source, but prevents closed-source SaaS clones).
- **Infrastructure:** Ktor sits behind a Traefik Load Balancer with Health Checks.

## 📚 Documentation Strategy
All docs follow the Atomic Note principle in `/docs`. Templates for ADRs, APIs, Domain Models, and Features are stored in `/docs/00_Meta/`.