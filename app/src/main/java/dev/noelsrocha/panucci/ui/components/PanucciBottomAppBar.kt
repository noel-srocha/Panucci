package dev.noelsrocha.panucci.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.navOptions
import dev.noelsrocha.panucci.ui.navigation.graphs.drinksListRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.highlightsListRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.menuListRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.navigateToDrinksList
import dev.noelsrocha.panucci.ui.navigation.graphs.navigateToHighlightsList
import dev.noelsrocha.panucci.ui.navigation.graphs.navigateToMenuList
import dev.noelsrocha.panucci.ui.theme.PanucciTheme

sealed class BottomAppBarItem(
    val label: String,
    val icon: ImageVector,
) {
    data object HighlightsList : BottomAppBarItem(
        label = "Destaques",
        icon = Icons.Filled.AutoAwesome
    )

    data object MenuList : BottomAppBarItem(
        label = "Menu",
        icon = Icons.Filled.RestaurantMenu
    )

    data object DrinksList : BottomAppBarItem(
        label = "Bebidas",
        icon = Icons.Outlined.LocalBar
    )
}

val bottomAppBarItems = listOf(
    BottomAppBarItem.HighlightsList,
    BottomAppBarItem.MenuList,
    BottomAppBarItem.DrinksList
)

@Composable
fun PanucciBottomAppBar(
    item: BottomAppBarItem,
    modifier: Modifier = Modifier,
    items: List<BottomAppBarItem> = emptyList(),
    onItemChange: (BottomAppBarItem) -> Unit = {}
) {
    NavigationBar(modifier) {
        items.forEach {
            val label = it.label
            val icon = it.icon
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) },
                selected = item.label == label,
                onClick = {
                    onItemChange(it)
                }
            )
        }
    }
}

@Preview
@Composable
fun PanucciBottomAppBarPreview() {
    PanucciTheme {
        PanucciBottomAppBar(
            item = bottomAppBarItems.first(),
            items = bottomAppBarItems
        )
    }
}