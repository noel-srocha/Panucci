package dev.noelsrocha.panucci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alura.panucci.ui.screens.HighlightsListScreen
import br.com.alura.panucci.ui.screens.ProductDetailsScreen
import dev.noelsrocha.panucci.sampledata.bottomAppBarItems
import dev.noelsrocha.panucci.sampledata.sampleProducts
import dev.noelsrocha.panucci.ui.components.BottomAppBarItem
import dev.noelsrocha.panucci.ui.components.PanucciBottomAppBar
import dev.noelsrocha.panucci.ui.navigation.AppDestination
import dev.noelsrocha.panucci.ui.screens.CheckoutScreen
import dev.noelsrocha.panucci.ui.screens.DrinksListScreen
import dev.noelsrocha.panucci.ui.screens.MenuListScreen
import dev.noelsrocha.panucci.ui.theme.PanucciTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntryState?.destination

//            LaunchedEffect(Unit) {
//                navController.addOnDestinationChangedListener { _, _, _ ->
//                    val routes = navController.currentBackStack.value.map { it.destination.route }
//
//                    Log.i("MainActivity", "onCreate: back track - $routes")
//                }
//            }

            val containsInBottomAppBarItems = currentDestination?.let { destination ->
                bottomAppBarItems.find { it.route.route == destination.route }
            } != null

            val isShowFAB = when (currentDestination?.route) {
                AppDestination.Menu.route, AppDestination.Drinks.route -> true
                else -> false
            }

            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val selectedItem by remember {
                        val item = currentDestination?.route?.let { route ->
                            bottomAppBarItems.firstOrNull { it.route.route == route }
                        } ?: bottomAppBarItems.first()

                        mutableStateOf(item)
                    }
                    PanucciApp(
                        bottomAppBarItemSelected = selectedItem,
                        onBottomAppBarItemSelectedChange = {
                            val route = it.route.route

                            navController.navigate(route) {
                                launchSingleTop = true
                                popUpTo(route)
                            }
                        },
                        onFabClick = {
                            navController.navigate(AppDestination.Checkout.route)
                        },
                        isShowTopBar = containsInBottomAppBarItems,
                        isShowBottomBar = containsInBottomAppBarItems,
                        isShowFAB = isShowFAB
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = AppDestination.Highlight.route
                        ) {
                            composable(AppDestination.Highlight.route) {
                                HighlightsListScreen(
                                    products = sampleProducts,
                                    onNavigateToDetails = {
                                        navController.navigate("${AppDestination.ProductDetails.route}/${it.id}")
                                    },
                                    onNavigateToCheckout = {
                                        navController.navigate(AppDestination.Checkout.route)
                                    }
                                )
                            }
                            composable(AppDestination.Menu.route) {
                                MenuListScreen(
                                    products = sampleProducts,
                                    onNavigateToDetails = {
                                        navController.navigate("${AppDestination.ProductDetails.route}/${it.id}")
                                    }
                                )
                            }
                            composable(AppDestination.Drinks.route) {
                                DrinksListScreen(
                                    products = sampleProducts,
                                    onNavigateToDetails = {
                                        navController.navigate("${AppDestination.ProductDetails.route}/${it.id}")
                                    }
                                )
                            }
                            composable("${AppDestination.ProductDetails.route}/productId") {
                                backStackEntry ->
                                val id = backStackEntry.arguments?.getString("productId")
                                sampleProducts.firstOrNull { product -> product.id == id }?.let {
                                    ProductDetailsScreen(
                                        product = it,
                                        onNavigateToCheckout = {
                                            navController.navigate(AppDestination.Checkout.route)
                                        }
                                    )
                                } ?: LaunchedEffect(Unit) {
                                    navController.navigateUp()
                                }
                            }
                            composable(AppDestination.Checkout.route) {
                                CheckoutScreen(
                                    products = sampleProducts,
                                    onPopbackStack = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanucciApp(
    bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    onFabClick: () -> Unit = {},
    isShowTopBar: Boolean = false,
    isShowBottomBar: Boolean = false,
    isShowFAB: Boolean = false,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            if (isShowTopBar) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Ristorante Panucci")
                    },
                )
            }
        },
        bottomBar = {
            if (isShowBottomBar) {
                PanucciBottomAppBar(
                    item = bottomAppBarItemSelected,
                    items = bottomAppBarItems,
                    onItemChange = onBottomAppBarItemSelectedChange,
                )
            }
        },
        floatingActionButton = {
            if (isShowFAB) {
                FloatingActionButton(
                    onClick = onFabClick
                ) {
                    Icon(
                        Icons.Filled.PointOfSale,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun PanucciAppPreview() {
    PanucciTheme {
        Surface {
            PanucciApp {}
        }
    }
}