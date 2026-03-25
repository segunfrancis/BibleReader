# BibleReader Design System

This project includes a Compose Multiplatform design system implementation under `composeApp/src/commonMain/kotlin/com/blazesoftstudio/biblereader/designsystem`.

## Foundations

- **Color system:** Light and dark schemes are both implemented.
  - Light scheme preserves the tonal sanctuary values from the previous implementation.
  - Dark scheme uses the provided neutral-variant palette with seed `#4A5D50` and the following core tokens: `primary #717972`, `secondary #757874`, `tertiary #5e7e70`, and neutral-driven surfaces.
- **Typography:** Serif defaults for headline/body (targeting Noto Serif) and sans-serif defaults for labels (targeting Inter).
- **Shape:** Moderate rounded corners and pill treatment for primary actions.
- **Spacing:** Balanced spacing scale for readable scripture-first layouts.

## Components

- `BibleReaderPrimaryButton`
- `BibleReaderSecondaryButton`
- `BibleReaderTertiaryButton`
- `BibleReaderGlowCtaBackground`
- `BibleReaderVerseOfDayCard`
- `BibleReaderInputField`
- `BibleReaderFrostedOverlay`
- `BibleReaderScriptureRow`
- `BibleReaderAmbientFabShadow`

## Notes

- `BibleReaderTheme` automatically switches between light and dark using `isSystemInDarkTheme()`.
- UI avoids divider lines and relies on tonal layering and spacing.
