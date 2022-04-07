package dev.rodosteam.questtime.quest.model

class QuestContent private constructor(
    val pages: Map<Page.Id, Page>, val startingId: Page.Id
) {
    constructor(pages: Iterable<Page>, startingId: Page.Id) : this(
        pages.associateBy { it.id },
        startingId
    )

    data class Page(
        val id: Id,
        val displayText: String,
        val choices: List<Choice>
    ) {
        @JvmInline
        value class Id(private val id: Long)

        data class Choice(
            val nextPageId: Id,
            val displayText: String
        )
    }
}