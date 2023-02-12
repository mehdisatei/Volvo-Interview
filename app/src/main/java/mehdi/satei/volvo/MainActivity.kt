package mehdi.satei.volvo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mehdi.satei.volvo.presentation.navigation.NavGraph
import mehdi.satei.volvo.presentation.theme.VolvoTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val currentTheme = isSystemInDarkTheme()
            val toggleTheme: () -> Unit = {
                if (currentTheme) setDayTheme() else setDarkTheme()
            }
            VolvoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavGraph(toggleTheme)
                }
            }
        }
    }

    private fun setDayTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}