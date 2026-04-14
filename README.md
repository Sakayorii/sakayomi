<p align="center">
  <img src="logo.png" alt="Sakayomi" width="100"/>
</p>

<h1 align="center">Sakayomi</h1>

<p align="center">
  <b>A free, open-source manga reader for Android.</b>
</p>

<p align="center">
  <a href="https://github.com/Sakayorii/sakayomi/releases/latest"><img src="https://img.shields.io/github/v/release/Sakayorii/sakayomi?style=flat-square&color=blue" alt="Release" /></a>
  <a href="https://github.com/Sakayorii/sakayomi/blob/main/LICENSE"><img src="https://img.shields.io/github/license/Sakayorii/sakayomi?style=flat-square" alt="License" /></a>
</p>

---

## About

Sakayomi is a free, open-source manga reader application for Android. Discover, read, and organize manga from a wide variety of sources directly on your device. No ads, no tracking, no compromises.

## Features

- Browse and read manga from hundreds of community-maintained sources
- Automatic library organization with categories and filters
- Configurable reader with multiple viewing modes (left-to-right, right-to-left, vertical, webtoon)
- Track reading progress with AniList, MyAnimeList, Kitsu, MangaUpdates, Shikimori, and Bangumi
- Schedule automatic library updates
- Download chapters for offline reading
- Light and dark themes
- Full backup and restore support
- Extension system for adding new sources

## Requirements

- Android 8.0 (Oreo) or higher
- Android Studio Ladybug+ (for building from source)
- JDK 17+

## Building from Source

```bash
# Clone the repository
git clone https://github.com/Sakayorii/sakayomi.git
cd sakayomi/app

# Build debug APK
./gradlew assembleStandardDebug

# Output: app/build/outputs/apk/standard/debug/app-standard-debug.apk
```

## Project Structure

```
sakayomi/
├── app/                    # Main Android application (Kotlin)
│   ├── app/                # Application module
│   ├── core/               # Core libraries
│   ├── data/               # Data layer
│   ├── domain/             # Domain layer
│   ├── presentation-core/  # UI foundation
│   ├── presentation-widget/ # Home screen widgets
│   ├── source-api/         # Source provider API
│   └── source-local/       # Local file source
├── sources/                # Extension source code (Kotlin)
│   ├── src/                # Extensions organized by language
│   ├── core/               # Shared extension core
│   └── lib-multisrc/       # Multi-source library
├── extensions/             # Pre-built extension APKs
│   ├── apk/                # Extension APK files
│   ├── icon/               # Extension icons
│   └── index.json          # Extension registry
└── libs/                   # Dependency libraries
    ├── extensions-lib/     # Extension API stubs
    └── image-decoder/      # Native image decoder (JPEG/WebP/AVIF/HEIF)
```

## Extensions

Sakayomi supports hundreds of manga sources through its extension system. Extensions are small APK plugins that add new content sources to the app.

### Installing Extensions

1. Open Sakayomi
2. Go to Browse > Extensions
3. Install extensions from the built-in marketplace

### Building Extensions

```bash
cd sources

# Build all extensions
./gradlew assembleDebug

# Build a specific extension
./gradlew :src:en:mangadex:assembleDebug
```

## Credits

Sakayomi is built upon the foundation of [Tachiyomi](https://tachiyomi.org/), the original open-source manga reader created by **inorichi** and maintained by the Tachiyomi community. Tachiyomi was discontinued in early 2024, and its successor [Mihon](https://github.com/mihonapp/mihon) continued development under a new name.

Sakayomi is an independent rebuild by **Sakayori Studio**, rebranded and maintained separately with its own extensions ecosystem and development direction. We are grateful to the original Tachiyomi and Mihon developers and contributors whose work made this project possible.

## Contributing

Contributions are welcome. Please follow these guidelines:

- Use Kotlin for all new code
- Follow the existing code style and architecture
- Test your changes on a real device or emulator
- Submit pull requests against the `main` branch

## License

```
Copyright (C) 2024-2026 Sakayori Studio

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <https://www.gnu.org/licenses/>.
```

**Important:** Under the terms of the GPL-3.0 license:
- You **must** disclose your source code if you distribute modified versions
- You **cannot** sublicense or sell this software as proprietary/closed-source
- You **must** include the original copyright notice and license
- Any derivative work **must** also be licensed under GPL-3.0

Commercial redistribution or resale of this software or its derivatives is prohibited unless explicitly authorized by Sakayori Studio.

---

<p align="center">
  Built by <b>Sakayori Studio</b><br/>
  <a href="https://github.com/Sakayorii">github.com/Sakayorii</a>
</p>
