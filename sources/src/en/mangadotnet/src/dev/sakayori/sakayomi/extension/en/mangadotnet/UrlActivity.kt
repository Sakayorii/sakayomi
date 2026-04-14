package dev.sakayori.sakayomi.extension.en.mangadotnet

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import kotlin.system.exitProcess

class UrlActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainIntent = Intent().apply {
            action = "dev.sakayori.sakayomi.SEARCH"
            putExtra("query", intent.data.toString())
            putExtra("filter", packageName)
        }

        try {
            startActivity(mainIntent)
        } catch (e: ActivityNotFoundException) {
            Log.e("Mangadotnet", "Unable to launch activity", e)
        }

        finish()
        exitProcess(0)
    }
}
