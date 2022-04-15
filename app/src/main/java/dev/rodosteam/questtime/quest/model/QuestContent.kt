package dev.rodosteam.questtime.quest.model

/**
 * Содержимое квеста - суть ориентированный граф, заданный списком смежности
 */
class QuestContent private constructor(val pages: Map<Page.Id, Page>, val startingId: Page.Id, val name: String) {
    // Создание квеста из Iterable страниц
    constructor(pages: Iterable<Page>, startingId: Page.Id, name: String) : this(
        pages.associateBy { it.id },
        startingId,
        name
    )

    /**
     * Страница -- вершина графа переходов
     *             + список ребер (следующих страниц, заданных своими [Page.Id]).
     * Порядок элементов в [choices] важен, т.к. по индексу выбирается следующая страница: [Walkthrough.choose],
     */
    data class Page(
        val id: Id,
        val displayText: String,
        val choices: List<Choice>
    ) {
        @JvmInline
        value class Id(private val id: Long) {
            override fun toString(): String {
                return id.toString()
            }
        }

        data class Choice(
            val nextPageId: Id,
            val displayText: String
        )
    }
}