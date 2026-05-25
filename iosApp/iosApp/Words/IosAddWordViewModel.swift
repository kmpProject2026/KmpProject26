import Combine
import CommonKmp

final class IosAddWordViewModel: ObservableObject {

    private let commonViewModel: AddWordViewModel
    private let actionSubject = PassthroughSubject<AddWordAction, Never>()
    private var cancellables = Set<AnyCancellable>()

    @Published
    private(set) var state: AddWordState

    var actions: AnyPublisher<AddWordAction, Never> {
        actionSubject.eraseToAnyPublisher()
    }

    init(_ commonViewModel: AddWordViewModel = AddWordViewModel()) {
        self.commonViewModel = commonViewModel
        self.state = commonViewModel.viewStates.value as! AddWordState

        (commonViewModel.viewStates.asPublisher() as AnyPublisher<AddWordState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)

        (commonViewModel.viewActions.asPublisher() as AnyPublisher<AddWordAction, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] action in
                self?.actionSubject.send(action)
            }
            .store(in: &cancellables)
    }

    func obtainEvent(_ event: AddWordEvent) {
        commonViewModel.obtainEvent(event: event)
    }

    deinit {
        commonViewModel.onCleared()
    }
}
