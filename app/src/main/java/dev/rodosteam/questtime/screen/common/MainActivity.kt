package dev.rodosteam.questtime.screen.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    override fun registerListener(listener: BackPressedListener) {
        backPressedListeners.add(listener);
    }

    override fun unregisterListener(listener: BackPressedListener) {
        backPressedListeners.remove(listener)
    }
}