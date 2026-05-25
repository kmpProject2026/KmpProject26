import Combine
import CommonKmp

final class IosWordsListViewModel: ObservableObject {

    private let commonViewModel: WordsListViewModel
    private let actionSubject = PassthroughSubject<WordsListAction, Never>()
    private var cancellables = Set<AnyCancellable>()

    @Published
    private(set) var state: WordsListState

    var actions: AnyPublisher<WordsListAction, Never> {
        actionSubject.eraseToAnyPublisher()
    }

    init(_ commonViewModel: WordsListViewModel = WordsListViewModel()) {
        self.commonViewModel = commonViewModel
        self.state = commonViewModel.viewStates.value as! WordsListState

        (commonViewModel.viewStates.asPublisher() as AnyPublisher<WordsListState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)

        (commonViewModel.viewActions.asPublisher() as AnyPublisher<WordsListAction, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] action in
                self?.actionSubject.send(action)
            }
            .store(in: &cancellables)
    }

    func obtainEvent(_ event: WordsListEvent) {
        commonViewModel.obtainEvent(event: event)
    }

    deinit {
        commonViewModel.onCleared()
    }
}
