package dev.rodosteam.questtime.utils

enum class Languages(val label: String, val code: String) {
    ENGLISH("English","en"),
    RUSSIAN("Русский","ru"),
    GERMAN("Deutsch","de");

    companion object {
        val DEFAULT = ENGLISH
        val OPTIONS = values().map {
            it.label
        }.toTypedArray()

        fun findByCode(code: String) : Languages {
            return values().find {
                it.code == code
            } ?: DEFAULT
        }
    }
}
