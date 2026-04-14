package dev.sakayori.sakayomi.extension.pt.egotoons

import dev.sakayori.sakayomi.source.model.Filter

class GenreFilter : Filter.Group<CheckBoxFilter>("Gêneros", genresList.map { CheckBoxFilter(it) })

class TagFilter : Filter.Group<CheckBoxFilter>("Tags", tagsList.map { CheckBoxFilter(it) })

class CheckBoxFilter(name: String) : Filter.CheckBox(name)

val genresList = listOf(
    "Ação", "Aventura", "Drama", "Escolar", "Fantasia", "Ficção", "Harem", "Reencarnação", "Romance",
)

val tagsList = listOf(
    "Ação", "Artes Marciais", "Aventura", "Comédia", "Drama", "Escolar", "Fantasia", "Ficção",
    "Harem", "Magia", "Murim", "Reencarnação", "Romance", "shounen", "Sobrenatural",
)
