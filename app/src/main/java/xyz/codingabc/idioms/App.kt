package xyz.codingabc.idioms

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

class App : Application() {
    private var nightMode = false
    private lateinit var prefs: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        nightMode = prefs.getBoolean(NIGHT_MODE_KEY, false)

        if (nightMode) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
    companion object {
        private const val NIGHT_MODE_KEY = "night_mode"
    }
}