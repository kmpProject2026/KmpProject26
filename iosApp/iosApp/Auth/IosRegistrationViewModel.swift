import Combine
import CommonKmp
import Foundation

final class IosRegistrationViewModel: ObservableObject {

    private let commonViewModel: RegistrationViewModel
    private let actionSubject = PassthroughSubject<RegistrationAction, Never>()
    private var cancellables = Set<AnyCancellable>()

    @Published
    private(set) var state: RegistrationState

    var actions: AnyPublisher<RegistrationAction, Never> {
        actionSubject.eraseToAnyPublisher()
    }

    init(_ commonViewModel: RegistrationViewModel = RegistrationViewModel()) {
        self.commonViewModel = commonViewModel
        self.state = commonViewModel.viewStates.value as! RegistrationState

        (commonViewModel.viewStates.asPublisher() as AnyPublisher<RegistrationState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)

        (commonViewModel.viewActions.asPublisher() as AnyPublisher<RegistrationAction, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] action in
                self?.actionSubject.send(action)
            }
            .store(in: &cancellables)
    }

    func obtainEvent(_ event: RegistrationEvent) {
        commonViewModel.obtainEvent(event: event)
    }

    deinit {
        commonViewModel.onCleared()
    }
}
