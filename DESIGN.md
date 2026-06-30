---
version: 1.0
name: SpecMD-Design-System
description: Clean, professional developer tool aesthetic for SpecMD - a device specs-to-markdown utility. Think VS Code meets Notion: light enough to feel approachable, dark enough to feel premium. No flashy AI gradients. Accent color is a sharp electric blue that signals "developer tool" without screaming "generic startup". The UI should feel like a well-crafted CLI wrapped in a nice GUI.
---

## Overview

SpecMD adalah developer utility — audience nya devs, tech reviewers, root users. Desain harus **clean, functional, no-nonsense**. Tidak perlu flashy AI gradient atau neon colors. Lebih ke arah: VS Code, Warp terminal, Linear, atau Vercel dashboard.

**Mood reference:** VS Code dark theme meets Stripe's clarity. Function over decoration. Every element earns its place.

---

## Colors

### Brand & Accent

```
primary: "#3B82F6"       # Electric blue — sharp, developer-tool signal
primary-hover: "#2563EB"  # Darker blue for press state
primary-soft: "#DBEAFE"   # Light blue tint for badges/tags
on-primary: "#FFFFFF"      # White on primary
```

### Background & Surface

```
bg-base: "#0D0D0D"        # Near-black base — not pure black, avoids harshness
bg-elevated: "#141414"   # Card surfaces — subtle elevation
bg-overlay: "#1A1A1A"    # Bottom sheets, modals
bg-input: "#1F1F1F"      # Input fields
```

### Borders & Dividers

```
border: "#262626"         # Subtle borders — visible but not loud
border-hover: "#333333"   # Hover state borders
```

### Text

```
text-primary: "#FAFAFA"   # Primary text — near white
text-secondary: "#A1A1AA" # Secondary/muted text — zinc-400
text-tertiary: "#71717A"  # Tertiary/disabled — zinc-500
text-on-accent: "#FFFFFF" # Text on blue accent
```

### Semantic

```
success: "#22C55E"       # Green — copy success, saved
warning: "#F59E0B"       # Amber — caution states
error: "#EF4444"         # Red — errors
info: "#3B82F6"          # Blue — informational (same as primary)
```

### Shadows (minimal)

```
shadow-sm: "0 1px 2px rgba(0,0,0,0.3)"
shadow-md: "0 4px 12px rgba(0,0,0,0.4)"
```

---

## Typography

### Font Stack

```
display: "Inter, -apple-system, BlinkMacSystemFont, 'Segoe UI', system-ui, sans-serif"
mono: "'JetBrains Mono', 'SF Mono', 'Fira Code', monospace"
```

### Scale

```
display-lg: 28px / 600 / -0.5px tracking  # App title
display-md: 24px / 600 / -0.3px tracking  # Section headers
title-lg: 18px / 500 / 0                 # Card titles
title-md: 16px / 500 / 0                 # Labels
body-md: 14px / 400 / 0                 # Body text
body-sm: 13px / 400 / 0                 # Secondary text
caption: 12px / 400 / 0                 # Hints, timestamps
code: 13px / 400 / 0 monospace          # Spec values, code
```

### Principles

- **No decorative fonts.** Inter only — clean, professional, designed for UI.
- **No exaggerated letter-spacing.** Tight tracking on headlines, none on body.
- **Mono for data.** Spec values always render in JetBrains Mono — reinforces "developer tool" identity.
- **Contrast over color for hierarchy.** Use text-primary vs text-secondary, not color.

---

## Spacing (8pt Grid)

```
space-1: 4px   # Tight icon gaps
space-2: 8px   # List items, compact padding
space-3: 12px  # Standard element spacing
space-4: 16px  # Card padding, section gaps
space-5: 20px  # Section dividers
space-6: 24px  # Major divisions
space-8: 32px  # Screen padding
```

---

## Border Radius

```
radius-sm: 6px   # Buttons, inputs
radius-md: 8px   # Cards, containers
radius-lg: 12px  # Bottom sheets, modals
radius-full: 9999px  # Pills, badges
```

---

## Motion

### Duration

```
instant: 50ms    # Micro interactions
fast: 150ms      # Button feedback
normal: 250ms    # Card expansion, transitions
slow: 350ms      # Bottom sheets, modals
```

### Easing

```
ease-out: cubic-bezier(0, 0, 0.2, 1)     # Exiting content
ease-in: cubic-bezier(0.4, 0, 1, 1)     # Entering content
ease-in-out: cubic-bezier(0.4, 0, 0.2, 1) # Standard transitions
```

### Principles

- **Motion is functional, not decorative.** Animations confirm state changes (copied, saved) or guide attention (bottom sheet slide up).
- **No bounce.** This isn't a consumer app. Motion is crisp, decisive.
- **Fast by default.** Developers are impatient.

---

## Components

### Top App Bar

```
Height: 56px
Background: transparent (bleeds to bg-base)
Left: App icon (24px) + "SpecMD" in text-primary
Right: Settings icon button (40px tap target)
Border: none
```

### Spec Card (Collapsible)

```
Background: bg-elevated
Border: 1px border color
Border-radius: radius-md (8px)
Padding: space-4 (16px)
Header: icon (emoji) + title in title-lg + chevron
Expanded: Spec rows inside
States:
  - Default: border color
  - Hover: border-hover (subtle lift signal)
  - Expanded: rotate chevron 90deg, 200ms
  - Loading: shimmer skeleton
```

