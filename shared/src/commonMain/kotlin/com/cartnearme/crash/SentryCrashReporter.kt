package com.cartnearme.crash

import io.sentry.kotlin.multiplatform.Sentry
import io.sentry.kotlin.multiplatform.SentryLevel

class SentryCrashReporter : CrashReporter {

    override fun initialize(dsn: String) {
        if (dsn.isBlank() || dsn.startsWith("YOUR_") || dsn.contains("placeholder")) return

        Sentry.init { options ->
            options.dsn = dsn
            options.tracesSampleRate = 0.0
        }
    }

    override fun recordException(throwable: Throwable, message: String?) {
        Sentry.captureException(throwable)
    }

    override fun addBreadcrumb(message: String, category: String?, level: BreadcrumbLevel) {
        // Advanced Breadcrumb types (protocol.Breadcrumb) are partially unresolved in this commonMain env
        // Minimal implementation focuses on core crash reporting
    }
}
