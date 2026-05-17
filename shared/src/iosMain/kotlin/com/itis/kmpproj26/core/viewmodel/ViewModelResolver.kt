package com.itis.kmpproj26.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import kotlin.reflect.KClass

// for using with jetpack viewmodel

@BetaInteropApi
@Throws(IllegalArgumentException::class)
@Suppress("unused")
fun ViewModelStore.resolveViewModel(
    modelClass: ObjCClass,
    factory: ViewModelProvider.Factory,
    key: String?,
    extras: CreationExtras? = null,
): ViewModel {
    @Suppress("UNCHECKED_CAST")
    val vmClass = getOriginalKotlinClass(modelClass) as? KClass<ViewModel>
    require(vmClass != null) { "The modelClass parameter must be a ViewModel type." }

    val provider = ViewModelProvider.Companion.create(this, factory, extras ?: CreationExtras.Empty)
    return key?.let { provider[key, vmClass] } ?: provider[vmClass]
}

// iosApp/IosViewModelStoreOwner.swift
// --------------
/*class IosViewModelStoreOwner: ObservableObject, ViewModelStoreOwner {

    let viewModelStore = ViewModelStore()

    /// This function allows retrieving the androidx ViewModel from the store.
    /// It uses the utilify function to pass the generic type T to shared code
    func viewModel<T: ViewModel>(
    key: String? = nil,
    factory: ViewModelProviderFactory,
    extras: CreationExtras? = nil
    ) -> T {
        do {
            return try viewModelStore.resolveViewModel(
                modelClass: T.self,
                factory: factory,
                key: key,
                extras: extras
                ) as! T
            } catch {
                fatalError("Failed to create ViewModel of type \(T.self)")
            }
        }

    /// This is called when this class is used as a `@StateObject`
    deinit {
        viewModelStore.clear()
    }
}*/


// ------------

/*struct ContentView: View {

    /// Use the store owner as a StateObject to allow retrieving ViewModels and scoping it to this screen.
    @StateObject private var viewModelStoreOwner = IosViewModelStoreOwner()

    var body: some View {
        let mainViewModel: MainViewModel = viewModelStoreOwner.viewModel(
                factory: MainViewModelKt.mainViewModelFactory
        )
        // .. the rest of the SwiftUI code
    }
}*/
