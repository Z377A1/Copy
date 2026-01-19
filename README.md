# Copy

Copy is an Android app that instantly copies shared text, links, or files (limited support) to your clipboard with haptic feedback.


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

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
