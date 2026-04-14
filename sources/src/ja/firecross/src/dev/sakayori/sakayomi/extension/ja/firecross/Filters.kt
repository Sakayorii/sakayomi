package dev.sakayori.sakayomi.extension.ja.firecross

import dev.sakayori.sakayomi.source.model.Filter

open class CheckBox(name: String, val value: String) : Filter.CheckBox(name)
class Label(name: String, value: String) : CheckBox(name, value)
class LabelFilter :
    Filter.Group<Label>(
        "Labels",
        listOf(
            Label("HJ文庫 (Novel)", "1"),
            Label("HJノベルス (Novel)", "2"),
            Label("コミックファイア (Manga)", "3"),
            Label("HJコミックス (Manga)", "4"),
        ),
    )
