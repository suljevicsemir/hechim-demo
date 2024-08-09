package com.semirsuljevic.hechimdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.hechimdemo.R
import com.semirsuljevic.hechimdemo.ui.AppNavigator
import com.semirsuljevic.hechimdemo.ui.theme.HechimDemoTheme
import com.semirsuljevic.ui.api.theme.HechimTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                resources.getColor(R.color.black),
                resources.getColor(R.color.black)
            )
        )
        super.onCreate(savedInstanceState)

        setContent {
            HechimDemoTheme {
                val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {}
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = HechimTheme.colors.backgroundDefault
                ) {
                    AppNavigator(viewModelStoreOwner = viewModelStoreOwner)
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HechimDemoTheme {
        Greeting("Android")
    }
}
