package com.smartherd.my_kotlin_app.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.smartherd.my_kotlin_app.R

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        val map1: WebView = view.findViewById(R.id.mapWebView1)
        val map2: WebView = view.findViewById(R.id.mapWebView2)

        map1.settings.javaScriptEnabled = true
        map2.settings.javaScriptEnabled = true

        val iframe1 = """
            <html>
            <body style="margin:0;padding:0;">
            <iframe
                src="https://embed.waze.com/iframe?zoom=16&lat=24.856899&lon=67.264684&ct=livemap"
                width="100%" height="100%" frameborder="0" allowfullscreen>
            </iframe>
            </body>
            </html>
        """.trimIndent()

        val iframe2 = """
            <html>
            <body style="margin:0;padding:0;">
            <iframe
                src="https://embed.waze.com/iframe?zoom=16&lat=24.859826&lon=67.069963&ct=livemap"
                width="100%" height="100%" frameborder="0" allowfullscreen>
            </iframe>
            </body>
            </html>
        """.trimIndent()

        map1.loadDataWithBaseURL(null, iframe1, "text/html", "UTF-8", null)
        map2.loadDataWithBaseURL(null, iframe2, "text/html", "UTF-8", null)

        return view
    }
}