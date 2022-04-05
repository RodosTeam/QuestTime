package dev.rodosteam.questtime.screen.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.screen.common.nav.BackPressDispatcher
import dev.rodosteam.questtime.screen.common.nav.BackPressedListener
import dev.rodosteam.questtime.screen.common.nav.ScreenNavigator

class MainActivity : AppCompatActivity(), BackPressDispatcher {

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet()

    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screenNavigator = ScreenNavigator(supportFragmentManager, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        screenNavigator.onSaveInstanceState(outState)
    }

    override fun registerListener(listener: BackPressedListener) {
        backPressedListeners.add(listener)
    }

    override fun unregisterListener(listener: BackPressedListener) {
        backPressedListeners.remove(listener)
    }

    override fun onBackPressed() {
        var isBackPressConsumedByAnyListener = false
        backPressedListeners.forEach { listener ->
            if (listener.onBackPressed()) {
                isBackPressConsumedByAnyListener = true
            }
        }
        if (!isBackPressConsumedByAnyListener) {
            super.onBackPressed()
        }
    }
}