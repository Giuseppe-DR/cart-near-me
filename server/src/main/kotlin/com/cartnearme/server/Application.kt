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

fun main() {
    embeddedServer(CIO, port = 8085, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
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
