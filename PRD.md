# SpecMD - Product Requirements Document

**Version:** 1.0  
**Last Updated:** 23 Juni 2025  
**Author:** Yuken Velino (Curzy)

---

## 1. Concept & Vision

**SpecMD** adalah tools one-tap untuk ekstrak spesifikasi jeroan HP ke format Markdown yang rapi. Tujuannya: devs, tech reviewers, dan seller bisa langsung export "rantai spesifikasi" siap贴在 GitHub README, forum JualBeli, atau blog tanpa perlu ketik manual.

> *"Dari `/proc/cpuinfo` ke clipboard dalam 2 detik."*

### Tagline
```
📱 SpecMD — Device Specs to Markdown
One tap. Endless possibilities.
```

### Target User
- **Android Developer** — cantumin device info di bug report
- **Tech Blogger / YouTuber** — bikin review spec sheet dengan cepat
- **Seller** — kasih detail barang dagangan di forum/OLX
- **Root Users** — flex hardware mereka di Reddit/XDA

---

## 2. Logo & Branding

### Primary Logo
```
┌─────────────────────────────────────┐
│                          [⚙️ Setelan] │  ← Top-right corner
│                                     │
│              📱 SpecMD              │  ← Logo + App Name
│     Device Specs to Markdown        │
│                                     │
│  ┌─────────────────────────────┐   │
│  │                             │   │
│  │        [ logo.png ]         │   │  ← App Icon (108x108)
│  │                             │   │
│  │   📱 + 📋 = 📄 Combination  │   │
│  │                             │   │
│  └─────────────────────────────┘   │
│                                     │
│  [ 📋 Copy ]  [ 💾 Save ]  [ 📤 Share ] │
│                                     │
└─────────────────────────────────────┘
```

