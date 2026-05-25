import CommonKmp
import SwiftUI

struct WordsCardsView: View {

    @StateObject
    private var viewModel = IosWordsCardsViewModel()

    @GestureState
    private var dragOffset: CGFloat = 0

    @State
    private var isShowingTranslation = false

    @State
    private var flyOutOffset: CGSize = .zero

    @State
    private var flyOutRotation: Double = 0

    @State
    private var isCardFlyingOut = false

    @Environment(\.appPalette)
    private var palette

    let navigateToAdd: () -> Void
    let navigateToList: () -> Void

    var body: some View {
        VStack(spacing: 0) {
            header

            content
        }
        .padding(.horizontal, 20)
        .padding(.bottom, 20)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(palette.background.ignoresSafeArea())
        .navigationBarBackButtonHidden(true)
        .onAppear {
            viewModel.obtainEvent(WordsCardsEvent.Init.shared)
        }
        .onChange(of: viewModel.state.currentIndex) { _, _ in
            isShowingTranslation = false
        }
        .onChange(of: viewModel.state.words.count) { _, _ in
            resetFlyOutState()
        }
        .onReceive(viewModel.actions) { action in
            if action is WordsCardsAction.NavigateToAdd {
                navigateToAdd()
            } else if action is WordsCardsAction.NavigateToList {
                navigateToList()
            }
        }
        .alert(
            "Error",
            isPresented: Binding(
                get: { viewModel.state.errorDialog != nil },
                set: { isPresented in
                    if !isPresented {
                        viewModel.obtainEvent(WordsCardsEvent.HideErrorDialog.shared)
                    }
                }
            )
        ) {
            Button("OK", role: .cancel) {
                viewModel.obtainEvent(WordsCardsEvent.HideErrorDialog.shared)
            }
        } message: {
            Text("An unknown error occurred")
        }
    }

    private var header: some View {
        return HStack {
            Text("Cards")
                .font(.system(size: 24, weight: .semibold))
                .foregroundStyle(palette.onSurface)
                .frame(maxWidth: .infinity, alignment: .leading)

            AppIconButton(
                systemName: "list.bullet",
                accessibilityLabel: "All words"
            ) {
                viewModel.obtainEvent(WordsCardsEvent.OnListClick.shared)
            }

            AppIconButton(
                systemName: "plus",
                accessibilityLabel: "Add word"
            ) {
                viewModel.obtainEvent(WordsCardsEvent.OnAddClick.shared)
            }
        }
        .padding(.top, 20)
        .padding(.bottom, 20)
    }

