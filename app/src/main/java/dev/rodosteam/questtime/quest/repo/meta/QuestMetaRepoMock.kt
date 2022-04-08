package dev.rodosteam.questtime.quest.repo.meta

class QuestMetaRepoMock : QuestMetaRepoBase() {
    init {
        val questsInfo = QuestsInfo()
        repeat(10) {
            quests[it] = questsInfo.generateRandomQuest(it)
        }
    }
}
