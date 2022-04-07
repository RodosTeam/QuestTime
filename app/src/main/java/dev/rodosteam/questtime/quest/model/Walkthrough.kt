package dev.rodosteam.questtime.quest.model

class Walkthrough private constructor(
    private val content: QuestContent,
    private val history: List<QuestContent.Page.Id>
) {
    constructor(content: QuestContent) : this(content, listOf(content.startingId))

    val page get() = content.pages.getValue(history.last())

    fun goBack() = Walkthrough(content, history.dropLast(1))

    fun choose(index: Int) = Walkthrough(
        content,
        history + page.choices[index].targetId
    )
}