package com.itis.kmpproj26.common.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class NavigationState(
    val backStacks: NavBackStack<NavKey>,
) {
    val stacksInUse: NavBackStack<NavKey>
        get() = backStacks
}
