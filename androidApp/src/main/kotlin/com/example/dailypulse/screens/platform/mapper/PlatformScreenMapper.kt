package com.example.dailypulse.screens.platform.mapper

import com.example.dailypulse.R
import com.example.dailypulse.platform.domain.model.InfoType.DEVICE_MODEL
import com.example.dailypulse.platform.domain.model.InfoType.OS_NAME
import com.example.dailypulse.platform.domain.model.InfoType.OS_VERSION
import com.example.dailypulse.platform.domain.model.InfoType.SCREEN_DENSITY
import com.example.dailypulse.platform.domain.model.PlatformInfoItem

internal fun PlatformInfoItem.getStringResourceId(): Int = when (type) {
    OS_NAME -> R.string.platform_os_name_item_label
    OS_VERSION -> R.string.platform_os_version_item_label
    DEVICE_MODEL -> R.string.platform_device_item_label
    SCREEN_DENSITY -> R.string.platform_density_item_label
}