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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.noelsrocha.panucci.ui.components.BottomAppBarItem
import dev.noelsrocha.panucci.ui.components.PanucciBottomAppBar
import dev.noelsrocha.panucci.ui.components.bottomAppBarItems
import dev.noelsrocha.panucci.ui.navigation.PanucciNavHost
import dev.noelsrocha.panucci.ui.navigation.graphs.drinksListRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.highlightsListRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.menuListRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.navigateSingleTopWithPopUpTo
import dev.noelsrocha.panucci.ui.navigation.graphs.navigateToCheckout
import dev.noelsrocha.panucci.ui.theme.PanucciTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val orderDoneMessage = backStackEntryState
                ?.savedStateHandle
                ?.getStateFlow<String?>("order_done", null)
                ?.collectAsState()
            val currentDestination = backStackEntryState?.destination
            val currentRoute = currentDestination?.route

//            LaunchedEffect(Unit) {
//                navController.addOnDestinationChangedListener { _, _, _ ->
//                    val routes = navController.currentBackStack.value.map { it.destination.route }
//
//                    Log.i("MainActivity", "onCreate: back track - $routes")
//                }
//            }

            val containsInBottomAppBarItems = when(currentRoute) {
                highlightsListRoute, drinksListRoute, menuListRoute -> true
                else -> false
            }

            val isShowFAB = when (currentRoute) {
                menuListRoute, drinksListRoute -> true
                else -> false
            }

            val snackbarHostState = remember { SnackbarHostState() }

            val rememberCoroutineScope = rememberCoroutineScope()

            orderDoneMessage?.value?.let { message ->
                rememberCoroutineScope.launch {
                    snackbarHostState.showSnackbar(message = message)
                }
            }

            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val selectedItem by remember {
                        val item = when(currentRoute) {
                            highlightsListRoute -> BottomAppBarItem.HighlightsList
                            drinksListRoute -> BottomAppBarItem.DrinksList
                            menuListRoute -> BottomAppBarItem.MenuList
                            else -> BottomAppBarItem.HighlightsList
                        }

                        mutableStateOf(item)
                    }
                    PanucciApp(
                        snackbarHostState = snackbarHostState,
                        bottomAppBarItemSelected = selectedItem,
                        onBottomAppBarItemSelectedChange = { item ->
                            navController.navigateSingleTopWithPopUpTo(item)
                        },
                        onFabClick = {
                            navController.navigateToCheckout()
                        },
                        isShowTopBar = containsInBottomAppBarItems,
                        isShowBottomBar = containsInBottomAppBarItems,
                        isShowFAB = isShowFAB
                    ) {
                        PanucciNavHost(navController = navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanucciApp(
    bottomAppBarItemSelected: BottomAppBarItem = BottomAppBarItem.HighlightsList,
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    onFabClick: () -> Unit = {},
    isShowTopBar: Boolean = false,
    isShowBottomBar: Boolean = false,
    isShowFAB: Boolean = false,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    content: @Composable () -> Unit,
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    Modifier.padding(8.dp)
                ) {
                    Text(text = data.visuals.message)
                }
            }
        },
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
            PanucciApp(content = {})
        }
    }
}