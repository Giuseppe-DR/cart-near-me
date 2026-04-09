# Product Design System & Constraints

This document defines the visual and functional design constraints for the **Cart-Near-Me** application. These constraints will guide UI generation and styling via **StitchMCP**.

---

## 🎨 Visual Identity & Aesthetic
A stoic, reliable, and technically precise interface that emphasizes help without surveillance.

- Theme Style: Premium Dark Mode (OLED Efficiency default) with a "Digital Commons" aesthetic.
- Design Philosophy: Material Design 3 (Expressive Theme) with Liquid Glass refraction effects for category depth.
- Core Intent: Proactive assistance using "Fuzzy Grid" logic. The UI should look like a scientific tool or a high-performance instrument rather than a social app.

## 🌈 Color Palette
*Use HEX codes or descriptive names that can be mapped to Design Tokens.*

Role                | Color (HEX/Name) | Context / Usage
Primary             | #344A86          | Chambray Navy: Key actions and brand identity (Trust & Security)
Secondary           | #407794          | Harbour Blue: Mapping context and secondary UI elements
Background          | #121212          | Deep Black: Primary background for high-contrast OLED efficiency
Surface             | #153334          | Gable Green: Cards, bottom sheets, and contrast sections
Success / Action    | #FFA500          | Bright Orange: Active ""Intent Triggers"" and primary CTA buttons
Warning / Alert     | #FFCE00          | Saffron Yellow: Category highlights and non-critical system status
Neutral Text        | #E1DDD6          | Silver Feather: High-readability off-white for primary content
Subtle Borders      | #4B4945          | Charred Gray: UI borders, dividers, and grid line definitions

## 🅰️ Typography
Role-based scaling optimized for legibility and technical precision.

- Primary Font: Inter (Sans-serif) - Used for all headers and body content.
- Secondary Font: Roboto Mono (Monospace) - Used for coordinates, Ktor sync logs, and grid data.
- Body Text: Size: 16px, Line height: 1.5. Use "BodyLarge" for primary task lists.
- Headings: Bold weights (700). Use "DisplayMedium" for "The Silent Guardian" hero headers.

## 📐 Layout & Shapes
Consistent structures that reflect the "Grid" logic of the privacy model.

- Corner Radius: 16px for cards and modals to match Material 3; 12px for buttons.
- Grid System: 8pt base grid; 4-column mobile layout with a focus on the "Comfortable Reach Zone" (bottom 75% of screen).
- Spacing/Padding: Standard increments: 8, 16, 24, 32.
- Shadows: Low elevation (Flat design). Use depth through "Liquid Glass" translucency rather than heavy drop shadows.

## 🧩 Component Styles
Specific rules for the KMP + Compose Multiplatform frontend.

- Buttons: High-contrast Bright Orange backgrounds with dark text for CTAs. 56dp height for primary mobile actions.
- Cards: Material 3 OutlinedCard style. Use Gable Green (#153334) for card surfaces with Saffron Yellow icons.
- Input Fields: Minimalist with floating labels; optimized for Natural Language Processing (NLP) task entry.
- Animations: Use Spring Physics for list transitions. "Intent Matches" should use a "Pulse" or "Ripple" animation instead of a static pin.

---

## 🤖 StitchMCP Integration Instructions
When providing prompts to StitchMCP tools, follow these rules derived from this document:

- Theme Alignment: Always specify "aligned with DESIGN.md using Material 3 Expressive tokens and Liquid Glass surfaces."
- Design MD Snippet: Use the following markdown block for StitchMCP's designMd parameter when updating Design Systems:
```markdown
Archetype: The Silent Guardian (Sage) 

Palette: #344A86 (Navy), #FFA500 (Orange), #121212 (Dark BG), #153334 (Gable Green) 

Fonts: Inter (Headers/Body), Roboto Mono (Grid Coordinates/Data)

Corner Radius: 16px (Cards), 12px (Buttons)

Privacy Rule: NO GPS PINS. Use "Intent Nodes" (Glowing Pulses) or 3x3 Grid Cell Highlighting.

Visual Logic: Liquid Glass refraction effects for depth.

Architecture: Compose Multiplatform + Ktor 3.0.
```
