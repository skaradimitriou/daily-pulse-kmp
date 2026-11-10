package com.example.dailypulse.platform.domain.model


data class PlatformInfoItem(
    val type: InfoType,
    val value: String
)

enum class InfoType {
    OS_NAME,
    OS_VERSION,
    DEVICE_MODEL,
    SCREEN_DENSITY
}
