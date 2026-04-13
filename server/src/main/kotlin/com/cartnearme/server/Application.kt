package com.cartnearme.server

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.calllogging.*
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import org.slf4j.event.Level
import org.koin.ktor.plugin.Koin
import org.koin.dsl.module
import com.cartnearme.di.commonModule
import com.cartnearme.data.DatabaseDriverFactory
import io.sentry.Sentry
import io.sentry.SentryOptions
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*

fun main() {
    val dsn = System.getenv("SENTRY_DSN") ?: "https://your-placeholder-dsn@sentry.io/0"
    
    if (dsn.contains("your-placeholder-dsn")) {
        println("WARNING: Sentry DSN is not configured. Crash reporting will be limited.")
    }

    Sentry.init { options ->
        options.dsn = dsn
        options.tracesSampleRate = 0.0 // Monitoring disabled as per decision
        options.beforeSend = SentryOptions.BeforeSendCallback { event, _ ->
            event.apply {
                message?.let { msg ->
                    msg.formatted = redactSentryMessage(msg.formatted ?: "")
                }
            }
            event
        }
    }

    embeddedServer(CIO, port = 8085, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

private fun redactSentryMessage(message: String): String {
    val coordinateRegex = Regex("[-+]?[0-9]*\\.[0-9]{4,}")
    return message.replace(coordinateRegex, "[REDACTED_COORD]")
}

fun Application.module() {
    install(Koin) {
        modules(commonModule, module {
            single { DatabaseDriverFactory() }
        })
    }



    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

    install(MicrometerMetrics) {
        registry = appMicrometerRegistry
        // dimension metrics by request method and route (ignoring path params)
        meterBinders = listOf(
            io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics(),
            io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics()
        )
    }

    install(CallLogging) {
        level = Level.INFO
        // We will complement this with logback.xml rules for PII scrubbing
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            Sentry.captureException(cause)
            call.respondText(text = "500: Internal Server Error", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        get("/") {
            call.respondText("Cart-Near-Me Backend is running.")
        }

        get("/health") {
            call.respondText("OK")
        }

        get("/metrics") {
            call.respondText(appMicrometerRegistry.scrape())
        }
    }
}
