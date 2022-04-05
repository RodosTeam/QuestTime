package dev.rodosteam.questtime.screen.common.mvp

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

abstract class MvpViewBase : MvpView {

    protected fun <T : View> findViewById(id: Int): T {
        return rootView.findViewById(id)
    }

    protected val context: Context
        get() = rootView.context

}
