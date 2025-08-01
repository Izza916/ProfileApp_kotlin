package com.smartherd.my_kotlin_app

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager // Deprecated, consider using androidx.preference.PreferenceManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class ContactActivity : BaseActivity() {

    override val toolbarTitle: String
        get() = "Contact"

    override fun getLayoutId(): Int = R.layout.activity_contact

    private lateinit var mapMain: MapView
    private lateinit var mapCity: MapView

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().load(
            applicationContext,
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )

        ensurePermissions()

        val btnEmail = findViewById<MaterialButton>(R.id.btnEmail)
        val btnLinkedIn = findViewById<MaterialButton>(R.id.btnLinkedIn)
        val btnGitHub = findViewById<MaterialButton>(R.id.btnGitHub)
        val btnAppointment = findViewById<MaterialButton>(R.id.btnAppointment)
        val btnMeeting = findViewById<MaterialButton>(R.id.btnMeeting)

        btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:k224120@nu.edu.pk"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry from your Android App")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "No email app found.", Toast.LENGTH_SHORT).show()
            }
        }

        btnLinkedIn.setOnClickListener {
            openUrl("https://www.linkedin.com/in/izza-farhat-2a81002aa/")
        }

        btnGitHub.setOnClickListener {
            openUrl("https://github.com/Izza916")
        }

        btnAppointment.setOnClickListener {
            openUrl("https://calendar.google.com/calendar/u/0/selfsched?sstoken=YOUR_TOKEN")
        }

        btnMeeting.setOnClickListener {
            openUrl("https://meet.google.com/new")
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_contact
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> startActivity(Intent(this, MainActivity::class.java))
                R.id.nav_about -> startActivity(Intent(this, AboutMeActivity::class.java))
                R.id.nav_skills -> startActivity(Intent(this, SkillsActivity::class.java))
                R.id.nav_projects -> startActivity(Intent(this, ProjectsActivity::class.java))
                R.id.nav_contact -> {  }
            }
            overridePendingTransition(0, 0) // Disable default transition
            true
        }

        mapMain = findViewById(R.id.mapMainCampus)
        mapCity = findViewById(R.id.mapCityCampus)

        listOf(
            mapMain to GeoPoint(24.8607, 67.0011),
            mapCity to GeoPoint(24.8600, 67.0250)
        ).forEach { (map, point) ->
            map.setMultiTouchControls(true)
            map.controller.setZoom(15.0)
            map.controller.setCenter(point)
            map.overlays.add(Marker(map).apply {
                position = point
                title = if (map === mapMain) "Main Campus" else "City Campus"
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            })
        }
    }

    private fun openUrl(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No browser found.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun ensurePermissions() {
        val perms = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
        )
        val missing = perms.filter {
            ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missing.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, missing.toTypedArray(), PERMISSION_REQUEST_CODE)
        }
    }

    override fun onResume() {
        super.onResume()
        mapMain.onResume()
        mapCity.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapMain.onPause()
        mapCity.onPause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE &&
            grantResults.any { it != PackageManager.PERMISSION_GRANTED }) {
            Toast.makeText(
                this,
                "Storage permission is required to cache map tiles.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}