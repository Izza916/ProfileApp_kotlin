package com.smartherd.my_kotlin_app

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import androidx.core.animation.doOnEnd

class MainActivity : BaseActivity() {

    override val toolbarTitle: String
        get() = "Home"

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)

        splash.setOnExitAnimationListener { splashScreenView ->
            ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.ALPHA,
                1f, 0f
            ).apply {
                interpolator = DecelerateInterpolator()
                duration = 300L
                doOnEnd { splashScreenView.remove() }
                start()
            }
        }

        val btnAboutMe: MaterialButton = findViewById(R.id.btnAboutMe)
        val btnSkills: MaterialButton = findViewById(R.id.btnSkills)
        val btnProjects: MaterialButton = findViewById(R.id.btnProjects)
        val btnContact: MaterialButton = findViewById(R.id.btnContact)

        btnAboutMe.setOnClickListener {
            startActivity(Intent(this, AboutMeActivity::class.java))
        }
        btnSkills.setOnClickListener {
            startActivity(Intent(this, SkillsActivity::class.java))
        }
        btnProjects.setOnClickListener {
            startActivity(Intent(this, ProjectsActivity::class.java))
        }
        btnContact.setOnClickListener {
            startActivity(Intent(this, ContactActivity::class.java))
        }

        val container = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main_content)
        ViewCompat.setOnApplyWindowInsetsListener(container) { v, insets ->
            val sys = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(sys.left, sys.top, sys.right, sys.bottom)
            insets
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home    -> {  }
                R.id.nav_about   -> startActivity(Intent(this, AboutMeActivity::class.java))
                R.id.nav_skills  -> startActivity(Intent(this, SkillsActivity::class.java))
                R.id.nav_projects-> startActivity(Intent(this, ProjectsActivity::class.java))
                R.id.nav_contact -> startActivity(Intent(this, ContactActivity::class.java))
            }
            overridePendingTransition(0, 0)
            true
        }
    }
}