import Combine
import CommonKmp
import Foundation

final class IosProfileViewModel: ObservableObject {

    private let commonViewModel: ProfileViewModel
    private let actionSubject = PassthroughSubject<ProfileAction, Never>()
    private var cancellables = Set<AnyCancellable>()

    @Published
    private(set) var state: ProfileState

    var actions: AnyPublisher<ProfileAction, Never> {
        actionSubject.eraseToAnyPublisher()
    }

    init(_ commonViewModel: ProfileViewModel = ProfileViewModel()) {
        self.commonViewModel = commonViewModel
        self.state = commonViewModel.viewStates.value as! ProfileState

        (commonViewModel.viewStates.asPublisher() as AnyPublisher<ProfileState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)

        (commonViewModel.viewActions.asPublisher() as AnyPublisher<ProfileAction, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] action in
                self?.actionSubject.send(action)
            }
            .store(in: &cancellables)
    }

    func obtainEvent(_ event: ProfileEvent) {
        commonViewModel.obtainEvent(event: event)
    }

    deinit {
        commonViewModel.onCleared()
    }
}
