package com.example.developershortcut.model

import android.provider.MediaStore
import com.example.developershortcut.R
import java.util.Collections.list

fun getActions(): List<ActionModel> {

    return listOf(
        ActionModel(
            1,
            R.drawable.ic_settings_24,
            android.content.Intent.ACTION_SEND,
            "Send Email",
            "mailto:example@example.com",
            "subject hey my friend"
        )/*,
    ActionModel(
        2,
        R.drawable.ic_settings_24,
        android.content.Intent.ACTION_SENDTO,
        "Send SMS",
        "mailto:example@example.com",
        "subject"
    ),
    ActionModel(
        3,
        R.drawable.ic_settings_24,
        MediaStore.ACTION_IMAGE_CAPTURE,
        "Settings",
        "mailto:example@example.com",
        "subject"
    ),
    ActionModel(
        4,
        R.drawable.ic_settings_24,
        android.content.Intent.ACTION_VIEW,
        "Settings",
        "mailto:example@example.com",
        "subject"
    ),
    ActionModel(
        5,
        R.drawable.ic_settings_24,
        android.content.Intent.ACTION_PICK,
        "Settings",
        "mailto:example@example.com",
        "subject"
    )*/
    )
}