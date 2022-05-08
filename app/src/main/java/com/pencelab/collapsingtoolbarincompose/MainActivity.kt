package com.pencelab.collapsingtoolbarincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pencelab.collapsingtoolbarincompose.data.model.Animal
import com.pencelab.collapsingtoolbarincompose.ui.theme.CollapsingToolbarInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animals = listOf(
            Animal("Jaguar", R.drawable.jaguar),
            Animal("Scarlet Macaw", R.drawable.scarlet_macaw),
            Animal("Baby Green Sea Turtle", R.drawable.baby_green_sea_turtle),
            Animal("Red-Eyed Tree Frog", R.drawable.red_eyed_tree_frog),
            Animal("Hoffman's Two-Toed Sloth", R.drawable.hoffmans_two_toed_sloth),
            Animal("Resplendent Quetzal", R.drawable.resplendent_quetzal),
            Animal("White-Nosed Coati", R.drawable.white_nosed_coati),
            Animal("Green Iguana", R.drawable.greeen_iguana),
            Animal("Keel-Billed Toucan", R.drawable.keel_billed_toucan),
            Animal("Capuchin Monkey", R.drawable.capuchin_monkey),
            Animal("Emerald Basilisk", R.drawable.emerald_basilisk),
            Animal("King Vulture", R.drawable.king_vulture)
        )

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
            }

            CollapsingToolbarInComposeApp(animals)
        }
    }
}

@Composable
fun CollapsingToolbarInComposeApp(animals: List<Animal>) {
    CollapsingToolbarInComposeTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_costa),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .height(16.dp)
                        .wrapContentWidth()
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_rica),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .height(16.dp)
                        .wrapContentWidth()
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_wildlife),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .height(32.dp)
                        .wrapContentWidth()
                )
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
            )
            animals.forEach {
                Text(
                    text = it.name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollapsingToolbarInComposeAppPreview(
    @PreviewParameter(AnimalListPreviewParameterProvider::class) animals: List<Animal>
) {
    CollapsingToolbarInComposeApp(animals)
}
