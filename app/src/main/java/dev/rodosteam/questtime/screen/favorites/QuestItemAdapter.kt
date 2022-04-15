package dev.rodosteam.questtime.screen.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.DOWNLOADED_KEY
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.QUEST_KEY
import dev.rodosteam.questtime.screen.questContent.QuestContentFragment.Companion.REPO_LIBRARY_CONTENT
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.REPO_LIBRARY_PREVIEW

class QuestItemAdapter(
    private val quests: List<QuestMeta>,
    private val navController: NavController
) :
    RecyclerView.Adapter<QuestItemAdapter.QuestItemHolder>() {

    class QuestItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var titleTv: TextView = view.findViewById(R.id.fragment_library_item__title)
        private var descriptionTv: TextView = view.findViewById(R.id.fragment_library_item__description)
        private var imageView: ImageView = view.findViewById(R.id.fragment_library_item__image)
        var playButton: FloatingActionButton =
            view.findViewById(R.id.fragment_library_item__playButton)

        fun bind(item: QuestMeta) {
            titleTv.text = item.title
            descriptionTv.text = item.description
            imageView.setImageResource(item.iconId)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): QuestItemHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_favorites_item, viewGroup, false)
        return QuestItemHolder(view)
    }

    override fun onBindViewHolder(holder: QuestItemHolder, position: Int) {
        holder.bind(quests[position])
        holder.playButton.setOnClickListener {
            navController.navigate(
                R.id.action_navigation_favorites_to_navigation_quest_content,
                bundleOf(
                    QUEST_KEY to quests[position].id,
                    REPO_LIBRARY_CONTENT to false
                )
            )
        }
        holder.view.setOnClickListener {
            navController.navigate(
                R.id.action_navigation_favorites_to_navigation_quest_preview,
                bundleOf(
                    QUEST_KEY to quests[position].id,
                    DOWNLOADED_KEY to true,
                    REPO_LIBRARY_PREVIEW to false
                )
            )
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = quests.size

}
