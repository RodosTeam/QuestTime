package dev.rodosteam.questtime.screen.common.base

import androidx.fragment.app.Fragment
import dev.rodosteam.questtime.App
import dev.rodosteam.questtime.screen.common.MainActivity

abstract class BaseFragment : Fragment() {

    protected val app: App get() = activity?.application as App
    protected val mainActivity get() = activity as MainActivity

}