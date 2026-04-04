---
status: Draft # Draft, Active, Deprecated
method: GET # GET, POST, PUT, DELETE, PATCH
route: /api/v1/resource
tags: [api, backend, endpoint]
---

# `{{method}} {{route}}`

> [!INFO] Summary
> [One-sentence description of what this endpoint does, e.g., "Fetches the nearest OSM points based on a category tag."]

## 🔒 Authentication
* **Required:** [Yes/No]
* **Type:** [e.g., JWT Bearer Token]

## 📥 Request

**Parameters / Query Strings:**
* `lat` (Double) - Required. The user's current latitude.
* `lng` (Double) - Required. The user's current longitude.

**Body (if applicable):**
```json
{
  "key": "value"
}
```

## 📤 Response

> [!SUCCESS] 200 OK
```json
{
  "status": "success",
  "data": []
}
```

> [!WARNING] 400 Bad Request / 404 Not Found
```json
{
  "status": "error",
  "message": "Human readable error"
}
```

## 🔗 Related Links
* **Domain Model:** [[Domain-Model-Name]]
* **Implementation:** [Link to GitHub file or local codebase reference]