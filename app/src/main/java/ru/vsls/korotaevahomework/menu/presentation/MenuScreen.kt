package ru.vsls.korotaevahomework.menu.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.menu.presentation.components.LogoutDialog
import ru.vsls.navigation.Screen
import ru.vsls.ui.components.topbars.MainTopAppBar

@Composable
fun MenuScreen(
    onExit: () -> Unit,
    onNavigate: (Screen) -> Unit,
    onNavigateToOnbording: () -> Unit,
) {

    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.menu_title),
                navigateTo = onNavigateToOnbording
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = 16.dp)
        ) {

            MenuItem(stringResource(R.string.my_loans)) {
                onNavigate(Screen.HistoryScreen)
            }

            MenuItem(stringResource(R.string.offers)) {
                onNavigate(Screen.OffersScreen)
            }

            MenuItem(stringResource(R.string.banks)) { /* TODO */ }

            MenuItem(stringResource(R.string.help)) { /* TODO */ }

            MenuItem(stringResource(R.string.language)) { /* TODO */ }

            MenuItem(stringResource(R.string.exit)) {
                showLogoutDialog = true
            }
        }
    }

    if (showLogoutDialog) {
        LogoutDialog(
            onConfirm = {
                showLogoutDialog = false
                onExit()
            },
            onCancel = { showLogoutDialog = false }
        )
    }
}

@Composable
fun MenuItem(title: String, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Icon(
            modifier = Modifier.size(ButtonDefaults.IconSize),
            painter = painterResource(R.drawable.arrow_right),
            contentDescription = stringResource(R.string.menu_next),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
