package dev.rodosteam.questtime.quest.repo.meta

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.rodosteam.questtime.api.dto.QuestMetaDto
import dev.rodosteam.questtime.api.dto.getMeta

class QuestMetaFireBase : QuestMetaRepoBase() {

    val fireMetaReference = FirebaseDatabase.getInstance().getReference("questsMeta")

    init {
        fireMetaReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {

                for (snapshot in p0.children) {
                    val questMetaDto = snapshot.getValue(QuestMetaDto::class.java)
                    assert(questMetaDto != null)
                    val meta = getMeta(questMetaDto!!)
                    quests[meta.id] = meta
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}