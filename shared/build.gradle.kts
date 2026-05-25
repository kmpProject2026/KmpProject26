import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use(::load)
    }
}

val yandexDictionaryApiKey = localProperties
    .getProperty("YANDEX_DICTIONARY_API_KEY")
    .orEmpty()
    .trim()
    .let(::removePropertyValueQuotes)

fun removePropertyValueQuotes(value: String): String {
    val trimmedValue = value.trim()
    if (trimmedValue.length < 2) return trimmedValue

    val firstChar = trimmedValue.first()
    val lastChar = trimmedValue.last()
    val hasMatchingQuotes = (firstChar == '"' && lastChar == '"') ||
            (firstChar == '\'' && lastChar == '\'')

    return if (hasMatchingQuotes) {
        trimmedValue.substring(1, trimmedValue.lastIndex).trim()
    } else {
        trimmedValue
    }
}

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "CommonKmp"
            isStatic = true

            /*
            * export
            * случае экспорта модуля, для него генерятся нормальные obj-c хэдеры и со стороны айоса доступны все декларации,
            * которые в нём объявлены. Без экспорта доступны только транзитивные элементы
            * */
              export(libs.androidx.lifecycle.viewmodel)
        }
    }
    
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.ktorClientCommon)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)

            implementation(libs.sqldelight.coroutines.extensions)

            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.serialization)

            implementation(libs.koin.core)
            implementation(libs.koin.compose.vm)

            implementation(libs.kotlinx.uuid)
            implementation(libs.krypto)

            api(libs.androidx.lifecycle.viewmodel)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.okhttp3.logging.interceptor)
            implementation(libs.timber)

            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.sqldelight.android.driver)

            implementation(libs.androidx.lifecycle.viewmodel)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.client.okhttp)

            implementation(libs.sqldelight.sqlite.driver)
            implementation(libs.kotlinx.coroutines.swing)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)

            implementation(libs.sqldelight.native.driver)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.itis.kmpproj26")
        }
    }
}

buildkonfig {
    packageName = "com.itis.kmpproj26"

    defaultConfigs {
        buildConfigField(
            STRING,
            "YANDEX_DICTIONARY_API_KEY",
            yandexDictionaryApiKey,
        )
    }
}


android {
    namespace = "com.itis.kmpproj26.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