### Logo Design Concept
- **Icon:** Smartphone dengan markdown document burst effect
- **Colors:** Cyan (#00D9FF) primary, Dark navy (#1A1A2E) background
- **Style:** Flat, minimal, tech-functional
- **File:** `logo.png` (512x512 source, various densities)

### Logo File Structure
```
app/src/main/res/
├── drawable/
│   ├── logo.png              # 48x48 mdpi
│   ├── logo.xml             # Adaptive icon background
│   └── ic_launcher_foreground.xml
├── mipmap-anydpi-v26/
│   └── ic_launcher.xml       # Adaptive icon
├── mipmap-hdpi/
│   └── ic_launcher.png       # 72x72
├── mipmap-mdpi/
│   └── ic_launcher.png       # 48x48
├── mipmap-xhdpi/
│   └── ic_launcher.png       # 96x96
├── mipmap-xxhdpi/
│   └── ic_launcher.png       # 144x144
└── mipmap-xxxhdpi/
    └── ic_launcher.png       # 192x192
```

---

## 3. UI Layout & Structure

### App Layout Overview
```
┌─────────────────────────────────────┐
│  📱 SpecMD              [⚙️ Setelan] │  ← Top App Bar
├─────────────────────────────────────┤
│                                     │
│  ┌─────────────────────────────┐   │
│  │ 📱 Device Identity         │   │  ← Collapsible Section
│  │ ├─ Model: Pixel 7 Pro      │   │
│  │ ├─ Manufacturer: Google     │   │
│  │ └─ Brand: Google           │   │
│  └─────────────────────────────┘   │
│                                     │
│  ┌─────────────────────────────┐   │
│  │ ⚙️ Hardware                 │   │
│  │ ├─ CPU: Tensor G2          │   │
│  │ ├─ RAM: 12 GB              │   │
│  │ └─ Storage: 256 GB         │   │
│  └─────────────────────────────┘   │
│                                     │
│  ┌─────────────────────────────┐   │
│  │ 📊 Display & Battery       │   │
│  │ ├─ Resolution: 1440x3120   │   │
│  │ ├─ Refresh: 120Hz          │   │
│  │ └─ Battery: 5000 mAh       │   │
│  └─────────────────────────────┘   │
│                                     │
│  ┌─────────────────────────────┐   │
│  │ 🔧 System                  │   │
│  │ ├─ Android: 14 (API 34)    │   │
│  │ └─ Security: Dec 2023      │   │
│  └─────────────────────────────┘   │
│                                     │
├─────────────────────────────────────┤
│  ┌─────────────────────────────┐   │
│  │      [ 📋 Copy to Clipboard ] │   │  ← Primary Action
│  └─────────────────────────────┘   │
│  ┌─────────────────────────────┐   │
│  │      [ 💾 Save as .md File ] │   │  ← Secondary Action
│  └─────────────────────────────┘   │
└─────────────────────────────────────┘
```

### Settings Bottom Sheet
```
┌─────────────────────────────────────┐
│               ▼                     │  ← Drag handle
│                                     │
│  ⚙️ Setelan / Settings              │
│  ─────────────────────────────────  │
│                                     │
│  🌐 Bahasa / Language              │
│     > Indonesia (default)           │
│     > English                      │
│                                     │
│  💝 Support                         │
│     > Buy Me a Coffee              │
│       [💳 EVM / BTC Addresses]     │
│                                     │
│  📋 Template                        │
│     > Customize Markdown Template  │
│                                     │
│  ℹ️ About                           │
│     > Version 1.0.0                │
│     > GitHub: Curzyori/spec-md     │
│                                     │
└─────────────────────────────────────┘
```

---

## 4. Design Language

### Color Palette
```
Background Colors:
├── Primary BG:     #1A1A2E  (Deep Navy)
├── Surface:        #16213E  (Card Background)
├── Surface Variant:#0F0F23  (Elevated cards)
└── Divider:        #2A2A4A  (Subtle borders)

Accent Colors:
├── Primary Accent: #00D9FF  (Cyan - interactive elements)
├── Secondary:      #7B2CBF  (Purple - secondary actions)
├── Tertiary:       #FF6B6B  (Coral - alerts)
└── Gradient:       Linear gradient #00D9FF → #7B2CBF

Text Colors:
├── Text Primary:   #E8E8E8  (Off-white)
├── Text Secondary: #8892B0  (Muted blue-gray)
├── Text Tertiary:  #5A6A8A  (Disabled/hints)
└── Text on Accent: #1A1A2E  (Dark text on cyan buttons)

Semantic Colors:
├── Success:        #00E676  (Green)
├── Warning:        #FFD600  (Yellow)
├── Error:          #FF5252  (Red)
└── Info:           #00D9FF  (Cyan)
```

### Typography
```
Font Families:
├── Primary:  Inter         (UI labels, buttons, headings)
├── Monospace: JetBrains Mono (spec values, markdown code)
└── Fallback:  system-ui, sans-serif

Type Scale:
├── Display:   28sp / Bold    (App title, main header)
├── Headline:  20sp / SemiBold (Section titles)
├── Title:     16sp / Medium   (Card titles)
├── Body:      14sp / Regular  (Content text)
├── Label:     12sp / Medium   (Chip, tab labels)
├── Caption:   11sp / Regular  (Hints, timestamps)
└── Code:      13sp / Regular  (Monospace spec values)

Line Heights:
├── Tight:   1.2  (Headings)
├── Normal:  1.5  (Body)
└── Relaxed: 1.75 (Long content)
```

### Spacing System (8pt Grid)
```
Base Unit: 8dp

Margins:
├── Screen horizontal: 16dp
├── Screen vertical:    24dp
├── Section gap:       24dp
└── Card padding:      16dp

Element Spacing:
├── Tight:   4dp   (icon + label)
├── Normal:   8dp   (list items)
├── Relaxed:  16dp  (between sections)
└── Loose:    24dp  (major divisions)

Border Radius:
├── Small:    4dp   (chips, badges)
├── Medium:   8dp   (buttons, inputs)
├── Large:    12dp  (cards)
└── Full:     999dp (FAB, pills)
```

### Motion Philosophy
```
Duration:
├── Instant:  50ms   (micro interactions)
├── Fast:    150ms  (button feedback)
├── Normal:  250ms  (expansion, transitions)
└── Slow:    400ms  (page transitions, modals)

Easing:
├── Standard:   CubicBezier(0.4, 0, 0.2, 1)  ← Most transitions
├── Decelerate: CubicBezier(0, 0, 0.2, 1)    ← Entering content
├── Accelerate: CubicBezier(0.4, 0, 1, 1)    ← Exiting content
└── Overshoot:  CubicBezier(0.34, 1.56, 0.64, 1) ← Bouncy feedback

Animations:
├── Spec Card Expand: Fade + slide, 200ms
├── Copy Success:      Scale up + checkmark, 300ms
├── Error Shake:      Horizontal shake 3x, 300ms
├── Pull to Refresh:  Circular progress indicator
└── Loading:          Shimmer skeleton (gradient sweep)
```

---

## 5. Features & Interactions

### 5.1 Core Feature: Spec Extraction

#### Data Sources & Fields

| Category | Source | Fields |
|----------|--------|--------|
| **Device Identity** | `android.os.Build` | model, manufacturer, brand, device, product |
| **Android Version** | `android.os.Build.VERSION` | release, sdk, securityPatch, buildId |
| **CPU/Processor** | `/proc/cpuinfo` | processor name, hardware, BogoMIPS, features |
| **Memory/RAM** | `ActivityManager.MemoryInfo` | totalMem, availMem |
| **Storage** | `StatFs` | totalBytes, availableBytes |
| **Display** | `WindowManager + DisplayMetrics` | widthPx, heightPx, densityDpi, refreshRate |
| **Battery** | `BatteryManager` | capacity, level, status, health |
| **Sensors** | `SensorManager` | sensor list (accelerometer, gyroscope, etc.) |
| **Camera** | `CameraManager` | back/front camera resolution, aperture |

#### Extraction Flow
```
App Launch
    ↓
Show Shimmer Skeleton (500ms max)
    ↓
Extract Specs (parallel, coroutines)
    ↓
Populate UI Cards
    ↓
Ready State
```

#### Error Handling
| Scenario | Behavior |
|----------|----------|
| Restricted API (custom ROM) | Show "Limited Access" badge + available fields only |
| Field unavailable | Display "N/A" with muted style + info tooltip |
| /proc/cpuinfo restricted | Fallback to `Build.HARDWARE` |
| Permission denied | Show rationale dialog + settings shortcut |

### 5.2 Export: Markdown Generation

#### Default Template
```markdown
# 📱 Device Specifications

**Generated:** 2024-01-15 14:30:00 WIB

## Device Identity
| Property | Value |
|----------|-------|
| Model | Pixel 7 Pro |
| Manufacturer | Google |
| Brand | Google |
| Device | Cheetah |

## Software
| Property | Value |
|----------|-------|
| Android Version | 14 (API 34) |
| Security Patch | December 5, 2023 |
| Build Number | UP1A.231005.007 |

## Hardware
| Property | Value |
|----------|-------|
| Processor | Tensor G2 |
| RAM | 12 GB |
| Internal Storage | 256 GB |

## Display
| Property | Value |
|----------|-------|
| Resolution | 1440 x 3120 pixels |
| Density | 560 dpi (XXHDPI) |
| Refresh Rate | 120 Hz |

## Battery
| Property | Value |
|----------|-------|
| Capacity | 5000 mAh |
| Status | Charging (75%) |

---
*Exported via SpecMD*
```

### 5.3 Settings Menu

#### Menu Structure
```
┌─────────────────────────────────────┐
│  ⚙️ Setelan / Settings              │
│  ─────────────────────────────────  │
│                                     │
│  🌐 Bahasa / Language         →    │
│     Mengubah bahasa aplikasi        │
│                                     │
│  🎨 Tema / Theme               →    │
│     Gelap / Terang / Sistem         │
│                                     │
│  📋 Template Markdown         →    │
│     Customize export template       │
│                                     │
│  💾 Export History            →    │
│     Lihat file yang sudah di-export│
│                                     │
│  ─────────────────────────────────  │
│                                     │
│  💝 Support / Donate          →    │
│     Dukung pengembangan aplikasi (Crypto) │
│                                     │
│  ℹ️ Tentang / About           →    │
│     GitHub repo dan informasi      │
│                                     │
└─────────────────────────────────────┘
```
┌─────────────────────────────────────┐
│  💝 Support SpecMD!                 │
│                                     │
│  If this app is useful to you,      │
│  support this project with crypto:  │
│                                     │
│  EVM (ETH / BNB / Polygon)          │
│  [ 0x54e18F...c44735 ] [📋 Copy]    │
│                                     │
│  BTC                                │
│  [ bc1q7g...cp7fs ] [📋 Copy]       │
│                                     │
│  Thank you for your support! 🙏     │
└─────────────────────────────────────┘
```

### 5.4 Export Actions

#### Bottom Sheet Options
```
┌─────────────────────────────────────┐
│              ▼ handle               │
│                                     │
│  📤 Export Specifications           │
│  ─────────────────────────────────  │
│                                     │
│  📋 Copy to Clipboard              │
│     Salin ke clipboard             │
│                                     │
│  💾 Save to Documents              │
│     Simpan sebagai file .md        │
│                                     │
│  📁 Save to Downloads              │
│     Simpan ke folder Downloads     │
│                                     │
│  📤 Share via...                   │
│     Bagikan ke aplikasi lain       │
│                                     │
│  👁 Preview Markdown               │
│     Lihat preview sebelum export   │
│                                     │
└─────────────────────────────────────┘
```

#### Success/Error Feedback
| Action | Success | Error |
|--------|---------|-------|
| Copy | "Copied to clipboard!" + green checkmark | "Failed to copy" + retry |
| Save | "Saved to Documents/SpecMD/" | "Storage full" / permission error |
| Share | Opens chooser | "No app available" |

---

## 6. Component Inventory

### 6.1 Top App Bar
```
┌─────────────────────────────────────┐
│  📱 SpecMD          [⚙️ Setelan]   │  ← Settings icon button
└─────────────────────────────────────┘

States:
- Default: App name + settings icon
- With Badge: Update indicator (optional)
- Scrolled: Elevate with shadow
```

### 6.2 Spec Card (Collapsible)
```
┌─────────────────────────────────────┐
│  📱 Device Identity          [▼]  │  ← Chevron rotates on expand
│  ─────────────────────────────────  │
│  │ Model:     │ Pixel 7 Pro        │  │
│  │ Mfg:       │ Google            │  │
│  │ Brand:     │ Google            │  │
│  └────────────────────────────────│  │
└─────────────────────────────────────┘

States:
- Collapsed: Header + item count + chevron
- Expanded: Full spec rows
- Loading: Shimmer skeleton
- Error: Red accent border + retry
```

### 6.3 Spec Row
```
│ Label (14sp)  │ Value (13sp Mono)  │

States:
- Default: Normal text
- Copied: Brief highlight (200ms)
- N/A: Italic + muted + tooltip
- Long Value: Horizontal scroll or truncated + expand
```

### 6.4 Action Button
```
┌─────────────────────────────────────┐
│  [ 📋 ]  Copy to Clipboard         │
└─────────────────────────────────────┘

States:
- Default: Primary cyan bg
- Pressed: Darker cyan + scale 0.98
- Loading: Spinner + "Copying..."
- Success: Green bg + checkmark (1.5s)
- Error: Red bg + shake + "Retry"
```

### 6.5 Settings Bottom Sheet
```
┌─────────────────────────────────────┐
│  ▼ drag handle                      │
│                                     │
│  ⚙️ Setelan                        │
│  ─────────────────────────────────  │
│  🌐 Bahasa                   [→]   │
│  🎨 Tema                      [→]   │
│  📋 Template                 [→]   │
│  💾 Export History           [→]   │
│  ─────────────────────────────────  │
│  💝 Buy Me a Coffee         [→]   │
│  ℹ️ Tentang                    [→]  │
└─────────────────────────────────────┘

Animation: Slide up from bottom, 300ms
Backdrop: 50% black overlay
Dismiss: Tap outside or swipe down
```

### 6.6 Language Selector
```
┌─────────────────────────────────────┐
│  🌐 Pilih Bahasa / Select Language  │
│  ─────────────────────────────────  │
│                                     │
│  🇮🇩 Bahasa Indonesia        [ ● ] │
│     Current / Default               │
│                                     │
│  🇺🇸 English                  [ ○ ] │
│                                     │
└─────────────────────────────────────┘
```

### 6.7 Markdown Preview
```
┌─────────────────────────────────────┐
│  📄 Markdown Preview        [X]    │
├─────────────────────────────────────┤
│  ┌─────────────────────────────┐   │
│  │ # Device Specifications     │   │
│  │                            │   │
│  │ | Model | Pixel 7 |       │   │
│  │ | RAM    | 12 GB    |     │   │
│  │                            │   │
│  │ ─────────────────────────  │   │
│  │ *Exported via SpecMD*      │   │
│  └─────────────────────────────┘   │
├─────────────────────────────────────┤
│  [ 📋 Copy ]  [ 💾 Save ]  [ 📤 ] │
└─────────────────────────────────────┘
```

### 6.8 Toast/Snackbar
```
Position: Bottom, 16dp above action buttons
Duration: 2s (info), 3s (error), 1.5s (success)

Colors:
├── Success: #00E676 background
├── Error:   #FF5252 background
└── Info:    #16213E background

Animation: Slide up + fade in, slide down + fade out
```

---

## 7. Technical Approach

### 7.1 Stack & Dependencies
```
Language:     Kotlin 1.9+
Min SDK:      26 (Android 8.0)
Target SDK:   34 (Android 14)
Compile SDK:  34

UI Framework: Jetpack Compose (Material 3)
Design:       Material 3 Dynamic Colors

Architecture:
├── Pattern:  MVVM + Clean Architecture
├── Layers:   UI → Domain → Data
└── State:    StateFlow + Compose State

Dependencies:
├── Hilt           - Dependency Injection
├── Coroutines     - Async operations
├── Room           - Export history DB
├── DataStore      - Preferences (language, theme)
├── Navigation     - Compose Navigation
└── Accompanist    - System UI controller
```

### 7.2 Project Structure
```
com.curzy.specmd/
├── SpecMDApp.kt                    # Application class
│
├── data/
│   ├── repository/
│   │   ├── SpecRepositoryImpl.kt
│   │   └── ExportRepositoryImpl.kt
│   │
│   ├── source/
│   │   ├── BuildInfoSource.kt      # android.os.Build
│   │   ├── CpuInfoSource.kt        # /proc/cpuinfo
│   │   ├── MemoryInfoSource.kt     # ActivityManager
│   │   ├── StorageInfoSource.kt    # StatFs
│   │   ├── DisplayInfoSource.kt    # WindowManager
│   │   ├── BatteryInfoSource.kt    # BatteryManager
│   │   ├── SensorInfoSource.kt     # SensorManager
│   │   └── CameraInfoSource.kt     # CameraManager
│   │
│   ├── local/
│   │   ├── SpecDatabase.kt         # Room (export history)
│   │   └── PreferencesManager.kt   # DataStore
│   │
│   └── model/
│       ├── DeviceSpec.kt
│       ├── SpecSection.kt
│       └── ExportHistory.kt
│
├── domain/
│   ├── model/
│   │   └── SpecItem.kt
│   ├── repository/
│   │   ├── SpecRepository.kt       # Interface
│   │   └── ExportRepository.kt     # Interface
│   └── usecase/
│       ├── GetDeviceSpecsUseCase.kt
│       ├── ExportToMarkdownUseCase.kt
│       ├── CopyToClipboardUseCase.kt
│       └── SaveToFileUseCase.kt
│
├── ui/
│   ├── MainActivity.kt
│   ├── navigation/
│   │   └── NavGraph.kt
│   │
│   ├── screen/
│   │   └── home/
│   │       ├── HomeScreen.kt
│   │       ├── HomeViewModel.kt
│   │       └── HomeState.kt
│   │
│   ├── components/
│   │   ├── SpecCard.kt
│   │   ├── SpecRow.kt
│   │   ├── ActionButton.kt
│   │   ├── ShimmerLoading.kt
│   │   └── MarkdownPreview.kt
│   │
│   ├── sheet/
│   │   ├── ExportBottomSheet.kt
│   │   └── SettingsBottomSheet.kt
│   │
│   └── theme/
│       ├── Color.kt
│       ├── Type.kt
│       ├── Shape.kt
│       └── Theme.kt
│
├── util/
│   ├── CpuInfoParser.kt
│   ├── MarkdownGenerator.kt
│   ├── FileManager.kt
│   └── ClipboardHelper.kt
│
└── di/
    └── AppModule.kt                # Hilt modules
```

### 7.3 Key Implementation

#### CPU Info Parser
```kotlin
object CpuInfoParser {
    
    fun parseCpuInfo(): CpuSpec {
        val cpuInfo = try {
            File("/proc/cpuinfo").readText()
        } catch (e: Exception) {
            // Fallback for restricted ROMs
            "${Build.HARDWARE} (${Build.SUPPORTED_ABIS.firstOrNull()})"
        }
        
        return CpuSpec(
            processor = extractField(cpuInfo, "model name") 
                ?: extractField(cpuInfo, "Processor")
                ?: Build.HARDWARE,
            hardware = extractField(cpuInfo, "Hardware")
                ?: Build.DEVICE,
            bogomips = extractField(cpuInfo, "BogoMIPS")
                ?: "N/A",
            features = extractCpuFeatures(cpuInfo)
        )
    }
    
    private fun extractField(text: String, key: String): String? {
        return text.lines()
            .find { it.startsWith("$key:") }
            ?.substringAfter(":")
            ?.trim()
    }
}
```

#### RAM Detection
```kotlin
object MemoryInfoSource {
    
    fun getRamInfo(context: Context): RamSpec {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) 
            as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        
        val totalGB = memoryInfo.totalMem / (1024.0 * 1024 * 1024)
        val availGB = memoryInfo.availMem / (1024.0 * 1024 * 1024)
        
        return RamSpec(
            totalGB = String.format("%.1f GB", totalGB),
            availableGB = String.format("%.1f GB", availGB),
            usedPercentage = ((totalGB - availGB) / totalGB * 100).toInt()
        )
    }
}
```

#### Storage Detection
```kotlin
object StorageInfoSource {
    
