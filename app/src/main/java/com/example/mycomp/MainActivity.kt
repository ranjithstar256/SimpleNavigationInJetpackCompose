package com.example.mycomp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycomp.ui.theme.MyCompTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCompTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(applicationContext)
                }
            }
        }
    }
}

@Composable
fun Greeting(context: Context) {
   /// Text(text = "Hello")
    App(context)
}

@Composable
fun App(context: Context) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("abt") {
            AboutScreen(context)
        }

        composable("detail/{itemId}/{itemId2}/{abc}/{hi}") {
                backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            val itemId2 = backStackEntry.arguments?.getString("itemId2")
            val s1 = backStackEntry.arguments?.getString("abc")
            DetailScreen(itemId = itemId, itemId2 = itemId2, xyz = s1, hh = "some")
        }
    }
}

@Composable
fun DetailScreen(itemId: String?, itemId2: String?, xyz: String?,hh:String?) {

    Column(Modifier.fillMaxSize()) {
        Text("Item ID: $itemId \n $itemId2 \n $xyz \n $hh")
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column {
        Button(
            onClick = {
                navController.navigate("detail/ranjith/chennai/android/housing") }
        ) {
            Text("Go to Detail Screen")
        }
        
        Button(onClick = {
            navController.navigate("abt") }) {
            Text(text = "Go to About screen")
        }
    }
}

@Composable
fun AboutScreen(context: Context) {
    Image(imageVector = Icons.Default.Email, contentDescription = "",
        Modifier.selectable(true,true,null) {
            Toast.makeText(context, "Thank you!", Toast.LENGTH_LONG).show()
        })
}
