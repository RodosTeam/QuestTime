package dev.rodosteam.questtime.quest.model

/**
 * Состояние прохождения квеста.
 */
class Walkthrough private constructor(
    // квест
    private val content: QuestContent,
    // история прохождения, чтобы возвращаться назад
    private val history: List<QuestContent.Page.Id>
) {
    /**
     * Начинает прохождение данного квеста.
     */
    constructor(content: QuestContent) : this(content, listOf(content.startingId))

    /**
     * Current page
     */
    val page get() = content.pages.getValue(history.last())

    /**
     * Шаг назад. TODO: Не дать удалить из history.
     *
     * @return возвращает предыдущее состояние
     */
    // TODO: Не дать удалить из history
    fun goBack() = Walkthrough(content, history.dropLast(1))

    /**
     * Шаг вперед.
     *
     * @param index Индекс выбранного варианта на странице
     * @return возвращает состояние после сделанного выбора
     */
    fun choose(index: Int) = Walkthrough(
        content,
        history + page.choices[index].nextPageId
    )
}