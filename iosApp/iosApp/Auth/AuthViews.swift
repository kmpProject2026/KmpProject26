import CommonKmp
import SwiftUI

private enum AuthTexts {
    static let appName = "Dictionary"
    static let unknownError = "An unknown error occurred"
    static let email = "Email"
    static let password = "Password"
    static let firstName = "First name"
    static let lastName = "Last name"
    static let enterEmail = "Enter email"
    static let enterPassword = "Enter password"
    static let enterFirstName = "Enter first name"
    static let enterLastName = "Enter last name"
    static let login = "Login"
    static let register = "Register"
}

struct SplashView: View {

    @StateObject
    private var viewModel = IosSplashViewModel()

    let navigateToLogin: () -> Void
    let navigateToProfile: () -> Void

    @Environment(\.appPalette)
    private var palette

    var body: some View {
        ZStack {
            palette.background.ignoresSafeArea()

            ProgressView()
                .tint(palette.primary)
                .scaleEffect(1.2)
        }
        .onAppear {
            viewModel.obtainEvent(SplashEvent.Init.shared)
        }
        .onReceive(viewModel.actions) { action in
            if action is SplashAction.NavigateToLogin {
                navigateToLogin()
            } else if action is SplashAction.NavigateToProfile {
                navigateToProfile()
            }
        }
    }
}

struct LoginView: View {

    @StateObject
    private var viewModel = IosLoginViewModel()

    let navigateToProfile: () -> Void
    let navigateToRegistration: () -> Void

    @Environment(\.appPalette)
    private var palette

    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                AuthHeader()

                VStack(spacing: 16) {
                    AppInputField(
                        title: AuthTexts.email,
                        placeholder: AuthTexts.enterEmail,
                        text: Binding(
                            get: { viewModel.state.email },
                            set: { viewModel.obtainEvent(LoginEvent.OnEmailChanged(email: $0)) }
                        ),
                        isError: viewModel.state.emailError != nil || viewModel.state.loginError != nil,
                        errorText: emailErrorText(viewModel.state.emailError)
                    )
                    .textInputAutocapitalization(.never)
                    .keyboardType(.emailAddress)
                    .autocorrectionDisabled()

                    AppSecureInputField(
                        title: AuthTexts.password,
                        placeholder: AuthTexts.enterPassword,
                        text: Binding(
                            get: { viewModel.state.password },
                            set: { viewModel.obtainEvent(LoginEvent.OnPasswordChanged(password: $0)) }
                        ),
                        isError: viewModel.state.passwordError != nil || viewModel.state.loginError != nil,
                        errorText: passwordErrorText(viewModel.state.passwordError) ?? loginErrorText(viewModel.state.loginError)
                    )
                    .textContentType(.password)
                }
                .padding(.top, 40)

                VStack(spacing: 8) {
                    Button(action: {
                        viewModel.obtainEvent(LoginEvent.OnLoginClick.shared)
                    }) {
                        buttonLabel(AuthTexts.login, isLoading: viewModel.state.isLoading, tint: palette.onPrimary)
                    }
                    .buttonStyle(AppPrimaryButtonStyle())
                    .disabled(viewModel.state.isLoading)

                    Button(action: {
                        viewModel.obtainEvent(LoginEvent.OnRegisterClick.shared)
                    }) {
                        Text(AuthTexts.register)
                    }
                    .buttonStyle(AppSecondaryButtonStyle())
                    .disabled(viewModel.state.isLoading)
                }
                .padding(.top, 32)
                .padding(.bottom, 32)
            }
            .frame(maxWidth: .infinity)
            .padding(.horizontal, 24)
        }
        .background(palette.background.ignoresSafeArea())
        .onAppear {
            viewModel.obtainEvent(LoginEvent.Init.shared)
        }
        .onReceive(viewModel.actions) { action in
            if action is LoginAction.NavigateToProfile {
                navigateToProfile()
            } else if action is LoginAction.NavigateToRegistration {
                navigateToRegistration()
            }
        }
        .alert("Error", isPresented: errorDialogBinding) {
            Button("OK", role: .cancel) {
                viewModel.obtainEvent(LoginEvent.HideErrorDialog.shared)
            }
        } message: {
            Text(AuthTexts.unknownError)
        }
    }

    private var errorDialogBinding: Binding<Bool> {
        Binding(
            get: { viewModel.state.dialog === LoginDialog.errorUnknown },
            set: { isPresented in
                if !isPresented {
                    viewModel.obtainEvent(LoginEvent.HideErrorDialog.shared)
                }
            }
        )
    }
}

struct RegistrationView: View {

    @StateObject
    private var viewModel = IosRegistrationViewModel()

    let navigateToProfile: () -> Void
    let navigateToLogin: () -> Void

    @Environment(\.appPalette)
    private var palette

    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                AuthHeader()

