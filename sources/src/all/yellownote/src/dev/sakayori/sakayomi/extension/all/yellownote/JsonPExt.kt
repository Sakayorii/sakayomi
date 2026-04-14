package dev.sakayori.sakayomi.extension.all.yellownote

import org.jsoup.nodes.Element

fun Element.parentText(): String? = parent()?.text()
