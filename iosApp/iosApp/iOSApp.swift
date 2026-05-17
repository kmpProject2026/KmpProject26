import SwiftUI
import CommonKmp

@main
struct iOSApp: App {


    init() {
        initShared()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }

    private func initShared() {
        let version = Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") as? String ?? "1.0.0"
        let build = Bundle.main.object(forInfoDictionaryKey: "CFBundleVersion") as? String ?? "0"

        var isDebug = false
        #if DEBUG
        isDebug = true
        #endif

        CommonKmp.shared.doInitKoin(
            configuration: Configuration(
                isHttpLoggingEnabled: isDebug,
                isDebug: isDebug
            ),
            appDeclaration: {_ in },
        )
    }
}
