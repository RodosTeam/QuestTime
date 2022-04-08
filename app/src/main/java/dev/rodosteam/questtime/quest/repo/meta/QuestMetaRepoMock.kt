package dev.rodosteam.questtime.quest.repo.meta

class QuestMetaRepoMock : QuestMetaRepoBase() {
    init {
        val questsInfo = QuestsMockInfo()
        repeat(10) {
            quests[it] = questsInfo.generateRandomQuest(it)
        }
    }
}
