package dev.rodosteam.questtime.screen.library

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
import dev.rodosteam.questtime.utils.InternalStorage

class QuestItemAdapter(
    private val quests: List<QuestMeta>,
    private val navController: NavController,
    private val intStorage: InternalStorage
) :
    RecyclerView.Adapter<QuestItemAdapter.QuestItemHolder>() {

    class QuestItemHolder(private val intStorage: InternalStorage, val view: View) : RecyclerView.ViewHolder(view) {
        private var titleTv: TextView = view.findViewById(R.id.fragment_library_item__title)
        private var descriptionTv: TextView = view.findViewById(R.id.fragment_library_item__description)
        private var imageView: ImageView = view.findViewById(R.id.fragment_library_item__image)
        var playButton: FloatingActionButton =
            view.findViewById(R.id.fragment_library_item__playButton)

        fun bind(item: QuestMeta) {
            titleTv.text = item.title
            descriptionTv.text = item.description
            imageView.setImageBitmap(intStorage.getBitmap(item.iconFilename))
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): QuestItemHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_library_item, viewGroup, false)
        return QuestItemHolder(intStorage, view)
    }

    override fun onBindViewHolder(holder: QuestItemHolder, position: Int) {
        holder.bind(quests[position])
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
