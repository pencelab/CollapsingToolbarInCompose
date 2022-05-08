package com.pencelab.collapsingtoolbarincompose.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.pencelab.collapsingtoolbarincompose.AnimalListPreviewParameterProvider
import com.pencelab.collapsingtoolbarincompose.data.model.Animal
import com.pencelab.collapsingtoolbarincompose.ui.theme.CollapsingToolbarInComposeTheme

private const val BottomBarHeightFraction = 0.14f
private const val TopBarHeightFraction = BottomBarHeightFraction / 2
private val BarColor = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.5f)

@Preview(showBackground = true)
@Composable
fun AnimalCardPreview(
    @PreviewParameter(AnimalListPreviewParameterProvider::class) animals: List<Animal>
) {
    CollapsingToolbarInComposeTheme {
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            animals.take(2).forEach {
                AnimalCard(
                    animal = it,
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(1f)
                        .wrapContentHeight()
                )
            }
        }
    }
}

@Composable
fun AnimalCard(
    animal: Animal,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.aspectRatio(0.66f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(animal.imageResId),
                contentDescription = animal.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            TopBar()
            BottomBar(animal.name)
        }
    }
}

@Composable
private fun BoxScope.TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(TopBarHeightFraction)
            .background(BarColor)
            .padding(horizontal = 8.dp, vertical = 2.dp)
            .align(Alignment.TopCenter)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .wrapContentWidth()
                .align(Alignment.CenterStart),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Rounded.Star, contentDescription = null)
            Icon(imageVector = Icons.Rounded.Star, contentDescription = null)
            Icon(imageVector = Icons.Rounded.Star, contentDescription = null)
        }

        Row(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .wrapContentWidth()
                .align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(
                        color = LocalContentColor.current.copy(alpha = 0.0f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Rounded.VolumeUp,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(
                        color = LocalContentColor.current.copy(alpha = 0.0f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Explore,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun BoxScope.BottomBar(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(BottomBarHeightFraction)
            .background(BarColor)
            .align(Alignment.BottomCenter)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}
