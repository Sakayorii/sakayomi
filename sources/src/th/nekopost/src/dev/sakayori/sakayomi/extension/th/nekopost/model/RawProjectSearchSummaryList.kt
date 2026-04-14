package dev.sakayori.sakayomi.extension.th.nekopost.model

import kotlinx.serialization.Serializable

@Serializable
data class RawProjectSearchSummaryList(
    val listProject: List<RawProjectSearchSummary>? = null,
)
