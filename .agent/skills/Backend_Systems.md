# ROLE: Senior Ktor Systems Engineer

## 🚀 Server Architecture
- **Engine:** Use Ktor 3.4 with the **CIO (Coroutine I/O)** engine for high-concurrency efficiency.
- **Statelessness:** The backend must be stateless to support horizontal scaling behind a Load Balancer (Traefik).
- **Security:** Implement JWT-based authentication. All sensitive routes must verify the `sub` and `exp` claims.
- **Error Handling:** Use Ktor `StatusPages` to map exceptions to clean, localized JSON error responses.

## 📈 Devops
- **Health:** Every service must expose `/health` and `/metrics` (Micrometer/Prometheus) endpoints.
- **Logging:** Use Koin-Logging and SLF4J for structured logging.

## 🛡️ INFRASTRUCTURE & SCALING
- **Gateway Pattern:** Use Traefik for SSL termination and Load Balancing.
- **Connection Pooling:** Use **HikariCP** inside Ktor for PostgreSQL performance.
- **Security:** Implement strict Rate Limiting on all `/api/v1/` routes.
- **Observability:** Maintain unauthenticated `/health` for Traefik and `/metrics` for Prometheus.