package dev.sakayori.sakayomi.extension.ja.dokiraw

import dev.sakayori.sakayomi.source.model.Filter

class GenreFilter :
    Filter.Select<String>(
        "Genre",
        arrayOf(
            "All", // 0
            "フルカラー",
            "Ecchi",
            "エロい",
            "コメディ",
            "ロマンス",
            "アクション",
            "スポーツ",
            "ファンタジー",
            "SF",
            "異世界",
            "心理的",
            "青年",
        ),
    )
