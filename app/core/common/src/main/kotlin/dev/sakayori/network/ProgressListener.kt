package dev.sakayori.sakayomi.network

interface ProgressListener {
    fun update(bytesRead: Long, contentLength: Long, done: Boolean)
}
