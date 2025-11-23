package ru.vsls.ui.components.topbars

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.vsls.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun CloseTopAppBar(navigateTo: () -> Unit) {
    TopAppBar(
        windowInsets = WindowInsets(0),
        title = { },
        navigationIcon = {
            IconButton(onClick = navigateTo) {
                Icon(
                    modifier = Modifier.size(androidx.compose.material3.ButtonDefaults.IconSize),
                    painter = painterResource(R.drawable.close),
                    contentDescription = stringResource(R.string.close),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}