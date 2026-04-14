package dev.sakayori.sakayomi.extension.pt.mangaflix

import dev.sakayori.sakayomi.source.model.Filter

class Genre(name: String, val id: String) : Filter.CheckBox(name)

class GenreFilter(genres: List<Genre>) : Filter.Group<Genre>("Gêneros", genres)
