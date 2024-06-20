package com.example.developershortcut.model

import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import com.example.developershortcut.R

fun getActions(): List<IntentActionModel> {

    return listOf(
        IntentActionModel(
            id = 1,
            icon = R.drawable.ic_email_24,
            name = "Send Email",
            description = "Open an email client to send an email.",
            intent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("example@example.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Custom Subject")
                putExtra(Intent.EXTRA_TEXT, "Custom Body")
            }
        ),
        IntentActionModel(
            id = 2,
            icon = R.drawable.ic_sms_24,
            name = "Send SMS",
            description = "Open a messaging app to send an SMS.",
            intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:" + Uri.encode("1234567890"))
                putExtra("sms_body", "Hello, this is a test message")
            }
        ),
        IntentActionModel(
            id = 3,
            icon = R.drawable.ic_open_in_browser_24,
            name = "Open Browser",
            description = "Open a web browser with a specific URL.",
            intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.google.com")
            }
        ),
        IntentActionModel(
            id = 4,
            icon = R.drawable.ic_photo_camera_24,
            name = "Capture Image",
            description = "Open the camera to capture an image.",
            intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        ),
        IntentActionModel(
            id = 5,
            icon = R.drawable.ic_image_search_24,
            name = "Pick Image",
            description = "Open the gallery to pick an image.",
            intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
        ),
        IntentActionModel(
            id = 6,
            icon = R.drawable.ic_call_24,
            name = "Dial Number",
            description = "Open the dialer with a specific phone number.",
            intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:1234567890")
            }
        ),
        IntentActionModel(
            id = 7,
            icon = R.drawable.ic_contacts_24,
            name = "Pick Contact",
            description = "Open the contacts app to pick a contact.",
            intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        )
    )
}