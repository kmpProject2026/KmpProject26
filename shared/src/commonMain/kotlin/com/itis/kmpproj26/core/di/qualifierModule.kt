package com.itis.kmpproj26.core.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

internal object QualifierDBName
internal object QualifierSettingName

internal val qualifierModule = module {
    factory<String>(named<QualifierDBName>()) {
        "book.db"
    }
    factory<String>(named<QualifierSettingName>()) {
        "settings"
    }
}

