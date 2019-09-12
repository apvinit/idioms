package xyz.codingabc.idioms

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.navHostFragment)
    }
    private var nightMode = false
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, drawerLayout)
        navigation.setupWithNavController(navController)

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        nightMode = prefs.getBoolean(NIGHT_MODE_KEY, false)

        if (nightMode) AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)

        MobileAds.initialize(this)

        val adRequest =
            AdRequest.Builder().addTestDevice("BC8E628EEE8616E4D117EA3A610DD0C6").build()
        adView.loadAd(adRequest)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }
    companion object {
        private const val NIGHT_MODE_KEY = "night_mode"
    }
}
