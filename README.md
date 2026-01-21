# Copy

<div align="center">

   <img src="app/src/main/ic_launcher-playstore.png" width="200" height="200" alt="App Icon">

   [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
   [![Android](https://img.shields.io/badge/Android-8.0+-green.svg)](https://developer.android.com)
   [![GitHub release](https://img.shields.io/github/v/release/Z377A1/Copy)](https://github.com/Z377A1/Copy/releases)

   Copy is an Android app that instantly copies shared text, links, or files (limited support) to your clipboard with haptic feedback.

   [Download APK](https://github.com/zettaienterprises/Copy/releases)

   ## Demo Videos

   <video width="320" height="240" controls>
   <source src="Promo/copy-promo-16-9.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>

   *16:9 Promo Video*

   <video width="240" height="320" controls>
   <source src="Promo/copy-promo-vertical.mp4" type="video/mp4">
   Your browser does not support the video tag.
   </video>

   *Vertical Promo Video*

</div>

## Table of Contents

- [Supported Android Versions](#supported-android-versions)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Limitations: Copying Images and Files](#limitations-copying-images-and-files)
- [Building from Source](#building-from-source)
- [Release Process](#release-process)
- [Contributing](#contributing)
- [Promo](#promo)
- [License](#license)


## Supported Android Versions

- **Minimum SDK:** 26 (Android 8.0 Oreo)
- **Target SDK:** 36 (Android 14)

## Features

- Share text, links, or files (limited support) to Copy from any app
- Instantly copies content to clipboard
- Haptic feedback for confirmation
- Minimal UI, returns you to your previous app

## Installation

1. Download the APK from the [releases](https://github.com/zettaienterprises/Copy/releases) page.
2. Transfer the APK to your Android device if downloaded in another device/machine.
3. Open the APK file and follow the prompts to install.
   - You may need to enable "Install from unknown sources" for your device to install the Copy APK.

## Usage

- Share any text, link, or file (limited support) from another app to "Copy".
- The content is immediately copied to your clipboard.
- You'll feel a short vibration as confirmation.

## Limitations: Copying Images and Files

**Note:** Only text can be reliably copied to the clipboard for pasting in other apps. While Copy can place file or image URIs on the clipboard, most Android apps (including browsers and messaging apps) only support pasting plain text. System keyboards like Gboard and Samsung Keyboard can copy and paste images because they use private or privileged APIs not available to third-party apps. Due to Android security restrictions, there is no public API for third-party apps to copy images or files to the clipboard in a way that other apps can paste as images. This is a platform limitation, not a bug in Copy.

## Building from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/zettaienterprises/Copy.git
   ```
2. Open the project in Android Studio.
3. Build the project (`Build > Make Project`).
4. Run or generate a release APK via Gradle:
   ```bash
   ./gradlew assembleRelease
   ```

## Release Process

- Releases are triggered by pushing a tag in the format `vX.Y.Z` (e.g., `v1.0.0`).
- The GitHub Actions workflow builds a signed APK and attaches it to the release.
- See release.yml for details.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Promo

Screenshots and promotional images for Copy can be found in the [Promo/screenshots](Promo/screenshots/) directory.

### QR Code

<img src="Promo/qr-code.png" width="150" height="150" alt="QR Code">

*Scan to download the APK*

### Screenshots

<div align="center">

<img src="Promo/screenshots/share-example.png" width="200" alt="Share Example">
<img src="Promo/screenshots/success-vibration.png" width="200" alt="Success Vibration">
<img src="Promo/screenshots/back-to-app.png" width="200" alt="Back to App">

*App Flow: Sharing, Confirmation, and Return*

</div>

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
