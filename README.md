# 🛒 Cart-Near-Me
**Privacy-First Location Reminders for the Minimalist.**

Cart-Near-Me is a high-performance, intent-based reminder application that alerts you to pending tasks when you are near relevant locations—all without ever knowing exactly where you are. Built on the **"Fuzzy Grid"** privacy model, it prioritizes user sovereignty over surveillance.

---

## 🛡️ The "Fuzzy Grid" Philosophy
Most location apps use GPS pins and constant tracking. **Cart-Near-Me** does not.
- **No GPS Pins**: We visualize proximity using illuminated grid cells, not precise markers.
- **Privacy-First Observability**: All analytics are scrubbed of PII and exact coordinates before they leave the device.
- **Offline-First**: SQLDelight handles local persistence, ensuring your intents remain private by default.

## 🚀 Tech Stack
- **Frontend**: Kotlin Multiplatform (KMP), Compose Multiplatform, Material Design 3.
- **Backend**: Ktor 3.0, Micrometer/Prometheus for observability.
- **Persistence**: SQLDelight (Local), PostgreSQL (Backend).
- **Design System**: "The Silent Guardian" (Liquid Glass Aesthetics).

## 📚 Documentation Index
Explore our technical vault to understand the project's inner workings:

### 🎭 Product & Design
- [[Project-Manifesto]] - Our core values and vision.
- [[DESIGN.md]] - Visual identity, design tokens, and branding.
- [[Fuzzy-Grid-Visual-Specifications]] - UI/UX blueprints for the 5 core mobile screens.

### 🏗️ Architecture & Decisions
- [[ADR-005-Local-Persistence-Schema]] - SQLDelight and data modelling logic.
- [[ADR-006-Dependency-Injection-Strategy]] - Koin 4.0 implementation.
- [[ADR-007-Observability-Analytics]] - Privacy-preserving telemetry strategy.
- [[ADR-008-Fuzzy-Grid-Architecture]] - (New) The mathematics of the privacy model.

### 🛠️ Development & Operations
- [[Backend-Runtime-Guide]] - How to run the Ktor server and Prometheus stack.
- [[ROADMAP]] - Current state and future horizons.
- [[Backend-Endpoints]] - API contract between client and server.

---
*Built with precision and care for the digital commons.*