import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.screens.R
import ru.vsls.screens.languages.AppLanguage
import ru.vsls.ui.components.BaseButton
import ru.vsls.ui.components.topbars.BackTopAppBar

@Composable
fun LanguagesScreen(
    currentLang: AppLanguage,
    onBack: () -> Unit,
    onApplyLanguage: (AppLanguage) -> Unit,
) {
    var selectedLang by remember { mutableStateOf(currentLang) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackTopAppBar(
                title = stringResource(R.string.language_title),
                navigateTo = onBack
            )
        },
        bottomBar = {
            BaseButton(
                onClick = { onApplyLanguage(selectedLang) },
                text = stringResource(R.string.apply),
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = 16.dp)
        ) {

            LangItem(
                title = stringResource(R.string.russian),
                selected = selectedLang == AppLanguage.Russian,
                onItemClick = { selectedLang = AppLanguage.Russian }
            )

            LangItem(
                title = stringResource(R.string.english),
                selected = selectedLang == AppLanguage.English,
                onItemClick = { selectedLang = AppLanguage.English }
            )
        }
    }
}

@Composable
private fun LangItem(
    title: String,
    selected: Boolean,
    onItemClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        RadioButton(
            selected = selected,
            onClick = { onItemClick() }
        )
    }
}