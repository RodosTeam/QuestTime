package dev.rodosteam.questtime.screen.external

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.DOWNLOADED_KEY
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.QUEST_KEY

class QuestItemAdapter(
    private val quests: List<QuestMeta>,
    private val navController: NavController,
) :
    RecyclerView.Adapter<QuestItemAdapter.QuestItemHolder>() {

    class QuestItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var titleTv: TextView = view.findViewById(R.id.fragment_external_item__title)
        private var descriptionTv: TextView =
            view.findViewById(R.id.fragment_external_item__description)
        private var imageView: ImageView = view.findViewById(R.id.fragment_external_item__image)
        var downloadButton: FloatingActionButton =
            view.findViewById(R.id.fragment_external_item__downloadButton)

        fun bind(item: QuestMeta) {
            titleTv.text = item.title
            descriptionTv.text = item.description
            Glide.with(view)
                .load(item.iconFilename)
                .centerCrop()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): QuestItemHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_external_item, viewGroup, false)
        return QuestItemHolder(view)
    }

    override fun onBindViewHolder(holder: QuestItemHolder, position: Int) {
        holder.bind(quests[position])

        holder.downloadButton.setOnClickListener {
            //TODO
            Toast.makeText(
                navController.context,
                "Мы обязательно все скачаем! TODO", Toast.LENGTH_SHORT
            ).show()
        }
        holder.view.setOnClickListener {
            navController.navigate(
                R.id.action_navigation_external_to_navigation_quest_preview,
                bundleOf(
                    QUEST_KEY to quests[position].id,
                    DOWNLOADED_KEY to true
                )
            )
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = quests.size

}
