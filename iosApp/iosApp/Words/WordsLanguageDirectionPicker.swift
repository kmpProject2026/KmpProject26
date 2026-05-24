import CommonKmp
import SwiftUI

struct WordsLanguageDirectionPicker: View {

    @Environment(\.appPalette)
    private var palette

    let sourceTitle: String
    let targetTitle: String
    let selectedSourceLanguage: WordLanguage
    let selectedTargetLanguage: WordLanguage
    let sourceLanguages: [WordLanguage]
    let targetLanguages: [WordLanguage]
    let onSourceSelected: (WordLanguage) -> Void
    let onTargetSelected: (WordLanguage) -> Void

    var body: some View {
        HStack(spacing: 10) {
            languageMenu(
                title: sourceTitle,
                selectedLanguage: selectedSourceLanguage,
                languages: sourceLanguages,
                onSelected: onSourceSelected
            )

            languageMenu(
                title: targetTitle,
                selectedLanguage: selectedTargetLanguage,
                languages: targetLanguages,
                onSelected: onTargetSelected
            )
        }
    }

    private func languageMenu(
        title: String,
        selectedLanguage: WordLanguage,
        languages: [WordLanguage],
        onSelected: @escaping (WordLanguage) -> Void
    ) -> some View {
        VStack(alignment: .leading, spacing: 6) {
            Text(title)
                .font(.system(size: 12, weight: .medium))
                .foregroundStyle(palette.onSurfaceVariant)
                .lineLimit(1)
                .minimumScaleFactor(0.8)
                .padding(.leading, 8)

            Menu {
                ForEach(languages, id: \.code) { language in
                    Button(language.title) {
                        onSelected(language)
                    }
                }
            } label: {
                HStack(spacing: 6) {
                    Text(selectedLanguage.title)
                        .font(.system(size: 14, weight: .medium))
                        .foregroundStyle(palette.onSurface)
                        .lineLimit(1)
                        .minimumScaleFactor(0.72)
                        .frame(maxWidth: .infinity, alignment: .leading)

                    Image(systemName: "chevron.down")
                        .font(.system(size: 12, weight: .semibold))
                        .foregroundStyle(palette.onSurfaceVariant)
                }
                .padding(.horizontal, 10)
                .padding(.vertical, 10)
                .frame(minHeight: 40)
                .background(
                    RoundedRectangle(cornerRadius: 8, style: .continuous)
                        .fill(palette.surfaceContainer)
                )
                .overlay(
                    RoundedRectangle(cornerRadius: 8, style: .continuous)
                        .stroke(palette.outline.opacity(0.24), lineWidth: 1)
                )
            }
        }
        .frame(maxWidth: .infinity)
    }
}
