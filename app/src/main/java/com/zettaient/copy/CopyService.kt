package com.zettaient.copy

import android.app.Service
import android.content.*
import android.os.*

class CopyService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        // Safe fetch for API 33+
        val clipData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra("CLIP_DATA", ClipData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent?.getParcelableExtra("CLIP_DATA")
        }

        clipData?.let {
            clipboard.setPrimaryClip(it)
        }

        // Keep process alive for 3 seconds so Gboard/Chrome finish pasting
        Handler(Looper.getMainLooper()).postDelayed({
            stopSelf()
        }, 3000)

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}