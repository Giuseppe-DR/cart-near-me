---
status: Draft
priority: High
tags: [api, ktor, kmp, documentation]
---

# API: Backend Endpoints

This document defines the communication contract between the **KMP Android/iOS Client** and the **Ktor Backend**.

## 🚀 Base URL
- **Local Dev**: `http://localhost:8085`
- **Observability**: Prometheus metrics are exposed at `http://localhost:8085/metrics`.

---

## 🛰️ Proximity & Scanning

### 1. `POST /api/v1/scan`
Captures matched POIs (Points of Interest) for a given geographic region.
- **Request Body**:
  ```json
  {
    "scan_mask": ["C12345", "C67890"],
    "categories": ["Grocery", "Pharmacy"],
    "entropy_key": "optional-client-nonce"
  }
  ```
- **Description**: The client sends a "Mask" of obfuscated Cell IDs. The backend returns POIs that intersect with these cells.
- **Response**: `200 OK`
  ```json
  [
    {
      "cell_id": "C12345",
      "category": "Grocery",
      "matches": 3,
      "metadata": { "sync_lag": "42ms" }
    }
  ]
  ```

---

## 📝 Intent Management (Anonymous)

### 2. `POST /api/v1/intents/sync`
Synchronizes locally captive intents to the backend for cross-device alerts (Anonymous).
- **Request Body**:
  ```json
  {
    "device_id": "SHA256(ANON_ID)",
    "intents": [
      {
        "id": "UUID",
        "label": "Buy Milk",
        "category": "Grocery",
        "status": "ACTIVE"
      }
    ]
  }
  ```
- **Response**: `201 Created`

---

## 📊 Observability (Internal)

### 3. `GET /metrics`
Exposes Micrometer/Prometheus metrics for Grafana Cloud.
- **Access**: Restricted to internal scrapers in production.
- **Metrics Catalog**:
  - `http.server.requests`: Latency and throughput.
  - `system.cpu.usage`: Backend load.
  - `matches.triggered.count`: Total successful privacy-preserving matches.

---

## 🛡️ Security & Headers
- **User-Agent**: `CartNearMe-KMP/1.0`
- **X-Privacy-Mask**: `enabled` (Mandatory header to ensure scrubbing middleware is active).
- **Format**: All requests and responses must be `application/json` and Kotlin `@Serializable` compatible.