### Spec Row

```
Layout: flex row, space-between
Label: body-sm in text-secondary
Value: code in text-primary (mono font)
Padding: space-2 vertical (8px)
Divider: 1px border color between rows
N/A state: text-tertiary italic
```

### Primary Action Button

```
Background: primary (#3B82F6)
Text: text-on-accent (white), title-md
Border-radius: radius-sm (6px)
Padding: 12px 20px
Height: 48px
Full-width at bottom of screen
States:
  - Default: primary blue
  - Hover: primary-hover (darker)
  - Pressed: scale 0.98
  - Success: success green (#22C55E) + checkmark icon
  - Disabled: 50% opacity
```

### Bottom Sheet

```
Background: bg-overlay
Border-radius: radius-lg top corners only
Backdrop: rgba(0,0,0,0.6)
Slide up animation: 350ms ease-out
Drag handle: 32px x 4px centered, bg-border color
Padding: space-4 (16px)
```

### Settings Menu Item

```
Layout: flex row, align center
Icon: 20px, text-secondary
Title: body-md text-primary
Subtitle: body-sm text-secondary
Chevron: text-tertiary
Tap area: full width, space-3 padding vertical
Divider between items: 1px border
```

### Copy Address Button (Support)

```
Compact button: 32px height
Background: success green on copy, primary blue default
Icon: copy icon 14px
Label: body-sm
Border-radius: radius-sm
Animation: 150ms color transition
```

### Toast / Snackbar

```
Background: bg-overlay
Text: text-primary
Position: bottom, 16px above action button
Border-radius: radius-sm
Duration: 2s (info), 3s (error)
Success variant: success green left border
```

### Language Selector

```
Radio-style selection
Current: filled dot in primary blue
Other: empty circle
Layout: stacked, full-width
Active row: bg-input background
```

---

## Layout

### Screen Structure

```
Safe area padding top + bottom
Horizontal padding: space-4 (16px)
Content: scrollable LazyColumn
Cards: 12px gap between cards
Bottom: fixed action button area (80px reserved)
```

### Spec Sections Order

1. Device Identity (📱)
2. Software (🔧)
3. Hardware (⚙️)
4. Display (📊)
5. Battery (🔋)

---

## Settings Bottom Sheet Structure

```
Header: ⚙️ Setelan / Settings

Section 1: 🌐 Bahasa / Language
  ├─ 🇮🇩 Bahasa Indonesia (●)
  └─ 🇺🇸 English (○)

Section 2: 📤 Export Specifications
  ├─ Copy to Clipboard
  ├─ Save to Documents
  ├─ Share via...
  └─ Preview Markdown

Section 3: 💝 Support / Donate
  ├─ EVM Address + Copy button
  └─ BTC Address + Copy button

Section 4: ℹ️ Tentang / About
  ├─ GitHub Repository (opens link)
  └─ Version 1.0.0
```

---

## Dark Theme Philosophy

- **Near-black, not pure black.** #0D0D0D vs #000000 — avoids harshness while maintaining dark aesthetic.
- **Subtle borders over shadows.** Cards defined by 1px borders, not drop shadows. Cleaner, more "IDE-like".
- **Electric blue accent.** Single accent color — no purple, no gradient. Confident, developer-tool identity.
- **Mono for data.** Spec values in JetBrains Mono reinforces the technical nature without being gimmicky.
- **Green for success.** Copy confirmation uses green (#22C55E) — universal "done" signal.
- **No decorative elements.** No gradients, no glows, no noise textures. Clean and functional.

---

## Anti-Patterns (Avoid)

- ❌ Purple/gradient backgrounds — too "AI startup"
- ❌ Neon accents — gimmicky, not professional
- ❌ Rounded everything — buttons stay sharp (6px), cards medium (8px)
- ❌ Decorative illustrations or mascot icons
- ❌ Color-heavy states — hierarchy through contrast, not hue
- ❌ Bouncy/spring animations — this is a tool, not a game
- ❌ Light theme (yet) — dark base is the developer default

---

## Implementation Notes

### Color Token Naming

Use semantic tokens that describe function, not appearance:
- `color.surface.primary` not `color.blue.500`
- `color.text.secondary` not `color.gray.400`

### Compose Material 3

```kotlin
// Custom color scheme extending Material 3 dark theme
private val SpecMDDarkColorScheme = darkColorScheme(
    primary = Color(0xFF3B82F6),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF1E3A5F),
    onPrimaryContainer = Color(0xFFDBEAFE),
    secondary = Color(0xFF3B82F6),  // Use primary only
    background = Color(0xFF0D0D0D),
    surface = Color(0xFF141414),
    surfaceVariant = Color(0xFF1A1A1A),
    onBackground = Color(0xFFFAFAFA),
    onSurface = Color(0xFFFAFAFA),
    onSurfaceVariant = Color(0xFFA1A1AA),
    outline = Color(0xFF262626),
    error = Color(0xFFEF4444),
    onError = Color(0xFFFFFFFF)
)
)
```

### Typography

```kotlin
val SpecMDTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        letterSpacing = (-0.5).sp,
        lineHeight = 36.sp
    ),
    // ... etc
)

val CodeTypography = TextStyle(
    fontFamily = FontFamily.Monospace,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    lineHeight = 20.sp
)
```
