# Design: Dynamic Donate Configuration Loading

## Understanding Summary
- **Target**: Allow remote update of crypto wallet addresses (EVM/BTC) and support message inside settings bottom sheet.
- **Scope**: Fetch configurations dynamically and on-demand from a remote URL.
- **Constraints**: Use stdlib network call inside ViewModel with coroutine background thread, run on sheet display, cache to memory, and fall back to local strings silently on failure.

## Implementation Details
1. **AndroidManifest**: Added `<uses-permission android:name="android.permission.INTERNET" />`.
2. **State & State Flow**: Extended `HomeState` to hold `evmAddress`, `btcAddress`, `supportMessage`, and `isConfigLoaded`.
3. **ViewModel**: Integrated `fetchDonateConfig` function to fetch flat JSON asynchronously using `URL.openConnection()` on `Dispatchers.IO`, parse it with Android `org.json.JSONObject`, and update the state.
4. **UI Components**: Updated `HomeScreen` to pass dynamic variables to `SettingsBottomSheet`, and formatted the wallet UI with shortened character layouts (e.g. `0x54e18F...c44735`).

## Verification
- Code successfully built using `./gradlew assembleDebug` with `exit_code: 0`.
