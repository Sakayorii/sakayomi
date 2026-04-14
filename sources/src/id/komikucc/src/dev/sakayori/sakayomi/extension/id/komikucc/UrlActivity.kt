package dev.sakayori.sakayomi.extension.id.komikucc

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log

class UrlActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.data
        if (data != null) {
            val intent = Intent().apply {
                setAction("dev.sakayori.sakayomi.SEARCH")
                putExtra("query", data.toString())
                putExtra("filter", packageName)
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Log.e("Komiku.cc", "Unable to launch activity", e)
            }
        }

        finish()
        System.exit(0)
    }
}
