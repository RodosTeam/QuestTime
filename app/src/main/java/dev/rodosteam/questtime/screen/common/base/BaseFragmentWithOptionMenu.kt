package dev.rodosteam.questtime.screen.common.base

import android.os.Bundle
import android.view.*

open class BaseFragmentWithOptionMenu : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}