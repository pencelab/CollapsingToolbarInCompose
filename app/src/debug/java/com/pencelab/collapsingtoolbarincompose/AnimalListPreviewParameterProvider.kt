package com.pencelab.collapsingtoolbarincompose

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pencelab.collapsingtoolbarincompose.data.model.Animal

class AnimalListPreviewParameterProvider: PreviewParameterProvider<List<Animal>> {
    override val values = sequenceOf(
        listOf(
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
    )
}
