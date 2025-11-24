package ru.vsls.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.vsls.screens.R

@Composable
internal fun BoardBottomBar(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    onClose: () -> Unit,
) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = {
                coroutineScope.launch {
                    val skipPage = pagerState.currentPage - 1
                    pagerState.animateScrollToPage(skipPage)
                }
            },
            modifier = Modifier.width(100.dp)
        ) {
            LeftContent(pagerState)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Dots(pagerState)
        }

        TextButton(
            onClick = {
                if (pagerState.currentPage == pagerState.pageCount - 1)
                    onClose()
                else
                    coroutineScope.launch {
                        val nextPage = pagerState.currentPage + 1
                        pagerState.animateScrollToPage(nextPage)
                    }
            },
            modifier = Modifier.width(100.dp)
        ) {
            RightContent(pagerState = pagerState)
        }
    }
}

@Composable
private fun RightContent(pagerState: PagerState) {
    if (pagerState.currentPage < pagerState.pageCount - 1)
        Text(
            stringResource(R.string.next),
            color = MaterialTheme.colorScheme.onPrimary
        )
    else
        Text(
            stringResource(R.string.close),
            color = MaterialTheme.colorScheme.onPrimary
        )
}

@Composable
private fun LeftContent(pagerState: PagerState) {
    if (pagerState.currentPage != 0)
        Text(
            text = stringResource(R.string.back),
            color = MaterialTheme.colorScheme.onPrimary
        )
    else
        Spacer(Modifier.height(0.dp))
}

@Composable
private fun Dots(pagerState: PagerState) {
    repeat(pagerState.pageCount) { iteration ->

        val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray

        Box(
            modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .background(color)
                .size(8.dp)
        )
    }
}
