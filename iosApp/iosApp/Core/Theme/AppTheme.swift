import SwiftUI

struct AppPalette {
    let primary: Color
    let onPrimary: Color
    let primaryContainer: Color
    let onPrimaryContainer: Color
    let secondaryContainer: Color
    let onSecondaryContainer: Color
    let error: Color
    let background: Color
    let surface: Color
    let surfaceContainer: Color
    let surfaceVariant: Color
    let onSurface: Color
    let onSurfaceVariant: Color
    let outline: Color

    init(colorScheme: ColorScheme) {
        switch colorScheme {
        case .dark:
            primary = Color(hex: 0xFFFFB875)
            onPrimary = Color(hex: 0xFF4C2600)
            primaryContainer = Color(hex: 0xFF713800)
            onPrimaryContainer = Color(hex: 0xFFFFD9B0)
            secondaryContainer = Color(hex: 0xFF52443A)
            onSecondaryContainer = Color(hex: 0xFFFFDCC4)
            error = Color(hex: 0xFFE46962)
            background = Color(hex: 0xFF140C05)
            surface = Color(hex: 0xFF1F1308)
            surfaceContainer = Color(hex: 0xFF24160A)
            surfaceVariant = Color(hex: 0xFF52443A)
            onSurface = Color(hex: 0xFFFFEED6)
            onSurfaceVariant = Color(hex: 0xFFD2C2B4)
            outline = Color(hex: 0xFF9B8C7F)

        default:
            primary = Color(hex: 0xFFAA6B2D)
            onPrimary = Color(hex: 0xFFFFFFFF)
            primaryContainer = Color(hex: 0xFFFFD9B0)
            onPrimaryContainer = Color(hex: 0xFF3A1B00)
            secondaryContainer = Color(hex: 0xFFEEDED1)
            onSecondaryContainer = Color(hex: 0xFF2B1709)
            error = Color(hex: 0xFFE46962)
            background = Color(hex: 0xFFFFF8EF)
            surface = Color(hex: 0xFFFFEED6)
            surfaceContainer = Color(hex: 0xFFFFF3E4)
            surfaceVariant = Color(hex: 0xFFEEDED1)
            onSurface = Color(hex: 0xFF24160A)
            onSurfaceVariant = Color(hex: 0xFF52443A)
            outline = Color(hex: 0xFF867567)
        }
    }
}

private struct AppPaletteKey: EnvironmentKey {
    static let defaultValue = AppPalette(colorScheme: .light)
}

extension EnvironmentValues {
    var appPalette: AppPalette {
        get { self[AppPaletteKey.self] }
        set { self[AppPaletteKey.self] = newValue }
    }
}

struct AppTheme<Content: View>: View {

    @Environment(\.colorScheme)
    private var colorScheme

    private let content: () -> Content

    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }

    var body: some View {
        let palette = AppPalette(colorScheme: colorScheme)

        content()
            .environment(\.appPalette, palette)
            .tint(palette.primary)
            .background(palette.background.ignoresSafeArea())
    }
}

extension Color {
    init(hex: UInt32) {
        let alpha = Double((hex >> 24) & 0xFF) / 255.0
        let red = Double((hex >> 16) & 0xFF) / 255.0
        let green = Double((hex >> 8) & 0xFF) / 255.0
        let blue = Double(hex & 0xFF) / 255.0

        self.init(.sRGB, red: red, green: green, blue: blue, opacity: alpha)
    }
}

struct AppPrimaryButtonStyle: ButtonStyle {

    @Environment(\.appPalette)
    private var palette

    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .font(.system(size: 16, weight: .regular))
            .foregroundStyle(palette.onPrimary)
            .frame(maxWidth: .infinity)
            .padding(.horizontal, 16)
            .padding(.vertical, 12)
            .background(
                RoundedRectangle(cornerRadius: 20, style: .continuous)
                    .fill(palette.primary.opacity(configuration.isPressed ? 0.78 : 1.0))
            )
    }
}

struct AppSecondaryButtonStyle: ButtonStyle {

    @Environment(\.appPalette)
    private var palette

    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .font(.system(size: 16, weight: .regular))
            .foregroundStyle(palette.onSecondaryContainer)
            .frame(maxWidth: .infinity)
            .padding(.horizontal, 16)
            .padding(.vertical, 12)
            .background(
                RoundedRectangle(cornerRadius: 20, style: .continuous)
                    .fill(palette.secondaryContainer.opacity(configuration.isPressed ? 0.78 : 1.0))
            )
    }
}

struct AppIconButton: View {

    let systemName: String
    let accessibilityLabel: String
    let action: () -> Void

    @Environment(\.appPalette)
    private var palette

    var body: some View {
        Button(action: action) {
            Image(systemName: systemName)
                .font(.system(size: 20, weight: .medium))
                .foregroundStyle(palette.onSurface)
                .frame(width: 44, height: 44)
        }
        .accessibilityLabel(accessibilityLabel)
    }
}

struct AppInputField: View {

    let title: String
    let placeholder: String
    @Binding var text: String
    var isError: Bool = false
    var errorText: String?
    var axis: Axis = .horizontal

    @Environment(\.appPalette)
    private var palette

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(.system(size: 16))
                .foregroundStyle(palette.onSurface)
                .padding(.leading, 12)

            TextField(placeholder, text: $text, axis: axis)
                .font(.system(size: 16))
                .foregroundStyle(palette.onSurface)
                .padding(.horizontal, 14)
                .padding(.vertical, 12)
                .background(
                    RoundedRectangle(cornerRadius: 16, style: .continuous)
                        .fill(palette.surface)
                )
                .overlay(
                    RoundedRectangle(cornerRadius: 16, style: .continuous)
                        .stroke(isError ? palette.error : palette.outline, lineWidth: 1)
                )

            if let errorText {
                Text(errorText)
                    .font(.system(size: 14))
                    .foregroundStyle(palette.error)
                    .padding(.leading, 12)
            }
        }
    }
}
