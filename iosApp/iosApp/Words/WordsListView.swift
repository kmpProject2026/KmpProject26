import CommonKmp
import SwiftUI

struct WordsListView: View {

    @StateObject
    private var viewModel = IosWordsListViewModel()

    @Environment(\.appPalette)
    private var palette

    let navigateBack: () -> Void
    let navigateToAdd: () -> Void

    var body: some View {
        VStack(spacing: 0) {
            header

            languageFilterMenu
                .padding(.horizontal, 20)
                .padding(.bottom, 12)

            if viewModel.state.isLoading {
                Spacer()
                ProgressView()
                    .tint(palette.primary)
                Spacer()
            } else if viewModel.state.words.isEmpty {
                Spacer()
                Text("No words yet")
                    .font(.system(size: 22, weight: .regular))
                    .foregroundStyle(palette.onSurface)
                Spacer()
            } else {
                List {
                    ForEach(viewModel.state.words, id: \.id) { word in
                        wordRow(word)
                            .listRowBackground(palette.background)
                            .listRowSeparatorTint(palette.outline.opacity(0.45))
                    }
                }
                .listStyle(.plain)
                .scrollContentBackground(.hidden)
            }
        }
        .background(palette.background.ignoresSafeArea())
        .navigationBarBackButtonHidden(true)
        .onAppear {
            viewModel.obtainEvent(WordsListEvent.Init.shared)
        }
        .onReceive(viewModel.actions) { action in
            if action is WordsListAction.NavigateBack {
                navigateBack()
            } else if action is WordsListAction.NavigateToAdd {
                navigateToAdd()
            }
        }
        .alert(
            "Error",
            isPresented: Binding(
                get: { viewModel.state.errorDialog != nil },
                set: { isPresented in
                    if !isPresented {
                        viewModel.obtainEvent(WordsListEvent.HideErrorDialog.shared)
                    }
                }
            )
        ) {
            Button("OK", role: .cancel) {
                viewModel.obtainEvent(WordsListEvent.HideErrorDialog.shared)
            }
        } message: {
            Text("An unknown error occurred")
        }
    }

    private var languageFilterMenu: some View {
        WordsLanguageDirectionPicker(
            sourceTitle: "Source language",
            targetTitle: "Target language",
            selectedSourceLanguage: viewModel.state.selectedSourceLanguageFilter,
            selectedTargetLanguage: viewModel.state.selectedTargetLanguageFilter,
            sourceLanguages: viewModel.state.availableSourceLanguageFilters,
            targetLanguages: viewModel.state.availableTargetLanguageFilters
        ) { language in
            viewModel.obtainEvent(
                WordsListEvent.OnSourceLanguageFilterChanged(language: language)
            )
        } onTargetSelected: { language in
            viewModel.obtainEvent(
                WordsListEvent.OnTargetLanguageFilterChanged(language: language)
            )
        }
    }

    private var header: some View {
        return HStack {
            AppIconButton(
                systemName: "chevron.left",
                accessibilityLabel: "Back"
            ) {
                viewModel.obtainEvent(WordsListEvent.OnBackClick.shared)
            }

            Text("All words")
                .font(.system(size: 24, weight: .semibold))
                .foregroundStyle(palette.onSurface)
                .frame(maxWidth: .infinity, alignment: .leading)

            AppIconButton(
                systemName: "plus",
                accessibilityLabel: "Add word"
            ) {
                viewModel.obtainEvent(WordsListEvent.OnAddClick.shared)
            }
        }
        .padding(.horizontal, 20)
        .padding(.vertical, 20)
    }

    private func wordRow(_ word: Word) -> some View {
        return HStack(spacing: 12) {
            VStack(alignment: .leading, spacing: 4) {
                Text(word.spelling)
                    .font(.system(size: 16, weight: .medium))
                    .foregroundStyle(palette.onSurface)

                Text(word.translation)
                    .font(.system(size: 14))
                    .foregroundStyle(palette.onSurfaceVariant)

                Text(word.languagePairTitle)
                    .font(.system(size: 12, weight: .medium))
                    .foregroundStyle(palette.onSurfaceVariant)
            }
            .frame(maxWidth: .infinity, alignment: .leading)

            Button {
                viewModel.obtainEvent(WordsListEvent.OnDeleteClick(wordId: word.id))
            } label: {
                Image(systemName: "xmark.circle")
                    .font(.system(size: 22))
                    .foregroundStyle(palette.onSurfaceVariant)
            }
            .accessibilityLabel("Delete word")
        }
        .padding(16)
        .background(
            RoundedRectangle(cornerRadius: 8, style: .continuous)
                .fill(palette.surfaceContainer)
        )
        .padding(.vertical, 4)
    }

}
