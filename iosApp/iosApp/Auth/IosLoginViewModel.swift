import Combine
import CommonKmp
import Foundation

final class IosLoginViewModel: ObservableObject {

    private let commonViewModel: LoginViewModel
    private let actionSubject = PassthroughSubject<LoginAction, Never>()
    private var cancellables = Set<AnyCancellable>()

    @Published
    private(set) var state: LoginState

    var actions: AnyPublisher<LoginAction, Never> {
        actionSubject.eraseToAnyPublisher()
    }

    init(_ commonViewModel: LoginViewModel = LoginViewModel()) {
        self.commonViewModel = commonViewModel
        self.state = commonViewModel.viewStates.value as! LoginState

        (commonViewModel.viewStates.asPublisher() as AnyPublisher<LoginState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)

        (commonViewModel.viewActions.asPublisher() as AnyPublisher<LoginAction, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] action in
                self?.actionSubject.send(action)
            }
            .store(in: &cancellables)
    }

    func obtainEvent(_ event: LoginEvent) {
        commonViewModel.obtainEvent(event: event)
    }

    deinit {
        commonViewModel.onCleared()
    }
}
