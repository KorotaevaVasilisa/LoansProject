package ru.vsls.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.screens.R
import ru.vsls.screens.onboarding.model.OnBoardModel
import ru.vsls.ui.components.topbars.CloseTopAppBar

@Composable
fun OnboardingScreen(
    boards: List<OnBoardModel>,
    navigateTo: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { boards.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CloseTopAppBar(navigateTo = navigateTo)
        },
        bottomBar = {
            BoardBottomBar(pagerState, coroutineScope, onClose = navigateTo)
        }
    ) { paddingValues ->

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->

            OnBoardItem(
                page = boards[page],
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
fun OnBoardItem(page: OnBoardModel, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
    ) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = page.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = page.description,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(
        boards = listOf(
            OnBoardModel(
                imageRes = R.drawable.illuctration_1,
                title = stringResource(R.string.board_title_1),
                description = stringResource(R.string.board_description_1)
            )
        ), navigateTo = {})
}