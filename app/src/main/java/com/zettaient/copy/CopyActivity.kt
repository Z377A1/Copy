package com.zettaient.copy

import android.app.Activity
import android.content.*
import android.net.Uri
import android.os.*
import androidx.core.content.IntentSanitizer

class CopyActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let { handleIntent(getSanitizedIntent(it)) }
        finishAndRemoveTask()
    }

    private fun getSanitizedIntent(inbound: Intent): Intent {
        return IntentSanitizer.Builder()
            .allowAction(Intent.ACTION_SEND)
            .allowAction(Intent.ACTION_SEND_MULTIPLE)
            .allowFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            .allowAnyComponent()
            .allowType { true }
            .allowExtra(Intent.EXTRA_TEXT, String::class.java)
            .allowExtra(Intent.EXTRA_STREAM, Uri::class.java)
            .allowExtra(Intent.EXTRA_STREAM, ArrayList::class.java)
            .build()
            .sanitizeByFiltering(inbound)
    }

    private fun handleIntent(intent: Intent) {
        val clipData: ClipData? = when {
            intent.hasExtra(Intent.EXTRA_TEXT) -> {
                ClipData.newPlainText("Text", intent.getStringExtra(Intent.EXTRA_TEXT))
            }
            intent.hasExtra(Intent.EXTRA_STREAM) -> {
                buildClipDataFromStream(intent)
            }
            else -> null
        }

        clipData?.let {
            triggerHapticFeedback()

            // Service to keep URI permissions alive
            val serviceIntent = Intent(this, CopyService::class.java).apply {
                putExtra("CLIP_DATA", it)
                // Important: Pass the permission flags to the service
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startService(serviceIntent)
        }
    }

    private fun buildClipDataFromStream(intent: Intent): ClipData? {
        val uris = mutableListOf<Uri>()

        // Try to get Multiple URIs (ArrayList)
        val streamArrayList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM)
        }

        if (streamArrayList != null) {
            uris.addAll(streamArrayList)
        } else {
            // Fallback to Single URI
            val singleUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(Intent.EXTRA_STREAM)
            }
            singleUri?.let { uris.add(it) }
        }

        if (uris.isEmpty()) return null

        // 1. Official Android Way: Use ClipData.newUri for automatic MIME sniffing
        // ContentResolver determines the correct MIME type (e.g., image/gif, image/jpeg)
        val clip = ClipData.newUri(contentResolver, "Copied Media", uris[0])

        // 2. Chrome Secret: Add HTML fallback for Gboard/legacy app compatibility
        val htmlContent = "<img src=\"${uris[0]}\">"
        clip.addItem(ClipData.Item(null, htmlContent, null, null))

        // 3. Plain text fallback for text-only apps
        clip.addItem(ClipData.Item(uris[0].toString()))

        // Add extra items for multi-selection (with HTML fallbacks)
        for (i in 1 until uris.size) {
            clip.addItem(ClipData.Item(uris[i]))
            clip.addItem(ClipData.Item(null, "<img src=\"${uris[i]}\">", null, null))
        }

        return clip
    }

    private fun triggerHapticFeedback() {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
        } else {
            @Suppress("DEPRECATION") getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
        vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}