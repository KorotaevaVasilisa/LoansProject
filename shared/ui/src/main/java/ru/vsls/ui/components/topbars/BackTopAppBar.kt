package ru.vsls.ui.components.topbars

import androidx.compose.foundation.layout.size
import ru.vsls.ui.R


@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun BackTopAppBar(title: String, navigateTo: () -> Unit) {
    androidx.compose.material3.TopAppBar(
        windowInsets = androidx.compose.foundation.layout.WindowInsets(0),
        title = { androidx.compose.material3.Text(text = title) },
        navigationIcon = {
            androidx.compose.material3.IconButton(onClick = navigateTo) {
                androidx.compose.material3.Icon(
                    modifier = androidx.compose.ui.Modifier.Companion.size(androidx.compose.material3.ButtonDefaults.IconSize),
                    painter = androidx.compose.ui.res.painterResource(R.drawable.arrow_back),
                    contentDescription = androidx.compose.ui.res.stringResource(R.string.click_back),
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}