    @ViewBuilder
    private var content: some View {
        if viewModel.state.isLoading {
            Spacer()
            ProgressView()
                .tint(palette.primary)
            Spacer()
        } else {
            VStack(spacing: 16) {
                languageFilterMenu

                if viewModel.state.currentWord != nil {
                    Spacer(minLength: 8)

                    swipeGuide

                    cardDeck

                    Text("\(viewModel.state.currentIndex + 1) / \(viewModel.state.words.count)")
                        .font(.system(size: 14, weight: .medium))
                        .foregroundStyle(palette.onSurfaceVariant)

                    Spacer(minLength: 12)
                } else {
                    Spacer()

                    Text("No words yet")
                        .font(.system(size: 22, weight: .regular))
                        .foregroundStyle(palette.onSurface)

                    Button("Add") {
                        viewModel.obtainEvent(WordsCardsEvent.OnAddClick.shared)
                    }
                    .buttonStyle(AppPrimaryButtonStyle())
                    .frame(maxWidth: 220)

                    Spacer()
                }
            }
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
                WordsCardsEvent.OnSourceLanguageFilterChanged(language: language)
            )
        } onTargetSelected: { language in
            viewModel.obtainEvent(
                WordsCardsEvent.OnTargetLanguageFilterChanged(language: language)
            )
        }
    }

    private var swipeGuide: some View {
        return HStack(spacing: 8) {
            swipeGuideItem(
                action: .repeatLater,
                systemName: "arrow.left",
                title: "Повторить"
            )

            swipeGuideItem(
                action: .remember,
                systemName: "arrow.right",
                title: "Помню"
            )
        }
        .padding(6)
        .background(
            RoundedRectangle(cornerRadius: 8, style: .continuous)
                .fill(palette.surfaceContainer)
        )
        .overlay(
            RoundedRectangle(cornerRadius: 8, style: .continuous)
                .stroke(palette.outline.opacity(0.18), lineWidth: 1)
        )
        .animation(.easeInOut(duration: 0.16), value: activeSwipeAction)
    }

    private func swipeGuideItem(
        action: CardSwipeAction,
        systemName: String,
        title: String
    ) -> some View {
        let isActive = activeSwipeAction == action

        return HStack(spacing: 6) {
            Image(systemName: systemName)
                .font(.system(size: 13, weight: .semibold))

            Text(title)
                .font(.system(size: 14, weight: .semibold))
                .lineLimit(1)
                .minimumScaleFactor(0.82)
        }
        .foregroundStyle(isActive ? palette.onPrimary : palette.onSurfaceVariant)
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 10)
        .padding(.vertical, 9)
        .background(
            RoundedRectangle(cornerRadius: 8, style: .continuous)
                .fill(isActive ? swipeGuideColor(for: action) : Color.clear)
        )
    }

    private var activeSwipeAction: CardSwipeAction? {
        if flyOutOffset.width < -10 || dragOffset < -10 {
            return .repeatLater
        }

        if flyOutOffset.width > 10 || dragOffset > 10 {
            return .remember
        }

        return nil
    }

    private func swipeGuideColor(for action: CardSwipeAction) -> Color {
        switch action {
        case .remember:
            return palette.primary
        case .repeatLater:
            return palette.error
        }
    }

    private var cardDeck: some View {
        ZStack {
            ForEach(visibleDeckCards.reversed(), id: \.position) { card in
                let isTopCard = card.position == 0

                flashcard(
                    card.word,
                    isTopCard: isTopCard
                )
                .scaleEffect(deckScale(for: card.position))
                .offset(
                    x: deckOffsetX(
                        for: card.position,
                        isTopCard: isTopCard
                    ),
                    y: deckOffsetY(
                        for: card.position,
                        isTopCard: isTopCard
                    )
                )
                .rotationEffect(
                    .degrees(deckRotation(for: card.position, isTopCard: isTopCard))
                )
                .zIndex(Double(visibleDeckCards.count - card.position))
                .allowsHitTesting(isTopCard)
            }
        }
        .frame(maxWidth: .infinity, minHeight: 336)
        .contentShape(Rectangle())
        .gesture(cardSwipeGesture)
        .onTapGesture {
            guard !isCardFlyingOut else { return }

            withAnimation(.spring(response: 0.32, dampingFraction: 0.86)) {
                isShowingTranslation.toggle()
            }
        }
        .animation(.spring(response: 0.28, dampingFraction: 0.82), value: dragOffset)
        .animation(.spring(response: 0.32, dampingFraction: 0.86), value: isShowingTranslation)
        .animation(.spring(response: 0.28, dampingFraction: 0.84), value: viewModel.state.currentIndex)
    }

    private var visibleDeckCards: [(position: Int, word: Word)] {
        let words = viewModel.state.words
        guard !words.isEmpty else { return [] }

        return (0..<min(3, words.count)).map { position in
            let currentIndex = Int(viewModel.state.currentIndex)
            let index = (currentIndex + position) % words.count

            return (position, words[index])
        }
    }

    private func flashcard(
        _ word: Word,
        isTopCard: Bool
    ) -> some View {
        let shouldShowTranslation = isTopCard && isShowingTranslation

        return ZStack {
            cardSide(
                text: word.spelling,
                color: palette.primary,
                font: .system(size: 32, weight: .semibold)
            )
            .opacity(shouldShowTranslation ? 0 : 1)

            cardSide(
                text: word.translation,
                color: palette.onSurface,
                font: .system(size: 28, weight: .semibold)
            )
            .scaleEffect(x: -1, y: 1)
            .opacity(shouldShowTranslation ? 1 : 0)
        }
        .frame(maxWidth: .infinity, minHeight: 280)
        .padding(20)
        .background(
            RoundedRectangle(cornerRadius: 8, style: .continuous)
                .fill(palette.surfaceContainer)
        )
        .overlay(
            RoundedRectangle(cornerRadius: 8, style: .continuous)
                .stroke(palette.outline.opacity(isTopCard ? 0.22 : 0.12), lineWidth: 1)
        )
        .shadow(
            color: palette.onSurface.opacity(isTopCard ? 0.12 : 0.05),
            radius: isTopCard ? 18 : 10,
            x: 0,
            y: isTopCard ? 12 : 7
        )
        .contentShape(RoundedRectangle(cornerRadius: 8, style: .continuous))
        .rotation3DEffect(
            .degrees(shouldShowTranslation ? 180 : 0),
            axis: (x: 0, y: 1, z: 0),
            perspective: 0.72
        )
    }

    private func cardSide(
        text: String,
        color: Color,
        font: Font
    ) -> some View {
        Text(text)
            .font(font)
            .foregroundStyle(color)
            .multilineTextAlignment(.center)
            .minimumScaleFactor(0.55)
            .frame(maxWidth: .infinity, maxHeight: .infinity)
    }

    private func deckScale(for position: Int) -> CGFloat {
        1 - CGFloat(position) * 0.055
    }

    private func deckOffsetX(
        for position: Int,
        isTopCard: Bool
    ) -> CGFloat {
        guard isTopCard else {
            return CGFloat(position) * 6
        }

        return dragOffset + flyOutOffset.width
    }

    private func deckOffsetY(
        for position: Int,
        isTopCard: Bool
    ) -> CGFloat {
        guard isTopCard else {
            return CGFloat(position) * 16
        }

        return flyOutOffset.height
    }

    private func deckRotation(
        for position: Int,
        isTopCard: Bool
    ) -> Double {
        guard isTopCard else {
            return Double(position) * -1.8
        }

        return Double(dragOffset / 30) + flyOutRotation
    }

    private var cardSwipeGesture: some Gesture {
        DragGesture(minimumDistance: 24)
            .updating($dragOffset) { value, state, _ in
                guard !isCardFlyingOut else { return }

                state = value.translation.width
            }
            .onEnded { value in
                guard !isCardFlyingOut else { return }
                guard abs(value.translation.width) > 64 else { return }

                let action: CardSwipeAction = value.translation.width < 0 ? .repeatLater : .remember
                flyOutCard(
                    action: action,
                    translation: value.translation
                )
            }
    }

    private func flyOutCard(
        action: CardSwipeAction,
        translation: CGSize
    ) {
        isShowingTranslation = false
        isCardFlyingOut = true

        let direction = action == .repeatLater ? -1.0 : 1.0

        withAnimation(.spring(response: 0.34, dampingFraction: 0.86)) {
            flyOutOffset = CGSize(
                width: direction * 760,
                height: translation.height * 0.45
            )
            flyOutRotation = direction * 24
        }

        DispatchQueue.main.asyncAfter(deadline: .now() + 0.24) {
            switch action {
            case .remember:
                viewModel.obtainEvent(WordsCardsEvent.OnRememberClick.shared)
            case .repeatLater:
                viewModel.obtainEvent(WordsCardsEvent.OnRepeatClick.shared)
            }

            resetFlyOutState()
        }
    }

    private func resetFlyOutState() {
        var transaction = Transaction()
        transaction.disablesAnimations = true

        withTransaction(transaction) {
            flyOutOffset = .zero
            flyOutRotation = 0
            isCardFlyingOut = false
        }
    }
}

private enum CardSwipeAction: Equatable {
    case remember
    case repeatLater
}
