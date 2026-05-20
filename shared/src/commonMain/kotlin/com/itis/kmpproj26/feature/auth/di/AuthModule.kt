package com.itis.kmpproj26.feature.auth.di

import com.itis.kmpproj26.feature.auth.data.datasource.PersistentAuthDataSource
import com.itis.kmpproj26.feature.auth.data.repository.AuthRepositoryImpl
import com.itis.kmpproj26.feature.auth.data.usecase.DeleteAccountUseCaseImpl
import com.itis.kmpproj26.feature.auth.data.usecase.GetCurrentUserUseCaseImpl
import com.itis.kmpproj26.feature.auth.data.usecase.IsUserLoggedInUseCaseImpl
import com.itis.kmpproj26.feature.auth.data.usecase.LoginUseCaseImpl
import com.itis.kmpproj26.feature.auth.data.usecase.LogoutUseCaseImpl
import com.itis.kmpproj26.feature.auth.data.usecase.RegisterUseCaseImpl
import com.itis.kmpproj26.feature.auth.domain.repository.AuthRepository
import com.itis.kmpproj26.feature.auth.domain.usecase.DeleteAccountUseCase
import com.itis.kmpproj26.feature.auth.domain.usecase.GetCurrentUserUseCase
import com.itis.kmpproj26.feature.auth.domain.usecase.IsUserLoggedInUseCase
import com.itis.kmpproj26.feature.auth.domain.usecase.LoginUseCase
import com.itis.kmpproj26.feature.auth.domain.usecase.LogoutUseCase
import com.itis.kmpproj26.feature.auth.domain.usecase.RegisterUseCase
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginEmailChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginInitEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginOnLoginClickEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginOnRegisterClickIntentHandler
import com.itis.kmpproj26.feature.auth.ui.screen.login.handler.LoginPasswordChangedIntentHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationEmailChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationFirstNameChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationInitEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationLastNameChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationOnLoginClickEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationOnRegisterClickEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.registration.handler.RegistrationPasswordChangedEventHandler
import com.itis.kmpproj26.feature.auth.ui.screen.splash.handler.SplashInitEventHandler
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authModule = module {
    factoryOf(::PersistentAuthDataSource)

    factory<AuthRepository> { AuthRepositoryImpl(get()) }

    factory<DeleteAccountUseCase> { DeleteAccountUseCaseImpl(get()) }
    factory<GetCurrentUserUseCase> { GetCurrentUserUseCaseImpl(get()) }
    factory<IsUserLoggedInUseCase> { IsUserLoggedInUseCaseImpl(get()) }
    factory<LoginUseCase> { LoginUseCaseImpl(get()) }
    factory<LogoutUseCase> { LogoutUseCaseImpl(get()) }
    factory<RegisterUseCase> { RegisterUseCaseImpl(get()) }

    factoryOf(::SplashInitEventHandler)

    factoryOf(::LoginEmailChangedEventHandler)
    factoryOf(::LoginHideErrorDialogEventHandler)
    factoryOf(::LoginInitEventHandler)
    factoryOf(::LoginOnLoginClickEventHandler)
    factoryOf(::LoginOnRegisterClickIntentHandler)
    factoryOf(::LoginPasswordChangedIntentHandler)

    factoryOf(::RegistrationEmailChangedEventHandler)
    factoryOf(::RegistrationFirstNameChangedEventHandler)
    factoryOf(::RegistrationHideErrorDialogEventHandler)
    factoryOf(::RegistrationInitEventHandler)
    factoryOf(::RegistrationLastNameChangedEventHandler)
    factoryOf(::RegistrationOnLoginClickEventHandler)
    factoryOf(::RegistrationOnRegisterClickEventHandler)
    factoryOf(::RegistrationPasswordChangedEventHandler)
}