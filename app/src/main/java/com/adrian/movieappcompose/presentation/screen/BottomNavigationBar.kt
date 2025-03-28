package com.adrian.movieappcompose.presentation.screen

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(
    selectedTab: BottomNavigationMenu,
    onTabSelected: (BottomNavigationMenu) -> Unit
) {

    NavigationBar {
        BottomNavigationMenu.entries.forEach { tab ->
            NavigationBarItem(
                icon = { Icon(tab.icon, contentDescription = tab.label) },
                label = { Text(tab.label) },
                selected = tab == selectedTab,
                onClick = { onTabSelected(tab) }
            )
        }
    }
}
