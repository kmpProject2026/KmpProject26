package com.itis.kmpproj26.feature.words.di

import com.itis.kmpproj26.core.netwrok.YANDEX_DICTIONARY_HTTP_CLIENT
import com.itis.kmpproj26.feature.words.data.datasource.PersistentWordsDataSource
import com.itis.kmpproj26.feature.words.data.datasource.RemoteWordsDataSource
import com.itis.kmpproj26.feature.words.data.repository.WordsRepositoryImpl
import com.itis.kmpproj26.feature.words.data.usecase.AddWordUseCaseImpl
import com.itis.kmpproj26.feature.words.data.usecase.DeleteWordUseCaseImpl
import com.itis.kmpproj26.feature.words.data.usecase.GetWordsUseCaseImpl
import com.itis.kmpproj26.feature.words.data.usecase.TranslateWordUseCaseImpl
import com.itis.kmpproj26.feature.words.domain.repository.WordsRepository
import com.itis.kmpproj26.feature.words.domain.usecase.AddWordUseCase
import com.itis.kmpproj26.feature.words.domain.usecase.DeleteWordUseCase
import com.itis.kmpproj26.feature.words.domain.usecase.GetWordsUseCase
import com.itis.kmpproj26.feature.words.domain.usecase.TranslateWordUseCase
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordBackEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordInitEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordSaveEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordSourceLanguageChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordSpellingChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordTargetLanguageChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordTranslateEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.add.handler.AddWordTranslationChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsInitEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsOpenAddEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsOpenListEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsRememberEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsRepeatEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsSourceLanguageFilterChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.cards.handler.WordsCardsTargetLanguageFilterChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListBackEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListDeleteEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListHideErrorDialogEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListInitEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListOpenAddEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListSourceLanguageFilterChangedEventHandler
import com.itis.kmpproj26.feature.words.ui.screen.list.handler.WordsListTargetLanguageFilterChangedEventHandler
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val wordsModule = module {
    factoryOf(::PersistentWordsDataSource)
    factory { RemoteWordsDataSource(get(named(YANDEX_DICTIONARY_HTTP_CLIENT))) }

    factory<WordsRepository> { WordsRepositoryImpl(get(), get()) }

    factory<GetWordsUseCase> { GetWordsUseCaseImpl(get()) }
    factory<TranslateWordUseCase> { TranslateWordUseCaseImpl(get()) }
    factory<AddWordUseCase> { AddWordUseCaseImpl(get()) }
    factory<DeleteWordUseCase> { DeleteWordUseCaseImpl(get()) }

    factoryOf(::WordsCardsInitEventHandler)
    factoryOf(::WordsCardsRepeatEventHandler)
    factoryOf(::WordsCardsRememberEventHandler)
    factoryOf(::WordsCardsSourceLanguageFilterChangedEventHandler)
    factoryOf(::WordsCardsTargetLanguageFilterChangedEventHandler)
    factoryOf(::WordsCardsOpenAddEventHandler)
    factoryOf(::WordsCardsOpenListEventHandler)
    factoryOf(::WordsCardsHideErrorDialogEventHandler)

    factoryOf(::WordsListInitEventHandler)
    factoryOf(::WordsListBackEventHandler)
    factoryOf(::WordsListOpenAddEventHandler)
    factoryOf(::WordsListDeleteEventHandler)
    factoryOf(::WordsListSourceLanguageFilterChangedEventHandler)
    factoryOf(::WordsListTargetLanguageFilterChangedEventHandler)
    factoryOf(::WordsListHideErrorDialogEventHandler)

    factoryOf(::AddWordInitEventHandler)
    factoryOf(::AddWordSpellingChangedEventHandler)
    factoryOf(::AddWordTranslationChangedEventHandler)
    factoryOf(::AddWordSourceLanguageChangedEventHandler)
    factoryOf(::AddWordTargetLanguageChangedEventHandler)
    factoryOf(::AddWordTranslateEventHandler)
    factoryOf(::AddWordSaveEventHandler)
    factoryOf(::AddWordBackEventHandler)
    factoryOf(::AddWordHideErrorDialogEventHandler)
}
