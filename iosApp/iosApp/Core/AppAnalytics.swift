import CommonKmp
import FirebaseAnalytics
import FirebaseCore
import FirebaseCrashlytics

enum AppAnalytics {

    static func configure() {
        FirebaseConfiguration.shared.setLoggerLevel(.debug)
        FirebaseApp.configure()
        Analytics.setAnalyticsCollectionEnabled(true)
        Crashlytics.crashlytics().setCrashlyticsCollectionEnabled(true)
        IosAnalyticsBridge.shared.setLogger(logger: FirebaseAnalyticsLogger())
    }

    static func logEvent(_ name: String) {
        Analytics.logEvent(name, parameters: nil)
        Crashlytics.crashlytics().log(name)
    }
}

private final class FirebaseAnalyticsLogger: IosAnalyticsLogger {
    func logEvent(name: String) {
        AppAnalytics.logEvent(name)
    }
}
