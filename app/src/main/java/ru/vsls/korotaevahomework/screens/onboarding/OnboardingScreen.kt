package ru.vsls.korotaevahomework.screens.onboarding

import android.R.attr.bottom
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.screens.onboarding.model.OnBoardModel
import ru.vsls.ui.components.topbars.CloseTopAppBar

@Composable
fun OnboardingScreen(
    boards: List<OnBoardModel>,
    navigateTo: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { boards.size })

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CloseTopAppBar(navigateTo = navigateTo)
        },
        bottomBar = {
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(1.dp, MaterialTheme.colorScheme.secondary),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
        }
    ) { paddingValues ->


        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            OnBoardItem(boards[page])
        }
    }
}

@Composable
fun OnBoardItem(page: OnBoardModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        Text(
            text = page.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = page.description,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(emptyList(), {})
}