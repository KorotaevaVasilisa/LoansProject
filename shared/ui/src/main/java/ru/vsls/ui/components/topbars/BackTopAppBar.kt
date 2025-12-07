package ru.vsls.ui.components.topbars

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.vsls.ui.R


@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun BackTopAppBar(title: String, navigateTo: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = navigateTo) {
                Icon(
                    modifier = Modifier.size(androidx.compose.material3.ButtonDefaults.IconSize),
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = stringResource(R.string.click_back),
                    tint =MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}