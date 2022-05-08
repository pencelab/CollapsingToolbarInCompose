package com.pencelab.collapsingtoolbarincompose.ui.composables

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.pencelab.collapsingtoolbarincompose.AnimalListPreviewParameterProvider
import com.pencelab.collapsingtoolbarincompose.data.model.Animal
import com.pencelab.collapsingtoolbarincompose.ui.theme.CollapsingToolbarInComposeTheme

@Preview(showBackground = true)
@Composable
fun EagerCatalogPreview(
    @PreviewParameter(AnimalListPreviewParameterProvider::class) animals: List<Animal>
) {
    CollapsingToolbarInComposeTheme {
        EagerCatalog(
            animals = animals,
            columns = 2,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun EagerCatalog(
    animals: List<Animal>,
    columns: Int,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(contentPadding.calculateTopPadding())
        )

        animals.chunked(columns).forEach { chunk ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(contentPadding.calculateStartPadding(LocalLayoutDirection.current))
                )

                chunk.forEach { animal ->
                    AnimalCard(
                        animal = animal,
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(1f)
                    )
                }

                val emptyCells = columns - chunk.size
                if (emptyCells > 0) {
                    Spacer(modifier = Modifier.weight(emptyCells.toFloat()))
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(contentPadding.calculateEndPadding(LocalLayoutDirection.current))
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(contentPadding.calculateBottomPadding())
        )
    }
}
