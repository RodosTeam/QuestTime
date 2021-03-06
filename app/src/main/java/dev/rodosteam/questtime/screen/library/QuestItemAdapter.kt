package dev.rodosteam.questtime.screen.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.database.Quest
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.DOWNLOADED_KEY
import dev.rodosteam.questtime.screen.preview.QuestPreviewFragment.Companion.QUEST_KEY

class QuestItemAdapter(
    private val quests: List<Quest>,
    private val navController: NavController,
) :
    RecyclerView.Adapter<QuestItemAdapter.QuestItemHolder>() {

    class QuestItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var titleTv: TextView = view.findViewById(R.id.fragment_list_item__title)
        private var descriptionTv: TextView = view.findViewById(R.id.fragment_list_item__description)
        private var imageView: ImageView = view.findViewById(R.id.fragment_list_item__image)
        var playButton: FloatingActionButton =
            view.findViewById(R.id.fragment_list_item__button)

        fun bind(item: Quest) {
            titleTv.text = item.title
            descriptionTv.text = item.description
            Glide.with(view)
                .load(item.iconUrl)
                .centerCrop()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): QuestItemHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_list_item, viewGroup, false)
        return QuestItemHolder(view)
    }

    override fun onBindViewHolder(holder: QuestItemHolder, position: Int) {
        holder.bind(quests[position])
        holder.playButton.setImageResource(R.drawable.ic_play_black_24dp)
        holder.playButton.setOnClickListener {
            navController.navigate(
                R.id.action_navigation_library_to_questContentFragment,
                bundleOf(QUEST_KEY to quests[position].id)
            )
        }
        holder.view.setOnClickListener {
            navController.navigate(
                R.id.action_navigation_library_to_questPreviewFragment,
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
