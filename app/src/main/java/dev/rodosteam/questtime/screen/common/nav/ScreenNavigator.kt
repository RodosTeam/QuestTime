package dev.rodosteam.questtime.screen.common.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.screen.common.menu.MenuFragment
import dev.rodosteam.questtime.screen.searchquest.SearchQuestFragment

class ScreenNavigator(
    fragmentManager: FragmentManager,
    savedInstanceState: Bundle?
) : FragNavController.RootFragmentListener {

    private val fragNavController = FragNavController(fragmentManager, R.id.container)

    init {
        fragNavController.rootFragmentListener = this
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState);
    }

    fun toSearchQuest() {
        fragNavController.pushFragment(SearchQuestFragment.newInstance())
    }

    override val numberOfRootFragments: Int
        get() = 1

    override fun getRootFragment(index: Int): Fragment {
        return MenuFragment.newInstance()
    }

    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun navigateUp() {
        fragNavController.popFragment()
    }

}
