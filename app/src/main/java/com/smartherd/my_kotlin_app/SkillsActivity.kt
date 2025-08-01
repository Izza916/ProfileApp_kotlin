package com.smartherd.my_kotlin_app

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class SkillsActivity : BaseActivity() {

    override val toolbarTitle: String
        get() = "Skills"

    override fun getLayoutId(): Int = R.layout.activity_skills // Integrated from the first snippet's override

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val skillList = listOf(
            Skill("Kotlin", 80),
            Skill("Java", 70),
            Skill("Android Studio", 75),
            Skill("Git", 60),
            Skill("Python", 80),
            Skill("x86 Assembly", 70),
            Skill("SQL", 75),
            Skill("Prolog", 60),
            Skill("C++", 80),
            Skill("C", 70),
            Skill("Unity", 75)
        )

        val rvSkills = findViewById<RecyclerView>(R.id.rvSkills)
        rvSkills.layoutManager = LinearLayoutManager(this)
        rvSkills.adapter = SkillAdapter(skillList)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_skills
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> startActivity(Intent(this, MainActivity::class.java))
                R.id.nav_about -> startActivity(Intent(this, AboutMeActivity::class.java))
                R.id.nav_skills -> {  }
                R.id.nav_projects -> startActivity(Intent(this, ProjectsActivity::class.java))
                R.id.nav_contact -> startActivity(Intent(this, ContactActivity::class.java))
            }
            overridePendingTransition(0, 0)
            true
        }
    }
}