package ru.vsls.ui.components.topbars

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.vsls.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(title: String, navigateTo: () -> Unit) {
    TopAppBar(
        windowInsets = WindowInsets(0),
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = navigateTo) {
                Icon(
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    painter = painterResource(R.drawable.answer),
                    contentDescription = stringResource(R.string.open_onbording)
                )
            }
        }
    )
}