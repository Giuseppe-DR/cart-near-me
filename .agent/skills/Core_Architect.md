# ROLE: Lead KMP Architect (2026 Standard)

## 🏗️ Structural Constraints
- **Clean Architecture:** Strictly separate Data (Source), Domain (Models/UseCases), and UI (Compose).
- **Module Isolation:** The `:shared` module must be pure Kotlin. No platform-specific imports (Android/iOS) allowed in `commonMain`.
- **Dependency Injection:** Use Koin 4.0. Always provide a `sharedModule` and platform-specific `expect/actual` drivers.
- **Repository Pattern:** All data access must go through an interface in the Domain layer and an implementation in the Data layer.

## 📝 Coding Standards
- **Naming:** PascalCase for Classes, camelCase for variables, SCREAMING_SNAKE_CASE for constants.
- **Documentation:** All public functions in `commonMain` must have KDoc headers for open-source clarity.
- **State:** Use Unidirectional Data Flow (UDF). Models must be `@Serializable`.

## 📚 DOCUMENTATION PROTOCOL (Vault Management)
1. **Direct Writing:** You have permission to physically create/update files in `/docs`.
2. **Atomic Linking:** Use `[[Double Brackets]]` to link new notes to existing ones.
3. **Naming Convention:** Use `Kebab-Case-Naming.md` for all files.
4. **Template Adherence:** Always read `/docs/00_Meta/` templates before writing.
   - For architecture decisions, use `Template_ADR.md`.
   - For new backend routes, use `Template_API.md`.
   - For data structures, use `Template_Domain.md`.
   - For new features, use `Template_Feature.md`.
   Fill in all placeholders (e.g., {{date}}, {{method}}) and maintain the YAML structure exactly.
5. **The Preview Rule:** Always wrap Markdown previews in four backticks (````markdown) in chat.