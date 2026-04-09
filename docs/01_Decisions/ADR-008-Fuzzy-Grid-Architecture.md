---
status: Accepted
date: 2026-04-09
tags: [adr, privacy, geography, fuzzy-grid, mobile]
---

# ADR-008: Fuzzy Grid Privacy Architecture

## Context & Problem
Traditional location-based reminders rely on precise GPS coordinates (lat/lng) being stored or processed in real-time. This creates a high risk for user surveillance and PII (Personally Identifiable Information) leaks. 

For **Cart-Near-Me**, we need a system that can trigger a "Match Alert" when a user is near a destination (e.g., a "Grocery" store) without the app ever holding or transmitting the user's exact precision coordinates.

## Decision
We are implementing the **Fuzzy Grid** privacy model, which replaces precision points with a quantized hexagonal or square grid system.

### 1. Coordinate Quantization
- **Method**: On-device coordinates are immediately converted into 1km x 1km "Cell IDs".
- **Logic**: All internal math (distance calculation, matching) is performed at the Cell level.
- **Data Disposal**: The raw `Location` object is discarded after the Cell ID is computed.

### 2. The 3x3 Interaction Model
- **UI Representation**: When a proximity match is found, the UI displays a 3x3 grid of soft-edged cells.
- **Logic**: One cell is highlighted (the "Match Node") in Bright Orange. This node indicates that the user's current Cell ID intersect with a "POI" (Point of Interest) Cell ID.
- **Entropic Noise**: To prevent reverse-engineering of the user's path, the 3x3 grid does not rotate with the user and utilizes a fixed orientation based on the `CELL_ID` hash.

### 3. Masked Scanning
- **Frontend-Backend Sync**: The app sends a "Scan Mask" (a list of redacted Cell IDs) to the Ktor backend.
- **Obfuscation**: The backend returns potential category matches for the general geographic region without knowing which specific Cell the user is currently standing in.

## Consequences
- **Positive**: Cryptographic Privacy. Even a complete database dump of "Matches" would only show roughly square-kilometer regions.
- **Positive**: Low Battery Impact. Cell-level matching is computationally cheaper than constant high-precision GPS polling.
- **Negative**: Accuracy Trade-off. The alert may trigger slightly early or late (up to 500m-1km variance) compared to a precision GPS app. 
- **Negative**: UI Complexity. Requires the "The Silent Guardian" design to explain this technical trade-off to the user visually.

## Alternatives Considered
- **Circle Geofencing**: Rejected. While standard, it still implies a center-point coordinate that is easily leaked.
- **Differential Privacy (Noise Injection)**: Considered but rejected for Phase 1 as it requires a higher volume of data to remain accurate.