    fun getStorageInfo(context: Context): StorageSpec {
        val path = Environment.getDataDirectory()
        val statFs = StatFs(path.path)
        
        val totalGB = statFs.totalBytes / (1024.0 * 1024 * 1024)
        val availGB = statFs.availableBytes / (1024.0 * 1024 * 1024)
        
        return StorageSpec(
            totalGB = String.format("%.0f GB", totalGB),
            availableGB = String.format("%.0f GB", availGB),
            usedPercentage = ((totalGB - availGB) / totalGB * 100).toInt()
        )
    }
}
```

#### Markdown Generator
```kotlin
object MarkdownGenerator {
    
    fun generate(spec: DeviceSpec, template: String? = null): String {
        return template ?: buildMarkdown(spec)
    }
    
    private fun buildMarkdown(spec: DeviceSpec): String {
        return buildString {
            appendLine("# 📱 Device Specifications")
            appendLine()
            appendLine("**Generated:** ${LocalDateTime.now()}")
            appendLine()
            
            spec.sections.forEach { section ->
                appendLine("## ${section.icon} ${section.title}")
                appendLine()
                appendLine("| Property | Value |")
                appendLine("|----------|-------|")
                
                section.items.forEach { item ->
                    appendLine("| ${item.label} | ${item.value} |")
                }
                appendLine()
            }
            
            appendLine("---")
            appendLine("*Exported via SpecMD*")
        }
    }
}
```

### 7.4 DataStore Preferences
```kotlin
@Singleton
class PreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val language = dataStore.data.map { it[LANGUAGE_KEY] ?: "id" }
    val theme = dataStore.data.map { it[THEME_KEY] ?: "system" }
    val customTemplate = dataStore.data.map { it[TEMPLATE_KEY] }
    
    suspend fun setLanguage(lang: String) {
        dataStore.edit { it[LANGUAGE_KEY] = lang }
    }
    
    suspend fun setTheme(theme: String) {
        dataStore.edit { it[THEME_KEY] = theme }
    }
}
```

### 7.5 Permissions
```xml
<!-- Tidak perlu permission khusus untuk spec extraction:
     - /proc/cpuinfo: public readable di semua Android
     - ActivityManager: Context.getSystemService()
     - Build fields: always accessible
