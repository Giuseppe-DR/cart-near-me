package com.cartnearme.analytics

/**
 * A privacy-first wrapper for PostHog or any other analytics provider.
 * Ensures that explicit lat/lon and detailed addresses never leave the device.
 */
interface PrivacyAnalyticsClient {
    /**
     * Tracks an event with strict PII scrubbing on the properties map.
     */
    fun track(event: String, properties: Map<String, Any> = emptyMap())
}

class PostHogPrivacyAnalyticsClient(
    private val captureMethod: (String, Map<String, Any>) -> Unit
) : PrivacyAnalyticsClient {

    private val piiKeys = setOf(
        "lat", "lng", "latitude", "longitude",
        "address", "radius", "exact_location", "coordinates"
    )

    override fun track(event: String, properties: Map<String, Any>) {
        val scrubbedProperties = properties.mapValues { (key, value) ->
            if (piiKeys.any { key.contains(it, ignoreCase = true) }) {
                "[REDACTED_PII]"
            } else {
                value
            }
        }
        
        captureMethod(event, scrubbedProperties)
    }
}
