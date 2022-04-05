package dev.rodosteam.questtime.screen.common.nav

interface BackPressDispatcher {

    fun registerListener(listener: BackPressedListener)
    fun unregisterListener(listener: BackPressedListener)

}
