import SwiftUI

struct WordsRootView: View {

    @State
    private var path: [WordsDestination] = []

    var body: some View {
        NavigationStack(path: $path) {
            WordsCardsView(
                navigateToAdd: { pushDestination(.add) },
                navigateToList: { pushDestination(.list) }
            )
            .navigationDestination(for: WordsDestination.self) { destination in
                switch destination {
                case .list:
                    WordsListView(
                        navigateBack: { popDestination() },
                        navigateToAdd: { pushDestination(.add) }
                    )

                case .add:
                    AddWordView(
                        navigateBack: { popDestination() }
                    )
                }
            }
        }
    }

    private func pushDestination(_ destination: WordsDestination) {
        guard path.last != destination else { return }

        path.append(destination)
    }

    private func popDestination() {
        guard !path.isEmpty else { return }

        path.removeLast()
    }
}
