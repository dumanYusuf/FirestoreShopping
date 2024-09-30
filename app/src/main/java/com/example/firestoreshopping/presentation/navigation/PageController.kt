import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firestoreshopping.R
import com.example.firestoreshopping.Screan
import com.example.firestoreshopping.presentation.basket_page_view.view.BasketPage
import com.example.firestoreshopping.presentation.favori_page_view.view.FavoriPage
import com.example.firestoreshopping.presentation.home_page_view.view.HomePage
import com.example.firestoreshopping.presentation.login_view.view.LoginPage
import com.example.firestoreshopping.presentation.login_view.view.RegisterPage
import com.example.firestoreshopping.presentation.person_page_view.view.PersonPage
import com.google.firebase.auth.FirebaseAuth
import java.net.URLDecoder

@Composable
fun PageController() {
    val items = listOf("Home", "Favori", "Basket", "Person")
    val currentIndex = remember { mutableStateOf(0) }
    val controller = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    var isUserLoggedIn by remember { mutableStateOf(auth.currentUser != null) }

    LaunchedEffect(true) {
        auth.addAuthStateListener { firebaseAuth ->
            isUserLoggedIn = firebaseAuth.currentUser != null
        }
    }

    Scaffold(
        bottomBar = {
            if (isUserLoggedIn) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = currentIndex.value == index,
                            onClick = {
                                currentIndex.value = index
                                when (index) {
                                    0 -> controller.navigate(Screan.HomePage.route) {
                                        popUpTo(Screan.HomePage.route) { inclusive = true }
                                    }
                                    1 -> controller.navigate(Screan.FavoriPage.route) {
                                        popUpTo(Screan.FavoriPage.route) { inclusive = true }
                                    }
                                    2 -> controller.navigate(Screan.BasketPage.route) {
                                        popUpTo(Screan.BasketPage.route) { inclusive = true }
                                    }
                                    3 -> controller.navigate(Screan.PersonPage.route) {
                                        popUpTo(Screan.PersonPage.route) { inclusive = true }
                                    }
                                }
                            },
                            icon = {
                                when (item) {
                                    "Home" -> Icon(painter = painterResource(id = R.drawable.home), contentDescription = null)
                                    "Favori" -> Icon(painter = painterResource(id = R.drawable.favori), contentDescription = null)
                                    "Basket" -> Icon(painter = painterResource(id = R.drawable.basket), contentDescription = null)
                                    "Person" -> Icon(painter = painterResource(id = R.drawable.person), contentDescription = null)
                                }
                            },
                            label = { Text(text = item) }
                        )
                    }
                }
            }

        }
    ) { innerPadding ->
        NavHost(navController = controller, startDestination = if (isUserLoggedIn) Screan.HomePage.route else Screan.RegisterPage.route, Modifier.padding(innerPadding)) {
            composable(Screan.RegisterPage.route) {
                RegisterPage(navController = controller)
                currentIndex.value=0
            }
            composable(Screan.LoginPage.route) {
                LoginPage(navController = controller)
                currentIndex.value=0
            }
            composable(Screan.HomePage.route) {
                HomePage()
            }
            composable(Screan.FavoriPage.route) {
                FavoriPage()
            }
            composable(Screan.BasketPage.route) {
                BasketPage()
            }
            composable(Screan.PersonPage.route) {
                PersonPage()
            }
        }
    }
}