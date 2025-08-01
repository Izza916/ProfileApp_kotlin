package com.smartherd.my_kotlin_app

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutMeActivity : BaseActivity() {

    override val toolbarTitle: String
        get() = "About Me"

    override fun getLayoutId(): Int = R.layout.activity_about_me

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in).apply {
            duration = 5000
        }

        findViewById<CardView>(R.id.servicesCard)?.startAnimation(fadeIn)
        findViewById<CardView>(R.id.officeHoursCard)?.startAnimation(fadeIn)
        findViewById<CardView>(R.id.educationCard)?.startAnimation(fadeIn)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_about
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> startActivity(Intent(this, MainActivity::class.java))
                R.id.nav_about -> {  }
                R.id.nav_skills -> startActivity(Intent(this, SkillsActivity::class.java))
                R.id.nav_projects -> startActivity(Intent(this, ProjectsActivity::class.java))
                R.id.nav_contact -> startActivity(Intent(this, ContactActivity::class.java))
            }
            overridePendingTransition(0, 0)
            true
        }
    }
}