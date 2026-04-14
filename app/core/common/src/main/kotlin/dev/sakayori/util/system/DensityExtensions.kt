package dev.sakayori.sakayomi.util.system

import android.content.res.Resources

/**
 * Converts to px.
 */
val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
