import CommonKmp
import Foundation
import SwiftUI

struct ProfileView: View {

    @StateObject
    private var viewModel = IosProfileViewModel()

    @State
    private var toastMessage: String?

    let navigateToAuth: () -> Void

    @Environment(\.appPalette)
    private var palette

    var body: some View {
        ZStack {
            palette.background.ignoresSafeArea()

            if viewModel.state.isLoading {
                ProgressView()
                    .tint(palette.primary)
                    .scaleEffect(1.2)
            } else {
                profileContent
            }
        }
        .onAppear {
            viewModel.obtainEvent(ProfileEvent.Init.shared)
        }
        .onReceive(viewModel.actions) { action in
            if action is ProfileAction.ShowToast {
                showToast("Profile successfully deleted")
            } else if action is ProfileAction.NavigateToAuth {
                navigateToAuth()
            }
        }
        .alert("Error", isPresented: errorDialogBinding) {
            Button("OK", role: .cancel) {
                viewModel.obtainEvent(ProfileEvent.HideErrorDialog.shared)
            }
        } message: {
            Text("An unknown error occurred")
        }
        .alert("Warning", isPresented: deleteDialogBinding) {
            Button("No", role: .cancel) {
                viewModel.obtainEvent(ProfileEvent.OnDeleteProfileCancelled.shared)
            }

            Button("Yes", role: .destructive) {
                viewModel.obtainEvent(ProfileEvent.OnDeleteProfileConfirmed.shared)
            }
        } message: {
            Text("Are you sure to delete profile?")
        }
        .overlay(alignment: .bottom) {
            if let toastMessage {
                Text(toastMessage)
                    .font(.system(size: 14, weight: .medium))
                    .foregroundStyle(palette.onPrimary)
                    .padding(.horizontal, 16)
                    .padding(.vertical, 12)
                    .background(
                        Capsule()
                            .fill(palette.primary)
                    )
                    .padding(.bottom, 24)
                    .transition(.move(edge: .bottom).combined(with: .opacity))
            }
        }
    }

    private var profileContent: some View {
        ScrollView {
            VStack(spacing: 0) {
                ProfileHeader()
                    .padding(.top, 24)

                VStack(spacing: 24) {
                    ProfileSectionCard(
                        title: "First name",
                        text: viewModel.state.firstName
                    )

                    ProfileSectionCard(
                        title: "Last name",
                        text: viewModel.state.lastName
                    )

                    ProfileSectionCard(
                        title: "Email",
                        text: viewModel.state.email
                    )

                    Text("You are together with us from \(viewModel.state.createdAt)")
                        .font(.system(size: 17, weight: .medium))
                        .foregroundStyle(palette.primary)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(16)
                        .background(
                            RoundedRectangle(cornerRadius: 16, style: .continuous)
                                .fill(palette.surfaceVariant)
                        )
                        .padding(.top, 8)
                }
                .padding(.top, 32)

                VStack(spacing: 8) {
                    Button(action: {
                        viewModel.obtainEvent(ProfileEvent.OnLogoutClick.shared)
                    }) {
                        Text("Log out")
                    }
                    .buttonStyle(AppPrimaryButtonStyle())

                    Button(action: {
                        viewModel.obtainEvent(ProfileEvent.OnDeleteProfileClick.shared)
                    }) {
                        Text("Delete profile")
                    }
                    .buttonStyle(AppSecondaryButtonStyle())
                }
                .padding(.top, 32)
                .padding(.bottom, 24)
            }
            .frame(maxWidth: .infinity)
            .padding(.horizontal, 16)
        }
    }

    private var errorDialogBinding: Binding<Bool> {
        Binding(
            get: { viewModel.state.dialog === ProfileDialog.errorUnknown },
            set: { isPresented in
                if !isPresented {
                    viewModel.obtainEvent(ProfileEvent.HideErrorDialog.shared)
                }
            }
        )
    }

    private var deleteDialogBinding: Binding<Bool> {
        Binding(
            get: { viewModel.state.dialog === ProfileDialog.confirmDelete },
            set: { _ in }
        )
    }

    private func showToast(_ message: String) {
        withAnimation(.easeInOut(duration: 0.2)) {
            toastMessage = message
        }

        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
            withAnimation(.easeInOut(duration: 0.2)) {
                if toastMessage == message {
                    toastMessage = nil
                }
            }
        }
    }
}

private struct ProfileHeader: View {

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

            Text("Dictionary")
                .font(.system(size: 24, weight: .semibold))
                .foregroundStyle(palette.primary)
        }
    }
}

private struct ProfileSectionCard: View {

    let title: String
    let text: String

    @Environment(\.appPalette)
    private var palette

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(.system(size: 16))
                .foregroundStyle(palette.onSurfaceVariant)
                .padding(.leading, 8)

            Text(text)
                .font(.system(size: 16))
                .foregroundStyle(palette.onSurface)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(16)
                .background(
                    RoundedRectangle(cornerRadius: 16, style: .continuous)
                        .fill(palette.surfaceVariant)
                )
        }
    }
}
