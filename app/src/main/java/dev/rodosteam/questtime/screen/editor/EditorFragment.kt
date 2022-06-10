package dev.rodosteam.questtime.screen.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dev.rodosteam.questtime.api.dto.QuestMetaDto
import dev.rodosteam.questtime.databinding.FragmentEditorBinding
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import dev.rodosteam.questtime.utils.Cat
import dev.rodosteam.questtime.utils.hasInternet

class EditorFragment : BaseFragment() {

    private var _binding: FragmentEditorBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        init()

        return root
    }

    private fun init() {

        binding.editorGetCatButton?.setOnClickListener {
            if (!context?.let { it1 -> hasInternet(it1) }!!) {
                Toast.makeText(context!!, "Нет подключения к интернету", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            it.isClickable = false
            Thread {
                val url = Cat.getUrl()
                mainActivity.runOnUiThread {
                    binding.editorProgressBar!!.visibility = View.VISIBLE
                    binding.editorImageView?.let { it1 ->
                        Glide.with(binding.root)
                            .load(url)
                            .into(it1)
                    }
                }
            }.start()
        }

        //Picasso calls it twice while running
        //First sets an empty image then the desired one
        binding.editorImageView?.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            var count = 0
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                if (count == 0) {
                    count++
                    return
                }
                if (count % 2 == 1) {
                    binding.editorImageView!!.visibility = View.VISIBLE
                    binding.editorProgressBar!!.visibility = View.INVISIBLE
                } else {
                    binding.editorImageView!!.visibility = View.INVISIBLE
                    binding.editorProgressBar!!.visibility = View.VISIBLE
                    binding.editorProgressBar!!.isClickable = true
                }
                count++
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}