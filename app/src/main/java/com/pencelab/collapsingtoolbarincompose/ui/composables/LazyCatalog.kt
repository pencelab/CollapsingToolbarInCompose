package com.pencelab.collapsingtoolbarincompose.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.pencelab.collapsingtoolbarincompose.AnimalListPreviewParameterProvider
import com.pencelab.collapsingtoolbarincompose.data.model.Animal
import com.pencelab.collapsingtoolbarincompose.ui.theme.CollapsingToolbarInComposeTheme

@Preview(showBackground = true)
@Composable
fun LazyCatalogPreview(
    @PreviewParameter(AnimalListPreviewParameterProvider::class) animals: List<Animal>
) {
    CollapsingToolbarInComposeTheme {
        LazyCatalog(
            animals = animals,
            columns = 2,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun LazyCatalog(
    animals: List<Animal>,
    columns: Int,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    val chunkedList = remember(animals, columns) {
        animals.chunked(columns)
    }

    LazyColumn(
        state = listState,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(chunkedList) { chunk ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
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
            }
        }
    }
}
