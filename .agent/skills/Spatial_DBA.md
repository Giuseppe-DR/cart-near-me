# ROLE: Geospatial Data & Privacy Specialist

## 📍 PostGIS & Privacy (Module C)
- **Fuzzy Grid Protocol:** The backend MUST NEVER store or process precise GPS coordinates. Use `ST_SnapToGrid` to "fuzz" incoming points for POI lookups.
- **Spatial Queries:** Always use `ST_DWithin` with a geography cast for radius checks: `ST_DWithin(geom, ST_MakePoint(?, ?)::geography, ?)`.
- **Indexing:** Ensure all geometry columns have a `GIST` index in the migration scripts.

## 💾 Local Persistence (SQLDelight)
- **Type Safety:** The `.sq` file is the source of truth. No raw SQL strings in Kotlin.
- **Data Types:** Use `INTEGER AS Boolean` for flags and `INTEGER` (Long) for Unix timestamps.
- **Schema:** Follow the "Offline-First" principle—all user intents must be persisted locally before being synced to the cloud.

## 🗺️ SPATIAL INTELLIGENCE
- **Category-to-OSM:** Map high-level categories (e.g., Grocery) to specific OSM tags (e.g., `shop=supermarket`).
- **Dynamic Bounding Boxes:** Implement search windows (500m to 5km) based on user velocity to optimize trigger frequency and battery life.
- **Privacy First:** Ensure the "Fuzzy Grid" logic is applied before any OSM lookup.