-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
    android:maxSdkVersion="29" />
<!-- Scoped storage (API 30+) handles file saves automatically -->
```

---

## 8. Localization

### String Resources Structure
```
res/
├── values/strings.xml          # Default (Indonesia)
├── values-en/strings.xml       # English
└── values-id/strings.xml       # Explicit Indonesia
```

### Key Strings (id)
```xml
<!-- App -->
<string name="app_name">SpecMD</string>
<string name="app_tagline">Device Specs to Markdown</string>

<!-- Home -->
<string name="section_device">Device Identity</string>
<string name="section_hardware">Hardware</string>
<string name="section_display">Display</string>
<string name="section_battery">Battery</string>
<string name="section_system">System</string>

<!-- Actions -->
<string name="action_copy">Copy to Clipboard</string>
<string name="action_save">Save as .md</string>
<string name="action_share">Share</string>
<string name="action_preview">Preview</string>

<!-- Settings -->
<string name="settings">Setelan</string>
<string name="settings_language">Bahasa</string>
<string name="settings_theme">Tema</string>
<string name="settings_template">Template</string>
<string name="settings_history">Riwayat Export</string>
<string name="settings_about">Tentang</string>
<string name="settings_support">Support / Donate</string>

<!-- Toasts -->
<string name="toast_copied">Copied to clipboard!</string>
<string name="toast_saved">Saved to Documents/SpecMD/</string>
<string name="toast_error">Something went wrong</string>

