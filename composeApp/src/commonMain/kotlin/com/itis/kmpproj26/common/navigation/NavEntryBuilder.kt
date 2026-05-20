package com.itis.kmpproj26.common.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey


typealias NavEntryBuilder = EntryProviderScope<NavKey>.(Navigator) -> Unit
