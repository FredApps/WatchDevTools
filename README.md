# WatchDevTools

Quick-access Wear OS tile for developer settings. Provides shortcut buttons to Developer options, Bluetooth settings, and Wi-Fi settings — all one swipe away without navigating through the full settings menu.

## Features

- **Dev Options Shortcut** — Jump directly to Android Developer settings (for wireless debugging, USB debugging, etc.)
- **Bluetooth Settings** — One-tap access to Bluetooth configuration
- **Wi-Fi Settings** — Quick navigation to Wi-Fi setup

## Installation

Add the tile to your Wear OS device manually:

1. Long-press on any existing tile in the tile carousel
2. Tap **Add**
3. Find and select **DevTools**
4. The tile is now in your carousel

No app drawer icon—this is purely a tile-based utility.

## Building

```bash
./gradlew build
```

To build and install directly to a connected watch:

```bash
./gradlew installDebug
```

## Configuration

- **Min SDK:** 30 (Wear OS 7.0)
- **Compile SDK:** 36
- **Target SDK:** 35
- **Language:** Kotlin
- **Architecture:** Wear OS Protolayout (tile-based UI)

## Development Notes

- The app uses `androidx.wear.tiles` and Protolayout for tile rendering
- Resource caching is managed via `RESOURCES_VERSION`; bump this constant when layout schema changes
- Tile buttons invoke `LaunchDevOptionsActivity`, which routes the appropriate Settings intent and immediately finishes
- No UI is painted (NoDisplay theme)—the launcher activity exists only to bridge tile clicks to system intents

## Package

- **Application ID:** `com.fredapps.watchdevtools`
- **Version:** 0.1.0
- **Namespace:** fredapps

## License

Copyright (C) 2026 FredApps

WatchDevTools is licensed under the GNU General Public License v3.0. See [LICENSE](LICENSE) for details.
