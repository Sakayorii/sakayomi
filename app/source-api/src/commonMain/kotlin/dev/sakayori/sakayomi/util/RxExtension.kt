package dev.sakayori.sakayomi.util

import rx.Observable

expect suspend fun <T> Observable<T>.awaitSingle(): T
