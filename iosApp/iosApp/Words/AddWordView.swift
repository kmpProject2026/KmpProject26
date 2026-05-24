import CommonKmp
import SwiftUI

struct AddWordView: View {

    @StateObject
    private var viewModel = IosAddWordViewModel()

    @Environment(\.appPalette)
    private var palette

    let navigateBack: () -> Void

    var body: some View {
        ScrollView {
            VStack(spacing: 16) {
                header

                WordsLanguageDirectionPicker(
                    sourceTitle: "Source language",
                    targetTitle: "Target language",
                    selectedSourceLanguage: viewModel.state.selectedSourceLanguage,
                    selectedTargetLanguage: viewModel.state.selectedTargetLanguage,
                    sourceLanguages: viewModel.state.availableSourceLanguages,
                    targetLanguages: viewModel.state.availableTargetLanguages
                ) { language in
                    viewModel.obtainEvent(
                        AddWordEvent.OnSourceLanguageChanged(language: language)
                    )
                } onTargetSelected: { language in
                    viewModel.obtainEvent(
                        AddWordEvent.OnTargetLanguageChanged(language: language)
                    )
                }

                AppInputField(
                    title: "Word",
                    placeholder: "Enter word",
                    text: Binding(
                        get: { viewModel.state.spelling },
                        set: { viewModel.obtainEvent(AddWordEvent.OnSpellingChanged(spelling: $0)) }
                    ),
                    isError: viewModel.state.spellingError != nil,
                    errorText: viewModel.state.spellingError == nil ? nil : "Word should not be empty"
                )
                .textInputAutocapitalization(.never)
                .autocorrectionDisabled()

                Button {
                    viewModel.obtainEvent(AddWordEvent.OnTranslateClick.shared)
                } label: {
                    HStack {
                        if viewModel.state.isTranslating {
                            ProgressView()
                                .tint(palette.onPrimary)
                        }
                        Text("Translate automatically")
                    }
                }
                .buttonStyle(AppPrimaryButtonStyle())
                .disabled(viewModel.state.isTranslating || viewModel.state.isSaving)
                .opacity(viewModel.state.isTranslating || viewModel.state.isSaving ? 0.62 : 1)

                Link(
                    "Powered by Yandex.Dictionary",
                    destination: URL(string: "http://api.yandex.com/dictionary")!
                )
                .font(.system(size: 12, weight: .medium))
                .foregroundStyle(palette.onSurfaceVariant)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.leading, 12)

                AppInputField(
                    title: "Translation",
                    placeholder: "Enter translation",
                    text: Binding(
                        get: { viewModel.state.translation },
                        set: { viewModel.obtainEvent(AddWordEvent.OnTranslationChanged(translation: $0)) }
                    ),
                    isError: viewModel.state.translationError != nil,
                    errorText: viewModel.state.translationError == nil ? nil : "Translation should not be empty",
                    axis: .vertical
                )
                .lineLimit(2...4)

                Button {
                    viewModel.obtainEvent(AddWordEvent.OnSaveClick.shared)
                } label: {
                    HStack {
                        if viewModel.state.isSaving {
                            ProgressView()
                                .tint(palette.onPrimary)
                        }
                        Text("Save")
                    }
                }
                .buttonStyle(AppPrimaryButtonStyle())
                .disabled(viewModel.state.isTranslating || viewModel.state.isSaving)
                .opacity(viewModel.state.isTranslating || viewModel.state.isSaving ? 0.62 : 1)
            }
            .padding(.horizontal, 20)
            .padding(.bottom, 20)
        }
        .background(palette.background.ignoresSafeArea())
        .navigationBarBackButtonHidden(true)
        .onReceive(viewModel.actions) { action in
            if action is AddWordAction.NavigateBack {
                navigateBack()
            }
        }
        .alert(
            "Error",
            isPresented: Binding(
                get: { viewModel.state.errorDialog != nil },
                set: { isPresented in
                    if !isPresented {
                        viewModel.obtainEvent(AddWordEvent.HideErrorDialog.shared)
                    }
                }
            )
        ) {
            Button("OK", role: .cancel) {
                viewModel.obtainEvent(AddWordEvent.HideErrorDialog.shared)
            }
        } message: {
            Text(errorMessage)
        }
    }

    private var header: some View {
        return HStack {
            AppIconButton(
                systemName: "chevron.left",
                accessibilityLabel: "Back"
            ) {
                navigateBack()
            }

            Text("Add word")
                .font(.system(size: 24, weight: .semibold))
                .foregroundStyle(palette.onSurface)
                .frame(maxWidth: .infinity, alignment: .leading)
        }
        .padding(.vertical, 20)
    }

    private var errorMessage: String {
        if viewModel.state.errorDialog === AddWordErrorDialog.network {
            return "Network error. Check your connection"
        }

        return "An unknown error occurred"
    }
}
