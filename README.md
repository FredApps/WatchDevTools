# WatchDevTools

Quick-access Wear OS tile for developer settings. Provides shortcut buttons to Developer options, Bluetooth settings, and Wi-Fi settings - all one swipe away without navigating through the full settings menu.

## Features

- **Dev Options Shortcut** - Jump directly to Android Developer settings (for wireless debugging, USB debugging, etc.)
- **Bluetooth Settings** - One-tap access to Bluetooth configuration
- **Wi-Fi Settings** - Quick navigation to Wi-Fi setup

## Installation

Download `WatchDevTools-1.0.apk` from the [WatchDevTools 1.0 release](https://github.com/FredApps/WatchDevTools/releases/tag/1.0).

Install it on a Wear OS watch with wireless debugging:

```bash
adb pair WATCH_IP:PAIRING_PORT
adb connect WATCH_IP:DEBUG_PORT
adb install -r WatchDevTools-1.0.apk
```

If you installed an earlier test build that used the old package name, remove it first:

```bash
adb uninstall com.fredapps.watchdevtools
```

The 1.0 release package is `com.fredapp.watchdevtools`.

## Add The Tile

Add the tile to your Wear OS device manually:

1. Long-press on any existing tile in the tile carousel
2. Tap **Add**
3. Find and select **DevTools**
4. The tile is now in your carousel

No app drawer icon - this is purely a tile-based utility.

## Building

```bash
./gradlew build
```

To build and install directly to a connected watch:

```bash
./gradlew installDebug
```

## Release Builds

Release APKs/AABs should be signed with your private Android signing key. Keep signing properties and keystores outside git; the repository ignores `local.properties`, `*.keystore`, `*.jks`, `*.apk`, and `*.aab`.

The GitHub 1.0 APK is debug-signed for direct sideloading from the release page.

Before publishing a release:

1. Update `versionCode` and `versionName` in `app/build.gradle.kts`
2. Build the release artifact with `./gradlew assembleRelease`
3. Sign the artifact using your local signing configuration

## Configuration

- **Min SDK:** 30 (Wear OS 7.0)
- **Compile SDK:** 36
- **Target SDK:** 35
- **Language:** Kotlin
- **Architecture:** Wear OS Protolayout (tile-based UI)
- **Tested on:** Samsung Galaxy Watch6 Classic LTE SM-R965F

## Development Notes

- The app uses `androidx.wear.tiles` and Protolayout for tile rendering
- Resource caching is managed via `RESOURCES_VERSION`; bump this constant when layout schema changes
- Tile buttons invoke `LaunchDevOptionsActivity`, which routes the appropriate Settings intent and immediately finishes
- No UI is painted (NoDisplay theme) - the launcher activity exists only to bridge tile clicks to system intents

## Package

- **Application ID:** `com.fredapp.watchdevtools`
- **Version:** 1.0.0
- **Namespace:** `com.fredapp.watchdevtools`

## License

WatchDevTools is licensed under the GNU General Public License v3.0. See [LICENSE](LICENSE) for details.
