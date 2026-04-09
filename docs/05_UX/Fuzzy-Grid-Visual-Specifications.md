---
status: 📝 Done
priority: Normal
tags: [ux, design-system, fuzzy-grid, mobile]
---

# Feature: Privacy-First Fuzzy Grid UX

## 🎯 Goal
Implement a high-performance, non-surveillance-based reminder system using the "Fuzzy Grid" privacy model. The UI must feel reliable and technical ("The Silent Guardian") while visualizing proximity without precise GPS pins.

## 🎨 Design System: Liquid Guardian
The design system for **Cart-Near-Me** is built around the **"Liquid Fortress"** creative north star.

- **Archetype**: The Silent Guardian (Sage).
- **Primary Color**: Chambray Navy (#344A86).
- **Primary CTA**: Bright Orange (#FFA500).
- **Background**: Deep Black (#121212) - Optimized for OLED efficiency.
- **Surface**: Gable Green (#153334) for cards and modals.
- **Typography**: 
    - **Headers/Body**: Inter (Editorial & Clean).
    - **Technical Data**: Roboto Mono (Precision & Scrappy Tech vibe).
- **Visual Effects**: Liquid Glass (Refraction + Backdrop Blur). 1px borders are prohibited; separation is achieved through tonal depth and glassmorphism.

## 📱 Mobile Screen Specifications

### 1. Dashboard (The Intent Hub)
- **Purpose**: Vertical list of active reminders ("Intents").
- **Visuals**: Intent cards use Liquid Glass surfaces against a Deep Black background.
- **Data**: Category icons with Saffron Yellow (#FFCE00) highlights.
- **Navigation**: Material 3 Navigation Bar at the bottom.

### 2. Intent Creation (NLP Input)
- **Purpose**: Fast capture of tasks using natural language.
- **Visuals**: Minimalist field with no surrounding box; Gable Green plinth with a 2px Orange bottom indicator on focus.
- **CTA**: High-contrast "Set Intent" button.

### 3. The Fuzzy Grid View (Privacy Core)
- **Purpose**: Visualize proximity without exposing exact coordinates.
- **Visualization**: A 3x3 grid of soft-edged Liquid Glass squares.
- **Logic**: NO GPS PINS. One cell is illuminated in Bright Orange to represent a "Category Match."
- **Overlay**: Subtle Roboto Mono overlay showing `CELL_ID`, `SCAN_MASK`, and `GRID_ENTROPY`.

### 4. Notification (Match Alert)
- **Purpose**: Technical, non-alarming alert for Ktor-based matches.
- **Text**: `Category Match: [Category]. Intent: [NLP Text].`
- **Visuals**: Translucent glass card overlay with technical metadata (e.g., `SYNC_LAG: 42ms`).
- **Action**: Direct CTA to "View on Grid."

### 5. System Status (KMP/Ktor Health)
- **Purpose**: Transparent system monitoring for high-performance reliability.
- **Visuals**: Harbour Blue (#407794) health bars and pulsing Orange sync indicators.
- **Logs**: Monospace scrollable logs showing KMP/Ktor synchronization events.

## 🛠️ Technical Implementation Notes
1. **Redundancy**: Design tokens were applied directly to Stitch generation and consistent across all 5 screen sessions.
2. **Material 3**: All screens utilize Material 3 Expressive tokens for corner radius (16px cards) and bottom navigation bar placement.
3. **Stitch Project ID**: `5741803704511997605`
4. **Asset ID (Design System)**: `623cd520c9894e088b44443ece0e7430`

## 🚦 Acceptance Criteria
- [x] High-contrast Orange CTA buttons (56dp height).
- [x] No 1px solid borders for sectioning.
- [x] Correct font pairings (Inter & Roboto Mono).
- [x] All screens use Bottom Navigation for high reachability.

## 🔗 Related Links
* **Design Guidelines:** [[DESIGN.md]]
* **Foundation Ledger:** [[Phase-1-Foundation-Ledger.md]]