<!-- Labels -->
<string name="label_model">Model</string>
<string name="label_manufacturer">Manufacturer</string>
<string name="label_android">Android Version</string>
<string name="label_cpu">Processor</string>
<string name="label_ram">RAM</string>
<string name="label_storage">Storage</string>
<string name="label_resolution">Resolution</string>
<string name="label_battery">Battery</string>
<string name="label_na">N/A</string>
```

### Key Strings (en)
```xml
<string name="app_name">SpecMD</string>
<string name="app_tagline">Device Specs to Markdown</string>

<string name="settings">Settings</string>
<string name="settings_language">Language</string>
<string name="settings_support">Buy Me a Coffee</string>

<string name="toast_copied">Copied to clipboard!</string>
<string name="toast_saved">Saved to Documents/SpecMD/</string>

<string name="label_na">N/A</string>
```

---

## 9. Milestones

### v1.0.0 — MVP
- [ ] Project setup (Compose + Hilt)
- [ ] Spec extraction (all sources)
- [ ] Home screen UI
- [ ] Settings bottom sheet
- [ ] Language toggle (ID/EN)
- [ ] Support / Donate option (Crypto)
- [ ] GitHub repository integration (Curzyori/spec-md)
- [ ] Markdown generation
- [ ] Copy to clipboard
- [ ] Save to file
- [ ] Share intent
- [ ] Dark theme
- [ ] Production build (.apk)

### v1.1.0 — Polish
- [ ] Markdown preview dialog
- [ ] Export history (Room DB)
- [ ] Custom template editor
- [ ] Pull-to-refresh
- [ ] Animations polish
- [ ] Shimmer loading

### v1.2.0 — Expand
- [ ] Sensors list section
- [ ] Camera specs section
- [ ] Theme toggle (Dark/Light/System)
- [ ] Multiple export format (JSON)
- [ ] App icon + adaptive icon

---

## 10. Appendix

### A. Sample Export Output

**Input:** Pixel 7 Pro  
**Output:**
```markdown
# 📱 Device Specifications

