package dev.noelsrocha.panucci.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.noelsrocha.panucci.sampledata.sampleProducts
import dev.noelsrocha.panucci.ui.components.CheckoutItemCard
import dev.noelsrocha.panucci.ui.theme.PanucciTheme
import dev.noelsrocha.panucci.ui.uistate.CheckoutUiState

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    uiState: CheckoutUiState = CheckoutUiState(),
    onPopBackStack: () -> Unit = {}
) {
    val products = uiState.products

    Box(
        modifier.fillMaxSize()
    ) {
        LazyColumn(
            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 100.dp
            )
        ) {
            item {
                Text(
                    text = "Order",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(products) { p ->
                CheckoutItemCard(product = p)
                Spacer(Modifier.height(16.dp))
            }
            item {
                Column {
                    Text(
                        text = "Payment",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500)
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            Modifier.padding(vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "VISA",
                                Modifier
                                    .background(
                                        MaterialTheme.colorScheme.primaryContainer,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(4.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(text = "VISA Classic")
                                Text(text = "****-0976")
                            }
                        }
                        Icon(
                            Icons.Outlined.ArrowForwardIos,
                            contentDescription = null
                        )
                    }
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Confirm",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Order")
                            Text(text = "9.0")
                        }
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Service (10%)")
                            Text(text = "9.0")
                        }
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Total")
                            Text(text = "9.0")
                        }
                    }
                }
            }
        }
        Button(
            onClick = { onPopBackStack() },
            Modifier
                .padding(
                    16.dp
                )
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Icon(
                Icons.Outlined.AccountBalanceWallet,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Order",
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun CheckoutScreenPreview() {
    PanucciTheme {
        Surface {
            CheckoutScreen(
                uiState = CheckoutUiState(products = sampleProducts)
            )
        }
    }
}

@Preview
@Composable
fun CheckoutScreenWithoutProductsPreview() {
    PanucciTheme {
        Surface {
            CheckoutScreen()
        }
    }
}