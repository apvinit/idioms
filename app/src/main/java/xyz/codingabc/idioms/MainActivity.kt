package xyz.codingabc.idioms

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.navHostFragment)
        toolbar.setupWithNavController(navController, null)
        setSupportActionBar(toolbar)

        MobileAds.initialize(this)

        val adRequest = AdRequest.Builder().addTestDevice("BC8E628EEE8616E4D117EA3A610DD0C6").build()
        adView.loadAd(adRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {

            R.id.action_toggle_night -> {
                if (item.isChecked) {
                    item.isChecked = false
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                } else {
                    item.isChecked = true
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)

        }
    }
}