                VStack(spacing: 16) {
                    AppInputField(
                        title: AuthTexts.firstName,
                        placeholder: AuthTexts.enterFirstName,
                        text: Binding(
                            get: { viewModel.state.firstName },
                            set: { viewModel.obtainEvent(RegistrationEvent.OnFirstNameChanged(firstName: $0)) }
                        ),
                        isError: viewModel.state.firstNameError != nil || viewModel.state.registrationError != nil,
                        errorText: nameErrorText(viewModel.state.firstNameError, field: AuthTexts.firstName)
                    )

                    AppInputField(
                        title: AuthTexts.lastName,
                        placeholder: AuthTexts.enterLastName,
                        text: Binding(
                            get: { viewModel.state.lastName },
                            set: { viewModel.obtainEvent(RegistrationEvent.OnLastNameChanged(lastName: $0)) }
                        ),
                        isError: viewModel.state.lastNameError != nil || viewModel.state.registrationError != nil,
                        errorText: nameErrorText(viewModel.state.lastNameError, field: AuthTexts.lastName)
                    )

                    AppInputField(
                        title: AuthTexts.email,
                        placeholder: AuthTexts.enterEmail,
                        text: Binding(
                            get: { viewModel.state.email },
                            set: { viewModel.obtainEvent(RegistrationEvent.OnEmailChanged(email: $0)) }
                        ),
                        isError: viewModel.state.emailError != nil || viewModel.state.registrationError != nil,
                        errorText: emailErrorText(viewModel.state.emailError)
                    )
                    .textInputAutocapitalization(.never)
                    .keyboardType(.emailAddress)
                    .autocorrectionDisabled()

                    AppSecureInputField(
                        title: AuthTexts.password,
                        placeholder: AuthTexts.enterPassword,
                        text: Binding(
                            get: { viewModel.state.password },
                            set: { viewModel.obtainEvent(RegistrationEvent.OnPasswordChanged(password: $0)) }
                        ),
                        isError: viewModel.state.passwordError != nil || viewModel.state.registrationError != nil,
                        errorText: passwordErrorText(viewModel.state.passwordError) ?? registrationErrorText(viewModel.state.registrationError)
                    )
                    .textContentType(.newPassword)
                }
                .padding(.top, 40)

                VStack(spacing: 8) {
                    Button(action: {
                        viewModel.obtainEvent(RegistrationEvent.OnRegisterClick.shared)
                    }) {
                        buttonLabel(AuthTexts.register, isLoading: viewModel.state.isLoading, tint: palette.onPrimary)
                    }
                    .buttonStyle(AppPrimaryButtonStyle())
                    .disabled(viewModel.state.isLoading)

                    Button(action: {
                        viewModel.obtainEvent(RegistrationEvent.OnLoginClick.shared)
                    }) {
                        Text(AuthTexts.login)
                    }
                    .buttonStyle(AppSecondaryButtonStyle())
                    .disabled(viewModel.state.isLoading)
                }
                .padding(.top, 32)
                .padding(.bottom, 32)
            }
            .frame(maxWidth: .infinity)
            .padding(.horizontal, 24)
        }
        .background(palette.background.ignoresSafeArea())
        .onAppear {
            viewModel.obtainEvent(RegistrationEvent.Init.shared)
        }
        .onReceive(viewModel.actions) { action in
            if action is RegistrationAction.NavigateToProfile {
                navigateToProfile()
            } else if action is RegistrationAction.NavigateToLogin {
                navigateToLogin()
            }
        }
        .alert("Error", isPresented: errorDialogBinding) {
            Button("OK", role: .cancel) {
                viewModel.obtainEvent(RegistrationEvent.HideErrorDialog.shared)
            }
        } message: {
            Text(AuthTexts.unknownError)
        }
    }

    private var errorDialogBinding: Binding<Bool> {
        Binding(
            get: { viewModel.state.dialog === RegistrationDialog.errorUnknown },
            set: { isPresented in
                if !isPresented {
                    viewModel.obtainEvent(RegistrationEvent.HideErrorDialog.shared)
                }
            }
        )
    }
}

private struct AuthHeader: View {

    @Environment(\.appPalette)
    private var palette

    var body: some View {
        VStack(spacing: 16) {
            ZStack {
                Circle()
                    .fill(Color(hex: 0xFFFFF3E4))
                    .overlay(
                        Circle()
                            .stroke(palette.primary, lineWidth: 2)
                    )

                Image(systemName: "book.closed.fill")
                    .font(.system(size: 54, weight: .semibold))
                    .foregroundStyle(palette.primary)
            }
            .frame(width: 96, height: 96)
            .padding(.top, 48)

            Text(AuthTexts.appName)
                .font(.system(size: 24, weight: .semibold))
                .foregroundStyle(palette.primary)
        }
    }
}

@ViewBuilder
private func buttonLabel(_ text: String, isLoading: Bool, tint: Color) -> some View {
    if isLoading {
        ProgressView()
            .tint(tint)
    } else {
        Text(text)
    }
}

private func emailErrorText(_ error: EmailError?) -> String? {
    guard let error else { return nil }

    if error === EmailError.empty {
        return "Email should not be empty"
    }

    if error === EmailError.invalid {
        return "Invalid email format"
    }

    return nil
}

private func passwordErrorText(_ error: PasswordError?) -> String? {
    guard let error else { return nil }

    if error === PasswordError.empty {
        return "Password should not be empty"
    }

    if error === PasswordError.short_ {
        return "Password should contain not less than 6 symbols"
    }

    return nil
}

private func nameErrorText(_ error: NameError?, field: String) -> String? {
    guard let error else { return nil }

    guard error === NameError.empty else { return nil }

    return "\(field) should not be empty"
}

private func loginErrorText(_ error: LoginError?) -> String? {
    guard let error else { return nil }

    guard error === LoginError.invalid else { return nil }

    return "Invalid email or password"
}

private func registrationErrorText(_ error: RegistrationError?) -> String? {
    guard let error else { return nil }

    guard error === RegistrationError.usedEmail else { return nil }

    return "Email already in use"
}