**Generated:** 2024-01-15 14:30:00

## Device Identity
| Property | Value |
|----------|-------|
| Model | Pixel 7 Pro |
| Manufacturer | Google |
| Brand | Google |
| Device | Cheetah |

## Software
| Property | Value |
|----------|-------|
| Android Version | 14 (API 34) |
| Security Patch | December 5, 2023 |
| Build Number | UP1A.231005.007 |

## Hardware
| Property | Value |
|----------|-------|
| Processor | Tensor G2 |
| RAM | 12 GB |
| Storage | 256 GB |

## Display
| Property | Value |
|----------|-------|
| Resolution | 1440 x 3120 |
| Refresh Rate | 120 Hz |

## Battery
| Property | Value |
|----------|-------|
| Capacity | 5000 mAh |
| Status | Charging |

---
*Exported via SpecMD*
```

### B. Competitor Analysis
| App | Pros | Cons |
|-----|------|------|
| CPU-Z | Complete hardware info | No markdown export |
| AIDA64 | Very detailed specs | Complex UI, no copy feature |
| Device Info | Decent export | Paid pro version needed |

**SpecMD Advantage:** One-tap export, clean markdown output, free, no ads, localization.

### C. Buy Me a Coffee Integration
```kotlin
const val EVM_ADDRESS = "0x54e18F0345a099D9FE6dd0576bb1699733c44735"
const val BTC_ADDRESS = "bc1q7g5whvwjvrh7mtuap2tu7qh3tyyhvls36cp7fs"
const val GITHUB_REPO_URL = "https://github.com/Curzyori/spec-md"
```

---

**Document Status:** ✅ Complete  
**Ready for Development:** Yes  
**Next Step:** Generate project structure + skeleton code
