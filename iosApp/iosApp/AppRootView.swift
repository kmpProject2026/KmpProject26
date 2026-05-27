import SwiftUI

private enum AppRootScreen {
    case splash
    case login
    case registration
    case main
}

private enum MainTab: Hashable {
    case words
    case profile
}

struct AppRootView: View {

    @State
    private var rootScreen: AppRootScreen = .splash

    @State
    private var selectedTab: MainTab = .words

    var body: some View {
        Group {
            switch rootScreen {
            case .splash:
                SplashView(
                    navigateToLogin: { rootScreen = .login },
                    navigateToProfile: { showMain(selectedTab: .profile) }
                )

            case .login:
                LoginView(
                    navigateToProfile: { showMain(selectedTab: .profile) },
                    navigateToRegistration: { rootScreen = .registration }
                )

            case .registration:
                RegistrationView(
                    navigateToProfile: { showMain(selectedTab: .profile) },
                    navigateToLogin: { rootScreen = .login }
                )

            case .main:
                MainTabView(
                    selectedTab: $selectedTab,
                    navigateToAuth: { rootScreen = .login }
                )
            }
        }
    }

    private func showMain(selectedTab: MainTab) {
        self.selectedTab = selectedTab
        rootScreen = .main
    }
}

private struct MainTabView: View {

    @Binding
    var selectedTab: MainTab

    let navigateToAuth: () -> Void

    @Environment(\.appPalette)
    private var palette

    var body: some View {
        TabView(selection: $selectedTab) {
            WordsRootView()
                .tabItem {
                    Label("Words", systemImage: "book.closed")
                }
                .tag(MainTab.words)

            ProfileView(navigateToAuth: navigateToAuth)
                .tabItem {
                    Label("Profile", systemImage: "person")
                }
                .tag(MainTab.profile)
        }
        .tint(palette.primary)
    }
}
