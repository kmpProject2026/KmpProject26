import Combine
import CommonKmp

final class IosWordsCardsViewModel: ObservableObject {

    private let commonViewModel: WordsCardsViewModel
    private let actionSubject = PassthroughSubject<WordsCardsAction, Never>()
    private var cancellables = Set<AnyCancellable>()

    @Published
    private(set) var state: WordsCardsState

    var actions: AnyPublisher<WordsCardsAction, Never> {
        actionSubject.eraseToAnyPublisher()
    }

    init(_ commonViewModel: WordsCardsViewModel = WordsCardsViewModel()) {
        self.commonViewModel = commonViewModel
        self.state = commonViewModel.viewStates.value as! WordsCardsState

        (commonViewModel.viewStates.asPublisher() as AnyPublisher<WordsCardsState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)

        (commonViewModel.viewActions.asPublisher() as AnyPublisher<WordsCardsAction, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] action in
                self?.actionSubject.send(action)
            }
            .store(in: &cancellables)
    }

    func obtainEvent(_ event: WordsCardsEvent) {
        commonViewModel.obtainEvent(event: event)
    }

    deinit {
        commonViewModel.onCleared()
    }
}
