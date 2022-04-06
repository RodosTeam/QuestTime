package dev.rodosteam.questtime.quest.repo

import dev.rodosteam.questtime.quest.model.QuestItem

class QuestsInfo {
    private val minInt = 0
    private val maxInt = 100

    private val questsTitles = arrayListOf(
        "Шрек",
        "Алиса в стране чудес",
        "Скуби Ду"
    )

    private val questsDescription = arrayListOf(
        "Огр-мизантроп спасает принцессу, чтобы вернуть свое болото.\n" +
                "Жил да был в сказочном государстве большой зеленый великан по имени Шрэк. " +
                "Жил он в гордом одиночестве в лесу, на болоте, которое считал своим. " +
                "Но однажды злобный коротышка — лорд Фаркуад, правитель волшебного королевства," +
                " безжалостно согнал на Шрэково болото всех сказочных обитателей.\n" +
                "И беспечной жизни зеленого великана пришел конец. " +
                "Но лорд Фаркуад пообещал вернуть Шрэку болото, если великан добудет ему " +
                "прекрасную принцессу Фиону, которая томится в неприступной башне, " +
                "охраняемой огнедышащим драконом.",
        "Своевольная девушка попадает в мир, где все с ног на голову.\n" +
                "Алиса гонится за Белым Кроликом через весь луг и видит, как он исчезает в кроличьей норе. " +
                "Затем, неожиданно, Алиса падает туда сама, летит вниз по странному, " +
                "сказочному туннелю и приземляется в круглом зале со множеством дверей. " +
                "Выпив из бутылки с надписью «ВЫПЕЙ МЕНЯ», она уменьшается в размерах, " +
                "а откусив от пирожного с надписью «СЪЕШЬ МЕНЯ» — вырастает. " +
                "Наконец, миновав череду злоключений, Алиса попадает через одну из дверей в " +
                "чудесный фантастический мир, который его обитатели называют Подземной страной.",
        "Однажды мальчик по имени Шэгги встречает бездомного щенка, которому дает довольно странную кличку – Скуби-ду. " +
                "Это становится началом не только настоящей дружбы, но и множества удивительных событий, " +
                "в которых главные герои принимают самое активное участие. " +
                "При поддержке школьников Дафни, Фредди и Вельмы приятели создают частное детективное агентство, " +
                "мечтая помогать городским властям справляться с различными преступниками. " +
                "Вскоре наступает момент, когда отважным сыщикам приходится столкнуться с реальной опасностью: " +
                "Подлый Дик собирается выпустить на свободу Цербера, жуткую собаку-призрака, " +
                "а отчаянная команда должна любой ценой остановить приближающийся апокалипсис."
    )

    fun generateRandomQuest(id : Int) : QuestItem {
        return QuestItem(id,
            questsTitles.random(),
            questsDescription.random(),
            (minInt..maxInt).random(),
            (minInt..maxInt).random(),
            (minInt..maxInt).random(),
            1)
    }
}