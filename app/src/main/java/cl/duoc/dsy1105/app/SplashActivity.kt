package cl.duoc.dsy1105.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_DuocDSY1105_Splash) // Asegura el tema de splash
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(600) // pequeña pausa para mostrar el splash
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}
