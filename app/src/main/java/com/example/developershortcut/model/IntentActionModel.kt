package com.example.developershortcut.model

import android.content.Intent

data class IntentActionModel(
    val id: Int,
    val icon: Int,
    val name: String,
    val description: String,
    val intent: Intent,
)