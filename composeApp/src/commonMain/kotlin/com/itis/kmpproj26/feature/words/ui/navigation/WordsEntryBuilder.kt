package com.itis.kmpproj26.feature.words.ui.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.itis.kmpproj26.common.navigation.Navigator
import com.itis.kmpproj26.common.navigation.Route
import com.itis.kmpproj26.feature.words.ui.screen.add.AddWordScreen
import com.itis.kmpproj26.feature.words.ui.screen.cards.WordsCardsScreen
import com.itis.kmpproj26.feature.words.ui.screen.list.WordsListScreen

fun EntryProviderScope<NavKey>.wordsEntryBuilder(
    navigator: Navigator,
) {
    entry<Route.WordsCards> {
        WordsCardsScreen(
            navigateToAdd = { navigator.navigate(WordsRoute.Add) },
            navigateToList = { navigator.navigate(WordsRoute.List) },
        )
    }

    entry<WordsRoute.List> {
        WordsListScreen(
            navigateBack = { navigator.navigateBack() },
            navigateToAdd = { navigator.navigate(WordsRoute.Add) },
        )
    }

    entry<WordsRoute.Add> {
        AddWordScreen(
            navigateBack = { navigator.navigateBack() },
        )
    }
}
