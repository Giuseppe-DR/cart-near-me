---
status: Accepted
date: 2026-04-05
tags: [adr, analytics, observability, privacy, kmp]
---

# ADR-007: Privacy-First Observability & Analytics

## Context & Problem
To ensure future growth and stability of `Cart-Near-Me`, we need detailed metrics on both the frontend (User Retention, Reminder Success) and the backend (System Errors, API Latency). However, referencing **Module C (Privacy)**, we have a strict mandate to never ingest or log precise GPS coordinates or Personally Identifiable Information (PII).

We need an architecture that seamlessly balances insightful operations with cryptographic-level data hygiene. Moreover, out of consideration for future open-source adoption and deployment costs, the stack should prioritize free tiers, open-source compatibility, and self-hosting capabilities.

## Decision
We are adopting a segregated "Analytics & Observability" architecture utilizing a combination of PostHog, Micrometer, and Grafana Cloud Free Tier:

1. **Frontend Telemetry (PostHog)**: 
   - We will utilize PostHog's Free Cloud Tier with an override option for users to self-host.
   - We will explicitly configure PostHog with a locally generated **Persistent Anonymous Instance ID** on first install to track User Retention while avoiding PII collection.
   - We will implement a `PrivacyAnalyticsClient` interface mapping exact `track()` events.
   - **Constraint**: The middleware will scrub ALL keys matching generic location regexes (`lat`, `lng`, etc.) before the HTTPS payload is sent.
   - **Compliance**: We classify this under legitimate operation interest. We will forgo an explicit in-app opt-in modal and rely on App Store / Play Store "Data Safety" labels ("Analytics - Not linked to User").

2. **Backend Metrics (Micrometer / Prometheus)**:
   - We will inject Micrometer into Ktor 3.4. Metrics will dimension by HTTP status and URI templates (not absolute paths with parameters).
   - In production, Prometheus will push to **Grafana Cloud Free Tier**, guaranteeing the lowest possible maintenance cost.
   - For local development, `docker-compose.yml` will host Prometheus and Grafana containers.

3. **Backend Log Scrubbing**:
   - `SLF4J`/`Logback` interceptors will mask floating-point coordinate pairs `[REDACTED_COORD]` in all Mapped Diagnostic Contexts to prevent leaks during DB failures.
   - Postgres `log_statement` will avoid full value binding in logs to prevent geographic polygons from landing in plain text.

## Consequences
- **Positive**: Complete peace of mind regarding PII. Even if our cloud provider or PostHog dashboard is compromised, the leaked dataset contains zero actionable geographic vectors.
- **Positive**: Low structural cost. Combining free cloud tiers handles immediate scale.
- **Negative**: Increased complexity. Developers must use `PrivacyAnalyticsClient` rather than invoking the raw PostHog SDK, limiting usage of default auto-capture features.
