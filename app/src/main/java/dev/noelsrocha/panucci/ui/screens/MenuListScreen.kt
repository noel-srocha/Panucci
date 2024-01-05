package dev.noelsrocha.panucci.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.noelsrocha.panucci.model.Product
import dev.noelsrocha.panucci.sampledata.sampleProducts
import dev.noelsrocha.panucci.ui.components.MenuProductCard
import dev.noelsrocha.panucci.ui.theme.PanucciTheme
import dev.noelsrocha.panucci.ui.theme.caveatFont

@Composable
fun MenuListScreen(
    modifier: Modifier = Modifier,
    title: String = "Menu",
    products: List<Product> = emptyList(),
) {
    Column(
        modifier.fillMaxSize()
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
        LazyColumn(
            modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { p ->
                MenuProductCard(
                    product = p,
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuListScreenPreview() {
    PanucciTheme {
        Surface {
            MenuListScreen(
                products = sampleProducts
            )
        }
    }
}