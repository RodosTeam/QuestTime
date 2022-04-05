package dev.rodosteam.questtime.screen.common.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.screen.common.base.BaseFragment

class MenuFragment : BaseFragment() {

    private lateinit var btnToSearch: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.menu_fragment, container, false)
        btnToSearch = rootView.findViewById(R.id.toSearchQuestButton)
        setup()
        return rootView
    }

    private fun setup() {
        btnToSearch.setOnClickListener {
            mainActivity.screenNavigator.toSearchQuest()
        }
    }

    companion object {
        fun newInstance() = MenuFragment()
    }
}

