package dev.noelsrocha.panucci.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.noelsrocha.panucci.model.Product
import dev.noelsrocha.panucci.sampledata.sampleProducts
import dev.noelsrocha.panucci.ui.components.DrinkProductCard
import dev.noelsrocha.panucci.ui.theme.PanucciTheme
import dev.noelsrocha.panucci.ui.theme.caveatFont
import dev.noelsrocha.panucci.ui.uistate.DrinksListUiState


@Composable
fun DrinksListScreen(
    modifier: Modifier = Modifier,
    title: String = "Drinks",
    columns: Int = 2,
    onProductClick: (Product) -> Unit = {},
    uiState: DrinksListUiState = DrinksListUiState()
) {
    val products = uiState.products

    Column(
        modifier
            .fillMaxSize()
    ) {
        Surface {
            Text(
                text = title,
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                fontFamily = caveatFont,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(columns),
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { p ->
                DrinkProductCard(
                    modifier = Modifier
                        .semantics { contentDescription = "Drinks Product Card Item" }
                        .clickable { onProductClick(p) },
                    product = p,
                )
            }
        }
    }
}

@Preview
@Composable
fun DrinksListScreenPreview() {
    PanucciTheme {
        Surface {
            DrinksListScreen(
                uiState = DrinksListUiState(products = sampleProducts),
                title = "Drinks"
            )
        }
    }
}