package dev.noelsrocha.panucci

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dev.noelsrocha.panucci.sampledata.sampleProducts
import dev.noelsrocha.panucci.ui.navigation.graphs.checkoutRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.drinksListRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.highlightsListRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.menuListRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.navigateToCheckout
import dev.noelsrocha.panucci.ui.navigation.graphs.navigateToProductDetails
import dev.noelsrocha.panucci.ui.navigation.graphs.productDetailsRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.productIdArgument
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            PanucciApp(navController = navController)
        }
    }

    @Test
    fun panucciNavHost_verifyStartDestination() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule
            .onNodeWithText("Highlights of the Day")
            .assertIsDisplayed()
    }

    @Test
    fun panucciNavHost_verifyIfMenuScreenIsDisplayed() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.onNodeWithText("Menu")
            .performClick()

        composeTestRule.onAllNodesWithText("Menu")
            .assertCountEquals(2)

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, menuListRoute)
    }

    @Test
    fun panucciNavHost_verifyIfDrinksScreenIsDisplayed() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.onNodeWithText("Drinks")
            .performClick()

        composeTestRule.onAllNodesWithText("Drinks")
            .assertCountEquals(2)

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, drinksListRoute)
    }

    @Test
    fun panucciNavHost_verifyIfHighlightsScreenIsDisplayed() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.onNodeWithText("Highlights")
            .performClick()

        composeTestRule.onNodeWithText("Highlights of the Day")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, highlightsListRoute)
    }

    @Test
    fun panucciNavHost_verifyIfProductDetailsScreenIsDisplayedFromHighlightsScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule
            .onAllNodesWithContentDescription("Highlight Product Card Item")
            .onFirst()
            .performClick()

        composeTestRule.waitUntil(3000) {
            composeTestRule.onAllNodesWithText("Something went wrong...").fetchSemanticsNodes().size == 1
        }

        composeTestRule
            .onNodeWithText("Something went wrong...")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "$productDetailsRoute/{$productIdArgument}")
    }

    @Test
    fun panucciNavHost_verifyIfProductDetailsScreenIsDisplayedFromMenuScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.onNodeWithText("Menu")
            .performClick()

        composeTestRule
            .onAllNodesWithContentDescription("Menu Product Card Item")
            .onFirst()
            .performClick()

        composeTestRule.waitUntil(3000) {
            composeTestRule
                .onAllNodesWithContentDescription("Product Details Content")
                .fetchSemanticsNodes().size == 1
        }

        composeTestRule
            .onNodeWithContentDescription("Product Details Content")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "$productDetailsRoute/{$productIdArgument}")
    }

    @Test
    fun panucciNavHost_verifyIfProductDetailsScreenIsDisplayedFromDrinksScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.onNodeWithText("Drinks")
            .performClick()

        composeTestRule
            .onAllNodesWithContentDescription("Drinks Product Card Item")
            .onFirst()
            .performClick()

        composeTestRule.waitUntil(3000) {
            composeTestRule
                .onAllNodesWithContentDescription("Product Details Content")
                .fetchSemanticsNodes().size == 1
        }

        composeTestRule
            .onNodeWithContentDescription("Product Details Content")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "$productDetailsRoute/{$productIdArgument}")
    }

    @Test
    fun panucciNavHost_verifyIfCheckoutScreenIsDisplayedFromHighlightsScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule
            .onNodeWithText("Highlights")
            .performClick()

        composeTestRule
            .onAllNodesWithText("Order")
            .onFirst()
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, checkoutRoute)
    }

    @Test
    fun panucciNavHost_verifyIfCheckoutScreenIsDisplayedFromDrinksScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule
            .onNodeWithText("Drinks")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Add order")
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, checkoutRoute)
    }

    @Test
    fun panucciNavHost_verifyIfCheckoutScreenIsDisplayedFromMenuScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule
            .onNodeWithText("Menu")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Add order")
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, checkoutRoute)
    }

    @Test
    fun panucciNavHost_verifyIfCheckoutScreenIsDisplayedFromProductDetailsScreen() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.runOnUiThread {
            navController.navigateToProductDetails(sampleProducts.first().id)
        }

        composeTestRule.waitUntil(3000) {
            composeTestRule.onAllNodesWithText("Order").fetchSemanticsNodes().size == 1
        }

        composeTestRule
            .onNodeWithContentDescription("Order Button")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Checkout Screen")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, checkoutRoute)
    }

    @Test
    fun panucciNavHost_verifyIfSnackbarIsDisplayedWhenFinishTheOrder() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule.runOnUiThread {
            navController.navigateToCheckout()
        }

        composeTestRule
            .onNodeWithContentDescription("Finish Order")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Order Completed Snackbar")
            .assertIsDisplayed()
    }

    @Test
    fun panucciNavHost_verifyIfFabIsDisplayedOnlyInMenuOrDrinksDestination() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule
            .onNodeWithText("Menu")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Add order")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Drinks")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Add order")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Highlights")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Add order")
            .assertDoesNotExist()
    }

    @Test
    fun panucciNavHost_verifyIfBottomAppBarIsDisplayedOnlyInHomeGraphNavigation() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule
            .onNodeWithText("Menu")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Bottom App Bar")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Drinks")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Bottom App Bar")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Highlights")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Bottom App Bar")
            .assertIsDisplayed()

        composeTestRule.runOnUiThread {
            navController.navigateToProductDetails(sampleProducts.first().id)
        }

        composeTestRule
            .onNodeWithContentDescription("Bottom App Bar")
            .assertDoesNotExist()

        composeTestRule.runOnUiThread {
            navController.navigateToCheckout()
        }

        composeTestRule
            .onNodeWithContentDescription("Bottom App Bar")
            .assertDoesNotExist()
    }

    @Test
    fun panucciNavHost_verifyIfTopAppBarIsDisplayedOnlyInHomeGraphNavigation() {
        composeTestRule.onRoot().printToLog("panucci app")

        composeTestRule
            .onNodeWithText("Menu")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Top App Bar")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Drinks")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Top App Bar")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Highlights")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Top App Bar")
            .assertIsDisplayed()

        composeTestRule.runOnUiThread {
            navController.navigateToProductDetails(sampleProducts.first().id)
        }

        composeTestRule
            .onNodeWithContentDescription("Top App Bar")
            .assertDoesNotExist()

        composeTestRule.runOnUiThread {
            navController.navigateToCheckout()
        }

        composeTestRule
            .onNodeWithContentDescription("Top App Bar")
            .assertDoesNotExist()
    }
}