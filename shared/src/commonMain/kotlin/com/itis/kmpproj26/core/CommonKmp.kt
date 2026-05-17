package com.itis.kmpproj26.core

import com.itis.kmpproj26.core.config.Configuration
import com.itis.kmpproj26.core.database.dbModule
import com.itis.kmpproj26.core.di.platformModule
import com.itis.kmpproj26.core.di.qualifierModule
import com.itis.kmpproj26.core.netwrok.networkModule
import com.itis.kmpproj26.feature.featureModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

object CommonKmp {

    fun initKoin(
        configuration: Configuration,
        appDeclaration: KoinAppDeclaration = {},
    ) {
        startKoin {
            appDeclaration()
            modules(
                createConfiguration(configuration),
                qualifierModule,
                platformModule,
                networkModule,
                dbModule,
                featureModule,
            )
        }
    }

    private fun createConfiguration(configuration: Configuration) = module {
        single<Configuration> { configuration }
    }
}
