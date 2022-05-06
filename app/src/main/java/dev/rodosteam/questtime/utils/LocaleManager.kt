package dev.rodosteam.questtime.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import androidx.core.app.ActivityCompat
import java.util.Locale


object LocaleManager {

    fun setLocale(context : Context): Context {
        return updateResources(context, getLanguageCode(context))
    }

    fun setLanguageCode(context: Context, code: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("languageCodePrefs", Context.MODE_PRIVATE)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putString("languageCodeValue", code)
        sharedPreferencesEditor.apply()
    }

    fun getLanguageCode(context: Context): String {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("languageCodePrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("languageCodeValue", getSystemOrDefaultLanguage(context).code)!!
    }

    fun setLanguagePosition(context: Context, position: Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("languageCodePrefs", Context.MODE_PRIVATE)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putInt("languagePositionValue", position)
        sharedPreferencesEditor.apply()
    }

    fun getLanguagePosition(context: Context): Int {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("languageCodePrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("languagePositionValue", getSystemOrDefaultLanguage(context).ordinal)
    }

    fun changeLanguageCode(context: Context, activity : Activity, languageCode: String, position: Int) {
        if (getLanguageCode(context) != languageCode) {
            setLanguageCode(context, languageCode)
            setLanguagePosition(context, position)
           ActivityCompat.recreate(activity)
        }
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    private fun getSystemOrDefaultLanguage(context: Context) : Languages {
        val lang : String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lang = context.resources.configuration.locales[0].language
        } else {
            @Suppress("DEPRECATION")
            lang = context.resources.configuration.locale.language
        }
        return Languages.findByCode(lang)
    }
}