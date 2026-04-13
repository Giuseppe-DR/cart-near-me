package com.cartnearme.crash

/**
 * A privacy-first crash reporter interface for KMP.
 */
interface CrashReporter {
    /**
     * Initializes the crash reporter with a DSN and optional configuration.
     */
    fun initialize(dsn: String)

    /**
     * Records a non-fatal exception with redaction for PII.
     */
    fun recordException(throwable: Throwable, message: String? = null)

    /**
     * Adds a breadcrumb to the current session.
     */
    fun addBreadcrumb(message: String, category: String? = null, level: BreadcrumbLevel = BreadcrumbLevel.INFO)
}

enum class BreadcrumbLevel {
    DEBUG, INFO, WARNING, ERROR
}
