package com.example.developershortcut.model

import com.example.developershortcut.R


fun getShortcuts() = listOf(

    ShortcutModel(
        1,
        R.drawable.ic_settings_24,
        android.provider.Settings.ACTION_SETTINGS,
        "Settings"
    ), ShortcutModel(
        2,
        R.drawable.ic_developer_mode_24,
        android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS,
        "Development Settings"
    ), ShortcutModel(
        3,
        R.drawable.ic_settings_bluetooth_24,
        android.provider.Settings.ACTION_BLUETOOTH_SETTINGS,
        "Bluetooth Settings"
    ), ShortcutModel(
        4,
        R.drawable.ic_location_on_24,
        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS,
        "Location Settings"
    ), ShortcutModel(
        5,
        R.drawable.ic_wifi_24,
        android.provider.Settings.ACTION_WIFI_SETTINGS,
        "WIFI Settings"
    ), ShortcutModel(
        6,
        R.drawable.ic_settings_applications_24,
        android.provider.Settings.ACTION_APPLICATION_SETTINGS,
        "Application Settings"
    ), ShortcutModel(
        7,
        R.drawable.ic_date_range_24,
        android.provider.Settings.ACTION_DATE_SETTINGS,
        "Date Settings"
    ), ShortcutModel(
        8,
        R.drawable.ic_volume_up_24,
        android.provider.Settings.ACTION_SOUND_SETTINGS,
        "Sound Settings"
    ), ShortcutModel(
        9,
        R.drawable.ic_battery_charging_full_24,
        android.provider.Settings.ACTION_BATTERY_SAVER_SETTINGS,
        "Battery Settings"
    ), ShortcutModel(
        10,
        R.drawable.ic_storage_24,
        android.provider.Settings.ACTION_INTERNAL_STORAGE_SETTINGS,
        "Internal Storage Settings"
    ), ShortcutModel(
        11,
        R.drawable.ic_info_24,
        android.provider.Settings.ACTION_DEVICE_INFO_SETTINGS,
        "Device Info"
    ), ShortcutModel(
        12,
        R.drawable.ic_accessibility_24,
        android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS,
        "Accessibility"
    )
)