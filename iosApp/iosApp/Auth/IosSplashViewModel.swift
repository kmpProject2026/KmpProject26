import Combine
import CommonKmp
import Foundation

final class IosSplashViewModel: ObservableObject {

    private let commonViewModel: SplashViewModel
    private let actionSubject = PassthroughSubject<SplashAction, Never>()
    private var cancellables = Set<AnyCancellable>()

    @Published
    private(set) var state: SplashViewState

    var actions: AnyPublisher<SplashAction, Never> {
        actionSubject.eraseToAnyPublisher()
    }

    init(_ commonViewModel: SplashViewModel = SplashViewModel()) {
        self.commonViewModel = commonViewModel
        self.state = commonViewModel.viewStates.value as! SplashViewState

        (commonViewModel.viewStates.asPublisher() as AnyPublisher<SplashViewState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)

        (commonViewModel.viewActions.asPublisher() as AnyPublisher<SplashAction, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] action in
                self?.actionSubject.send(action)
            }
            .store(in: &cancellables)
    }

    func obtainEvent(_ event: SplashEvent) {
        commonViewModel.obtainEvent(event: event)
    }

    deinit {
        commonViewModel.onCleared()
    }
}
