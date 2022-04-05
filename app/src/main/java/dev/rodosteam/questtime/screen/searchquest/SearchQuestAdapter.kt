package dev.rodosteam.questtime.screen.searchquest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.rodosteam.questtime.quest.model.QuestItem
import dev.rodosteam.questtime.screen.searchquest.row.SearchQuestRowMvpView
import dev.rodosteam.questtime.screen.searchquest.SearchQuestAdapter.QuestItemViewHolder
import dev.rodosteam.questtime.screen.searchquest.row.SearchQuestRowMvpViewImpl
import java.util.ArrayList

class SearchQuestAdapter(
    private val layoutInflater: LayoutInflater,
    private val listener: SearchQuestRowMvpView.Listener
) : RecyclerView.Adapter<QuestItemViewHolder>(),
    SearchQuestRowMvpView.Listener {

    class QuestItemViewHolder(val searchQuestRowMvpView: SearchQuestRowMvpView) :
        RecyclerView.ViewHolder(searchQuestRowMvpView.rootView)

    private val items: MutableList<QuestItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestItemViewHolder {
        val searchQuestRowMvpView: SearchQuestRowMvpView =
            SearchQuestRowMvpViewImpl(layoutInflater, parent, this)
        return QuestItemViewHolder(searchQuestRowMvpView)
    }

    override fun onBindViewHolder(holder: QuestItemViewHolder, position: Int) {
        val questItem = items[position]
        holder.searchQuestRowMvpView.bindData(questItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onQuestItemClicked(questItemId: Int) {
        listener.onQuestItemClicked(questItemId)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindData(blogItems: List<QuestItem>) {
        items.clear()
        items.addAll(blogItems)
        notifyDataSetChanged()
    }

}