//
//  WeatherViewModel.swift
//  iosApp
//
//  Created by Nail Shaykhraziev on 13.04.2026.
//

import CommonKmp
import Combine

final class IosWeatherViewModel : ObservableObject {

    private let commonViewModel: SplashViewModel

    @Published
    private(set) var state: SplashViewState

    init(_ commonViewModel: SplashViewModel) {
        self.commonViewModel = commonViewModel

        self.state = commonViewModel.viewStates.value as! SplashViewState
        (self.commonViewModel.viewStates.asPublisher() as AnyPublisher<SplashViewState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }

    func obtainEvent(_ event : SplashEvent) {
        self.commonViewModel.obtainEvent(event: event)
    }

    deinit {
        commonViewModel.onCleared()
    }

}
