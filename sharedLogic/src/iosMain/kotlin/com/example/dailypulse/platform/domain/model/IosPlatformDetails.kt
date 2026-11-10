package com.example.dailypulse.platform.domain.model

import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

internal actual fun getPlatformDetailsList(): List<PlatformInfoItem> =
    with(UIDevice.currentDevice) {
        listOf(
            PlatformInfoItem(
                type = InfoType.OS_NAME,
                value = systemName
            ),
            PlatformInfoItem(
                type = InfoType.OS_VERSION,
                value = systemVersion
            ),
            PlatformInfoItem(
                type = InfoType.DEVICE_MODEL,
                value = model
            ),
            PlatformInfoItem(
                type = InfoType.SCREEN_DENSITY,
                value = (UIScreen.mainScreen.scale.toInt()).toString()
            )
        )
    }