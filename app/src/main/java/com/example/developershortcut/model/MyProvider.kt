package com.example.developershortcut.model

import com.example.developershortcut.R


fun actions() = listOf(
    ActionModel(
        1,
        R.drawable.ic_settings_24,
        android.provider.Settings.ACTION_SETTINGS,
        "Settings"
    ), ActionModel(
        2,
        R.drawable.ic_developer_mode_24,
        android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS,
        "Development Settings"
    ), ActionModel(
        3,
        R.drawable.ic_settings_bluetooth_24,
        android.provider.Settings.ACTION_BLUETOOTH_SETTINGS,
        "Bluetooth Settings"
    ), ActionModel(
        4,
        R.drawable.ic_location_on_24,
        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS,
        "Location Settings"
    ), ActionModel(
        5,
        R.drawable.ic_wifi_24,
        android.provider.Settings.ACTION_WIFI_SETTINGS,
        "WIFI Settings"
    ), ActionModel(
        6,
        R.drawable.ic_settings_applications_24,
        android.provider.Settings.ACTION_APPLICATION_SETTINGS,
        "Application Settings"
    ), ActionModel(
        7,
        R.drawable.ic_date_range_24,
        android.provider.Settings.ACTION_DATE_SETTINGS,
        "Date Settings"
    ), ActionModel(
        8,
        R.drawable.ic_volume_up_24,
        android.provider.Settings.ACTION_SOUND_SETTINGS,
        "Sound Settings"
    ), ActionModel(
        9,
        R.drawable.ic_battery_charging_full_24,
        android.provider.Settings.ACTION_BATTERY_SAVER_SETTINGS,
        "Battery Settings"
    ), ActionModel(
        10,
        R.drawable.ic_storage_24,
        android.provider.Settings.ACTION_INTERNAL_STORAGE_SETTINGS,
        "Internal Storage Settings"
    )
)