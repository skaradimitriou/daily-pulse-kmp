package com.example.dailypulse.platform.domain.model

import android.content.res.Resources
import android.os.Build
import kotlin.math.round

internal actual fun getPlatformDetailsList(): List<PlatformInfoItem> = listOf(
    PlatformInfoItem(
        type = InfoType.OS_NAME,
        value = "Android"
    ),
    PlatformInfoItem(
        type = InfoType.OS_VERSION,
        value = Build.VERSION.SDK_INT.toString()
    ),
    PlatformInfoItem(
        type = InfoType.DEVICE_MODEL,
        value = "${Build.MANUFACTURER} ${Build.MODEL}"
    ),
    PlatformInfoItem(
        type = InfoType.SCREEN_DENSITY,
        value = (round(Resources.getSystem().displayMetrics.density).toInt()).toString()
    )
)