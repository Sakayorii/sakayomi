package dev.sakayori.sakayomi.ui.reader.viewer

import dev.sakayori.sakayomi.data.database.models.toDomainChapter
import dev.sakayori.sakayomi.ui.reader.model.ReaderChapter
import tachiyomi.domain.chapter.service.calculateChapterGap as domainCalculateChapterGap

fun calculateChapterGap(higherReaderChapter: ReaderChapter?, lowerReaderChapter: ReaderChapter?): Int {
    return domainCalculateChapterGap(
        higherReaderChapter?.chapter?.toDomainChapter(),
        lowerReaderChapter?.chapter?.toDomainChapter(),
    )
}
