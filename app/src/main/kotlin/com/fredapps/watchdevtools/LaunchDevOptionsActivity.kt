/*
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.fredapps.watchdevtools

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.wear.tiles.TileService

/**
 * No-UI launcher. Fires the Developer options intent and immediately finishes
 * so the user lands directly in Settings -> Developer options, where Wireless
 * debugging is one swipe away. Falls back to the top-level Settings activity if
 * Developer options haven't been unlocked yet (build-number tap x 7).
 *
 * As a side effect, asks the system to re-fetch our tile's content. The Wear
 * OS tile carousel aggressively caches tile layouts; without an explicit
 * requestUpdate the carousel will keep showing the version captured the first
 * time the tile was added, even after we reinstall with new rendering code.
 */
class LaunchDevOptionsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TileService.getUpdater(this).requestUpdate(DevOptionsTileService::class.java)
        val action = supportedSettingsAction(intent.getStringExtra(EXTRA_SETTINGS_ACTION))
        try {
            startActivity(Intent(action).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        } catch (_: ActivityNotFoundException) {
            startActivity(
                Intent(Settings.ACTION_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
        finish()
    }

    companion object {
        /** Tile buttons pass the settings action to launch via this extra. */
        const val EXTRA_SETTINGS_ACTION = "settings_action"

        private val SUPPORTED_SETTINGS_ACTIONS = setOf(
            Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS,
            Settings.ACTION_BLUETOOTH_SETTINGS,
            Settings.ACTION_WIFI_SETTINGS,
        )

        private fun supportedSettingsAction(action: String?): String =
            action
                ?.takeIf { it in SUPPORTED_SETTINGS_ACTIONS }
                ?: Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
    }
}
