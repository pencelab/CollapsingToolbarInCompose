package com.pencelab.collapsingtoolbarincompose.ui.screens

import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.pencelab.collapsingtoolbarincompose.AnimalListPreviewParameterProvider
import com.pencelab.collapsingtoolbarincompose.data.model.Animal
import com.pencelab.collapsingtoolbarincompose.ui.composables.CollapsingToolbar
import com.pencelab.collapsingtoolbarincompose.R
import com.pencelab.collapsingtoolbarincompose.ui.composables.LazyCatalog
import com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar.FixedScrollFlagState
import com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar.ToolbarState
import com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar.scrollflags.*
import com.pencelab.collapsingtoolbarincompose.ui.theme.CollapsingToolbarInComposeTheme
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

private val MinToolbarHeight = 96.dp
private val MaxToolbarHeight = 176.dp

@Composable
private fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    return rememberSaveable(saver = ScrollState.Saver) {
        ScrollState(toolbarHeightRange)
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogPreview(
    @PreviewParameter(AnimalListPreviewParameterProvider::class) animals: List<Animal>
) {
    CollapsingToolbarInComposeTheme {
        Catalog(
            animals = animals,
            columns = 2,
            onPrivacyTipButtonClicked = {},
            onSettingsButtonClicked = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Catalog(
    animals: List<Animal>,
    columns: Int,
    onPrivacyTipButtonClicked: () -> Unit,
    onSettingsButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
    val toolbarState = rememberToolbarState(toolbarHeightRange)
    val listState = rememberLazyListState()

    val scope = rememberCoroutineScope()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                toolbarState.scrollTopLimitReached = listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
                toolbarState.scrollOffset = toolbarState.scrollOffset - available.y
                return Offset(0f, toolbarState.consumed)
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (available.y > 0) {
                    scope.launch {
                        animateDecay(
                            initialValue = toolbarState.height + toolbarState.offset,
                            initialVelocity = available.y,
                            animationSpec = FloatExponentialDecaySpec()
                        ) { value, velocity ->
                            toolbarState.scrollTopLimitReached = listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
                            toolbarState.scrollOffset = toolbarState.scrollOffset - (value - (toolbarState.height + toolbarState.offset))
                            if (toolbarState.scrollOffset == 0f) scope.coroutineContext.cancelChildren()
                        }
                    }
                }

                return super.onPostFling(consumed, available)
            }
        }
    }

    Box(modifier = modifier.nestedScroll(nestedScrollConnection)) {
        LazyCatalog(
            animals = animals,
            columns = columns,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { translationY = toolbarState.height + toolbarState.offset }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { scope.coroutineContext.cancelChildren() }
                    )
                },
            listState = listState,
            contentPadding = PaddingValues(bottom = if (toolbarState is FixedScrollFlagState) MinToolbarHeight else 0.dp)
        )
        CollapsingToolbar(
            backgroundImageResId = R.drawable.toolbar_background,
            progress = toolbarState.progress,
            onPrivacyTipButtonClicked = onPrivacyTipButtonClicked,
            onSettingsButtonClicked = onSettingsButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(with(LocalDensity.current) { toolbarState.height.toDp() })
                .graphicsLayer { translationY = toolbarState.offset }
        )
    }
}