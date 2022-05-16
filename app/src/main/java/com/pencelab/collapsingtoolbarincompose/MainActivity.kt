package com.pencelab.collapsingtoolbarincompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pencelab.collapsingtoolbarincompose.data.model.Animal
import com.pencelab.collapsingtoolbarincompose.ui.screens.Catalog
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
        val context = LocalContext.current
        Catalog(
            animals = animals,
            columns = 2,
            onPrivacyTipButtonClicked = {
                Toast.makeText(context, "Privacy Tip button clicked!", Toast.LENGTH_SHORT).show()
            },
            onSettingsButtonClicked = {
                Toast.makeText(context, "Settings button clicked!", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CollapsingToolbarInComposeAppPreview(
    @PreviewParameter(AnimalListPreviewParameterProvider::class) animals: List<Animal>
) {
    CollapsingToolbarInComposeApp(animals)
}
