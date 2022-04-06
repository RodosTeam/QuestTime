package dev.rodosteam.questtime.screen.common.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.quest.model.QuestItem

class QuestItemAdapter(
    private val quests: List<QuestItem>,
    private val navController: NavController
    ) :
    RecyclerView.Adapter<QuestItemAdapter.QuestItemHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class QuestItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var titleTv: TextView = view.findViewById(R.id.fragment_library_item__title)
        private var descriptionTv: TextView =
            view.findViewById(R.id.fragment_library_item__description)

        fun bind(item: QuestItem) {
            titleTv.text = item.title
            descriptionTv.text = item.description
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): QuestItemHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_library_item, viewGroup, false)
        return QuestItemHolder(view)
    }

    override fun onBindViewHolder(holder: QuestItemHolder, position: Int) {
        holder.bind(quests[position])
        holder.view.setOnClickListener {
            navController.navigate(R.id.action_navigation_library_to_questPreviewFragment,
            bundleOf("quest" to quests[position].id))
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = quests.size

}
