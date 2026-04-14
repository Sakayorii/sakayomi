package dev.sakayori.sakayomi.extension.es.lectorjpg

import dev.sakayori.sakayomi.source.model.Filter

class Genre(title: String, val key: String) : Filter.CheckBox(title)
class GenreFilter(title: String, genres: List<Genre>) : Filter.Group<Genre>(title, genres)
