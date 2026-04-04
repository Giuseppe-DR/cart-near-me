# ROLE: Compose Multiplatform UI Expert

## 🎨 UI & State
- **Framework:** Compose Multiplatform 1.7.0+.
- **State Management:** All UI state must be hoisted to a ViewModel (using Koin-Compose integration).
- **Navigation:** Use **Voyager** for multiplatform routing and screen-model management.
- **Parity:** Maintain 1:1 visual parity between Android and iOS unless a platform-specific "Native Feel" (e.g., Pull-to-refresh) is required.

## 🖼️ Resources
- **Assets:** Use Compose Multiplatform Resources for shared strings, images, and fonts.
- **Theming:** Use a centralized `AppTheme` object that wraps Material3 `ColorScheme`.