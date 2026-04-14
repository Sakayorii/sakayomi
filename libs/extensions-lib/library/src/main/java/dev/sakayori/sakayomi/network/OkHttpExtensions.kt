package dev.sakayori.sakayomi.network

import okhttp3.Call
import okhttp3.Response
import rx.Observable

fun Call.asObservable(): Observable<Response> = throw Exception("Stub!")

fun Call.asObservableSuccess(): Observable<Response> = throw Exception("Stub!")

suspend fun Call.await(): Response = throw Exception("Stub!")
