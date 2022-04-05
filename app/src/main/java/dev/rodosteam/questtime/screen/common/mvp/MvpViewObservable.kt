package dev.rodosteam.questtime.screen.common.mvp

interface MvpViewObservable<ListenerType> : MvpView {
    fun registerListener(listener: ListenerType)
    fun unregisterListener(listener: ListenerType)
}
