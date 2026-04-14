package dev.sakayori.sakayomi.extension.en.bookwalker

interface BookWalkerPreferences {
    val showLibraryInPopular: Boolean
    val shouldValidateLogin: Boolean
    val imageQuality: ImageQualityPref
    val filterChapters: FilterChaptersPref
    val attemptToReadPreviews: Boolean
    val useEarliestThumbnail: Boolean
    val excludeCategoryFilters: Regex
    val excludeGenreFilters: Regex
}
