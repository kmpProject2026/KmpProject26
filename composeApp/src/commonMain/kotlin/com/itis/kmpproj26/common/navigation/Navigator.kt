package com.itis.kmpproj26.common.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val state: NavigationState) {
    fun navigate(route: NavKey) {
        state.stacksInUse.add(route)
    }

    fun navigateClearingStack(route: NavKey) {
        state.stacksInUse.clear()
        state.stacksInUse.add(route)
    }

    fun navigateBack() {
        if (state.stacksInUse.size > 1) {
            state.stacksInUse.removeLastOrNull()
        }
    }
}
