package dev.rodosteam.questtime.screen.settings

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.FragmentSettingsBinding
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import dev.rodosteam.questtime.utils.Languages
import dev.rodosteam.questtime.utils.LocaleManager.changeLanguageCode
import dev.rodosteam.questtime.utils.LocaleManager.getLanguagePosition


class SettingsFragment : BaseFragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingsViewModel =
            ViewModelProvider(this)[SettingsViewModel::class.java]

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val selected = getLanguagePosition(requireContext())
        binding.fragmentSettingsLangContainerValue.text = Languages.values()[selected].label
        binding.fragmentSettingsLangContainer.setOnClickListener {
            AlertDialog.Builder(requireContext()).setTitle(R.string.language_settings).setSingleChoiceItems(
                Languages.OPTIONS,
                selected
            ) { dialogInterface: DialogInterface?, i: Int ->
                changeLanguageCode(requireContext(), mainActivity, Languages.values()[i].code, i)
                dialogInterface?.dismiss()
            }.show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}