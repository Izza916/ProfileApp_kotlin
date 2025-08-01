package com.smartherd.my_kotlin_app

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProjectsActivity : BaseActivity() {

    override val toolbarTitle: String
        get() = "Projects"

    override fun getLayoutId(): Int = R.layout.activity_projects

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<RecyclerView>(R.id.projectsRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@ProjectsActivity)
            adapter = ProjectAdapter(
                listOf(
                    Project("Dr Bot", "Developed a command‑line application in C for patient registration, appointment scheduling, and diagnostics.  \n" + "Implemented file‑based data storage with custom structs and dynamic memory management  \n" + "Added validation checks and user‑friendly menus for staff workflows  \n"),
                    Project("Profile App", "Kotlin Android app showcasing my portfolio"),
                    Project("API-Based Hospital Management System ", "Built a hospital management system using Java and Maven with modular class design (Patient, Doctor, Drug, etc.).\n" + "Integrated OpenFDA REST API to fetch real-time drug data.\n" + "Applied OOP principles: inheritance, encapsulation, abstraction.\n" + "Included patient registration, doctor assignment, and prescription features.\n"),
                    Project("Boatrish", "Ship battle game with unity "),
                    Project("IP calculator for subnet design", "Calculations made for IPv4/IPv6 used in subnet designing"),
                    Project("Cognitive insights in T20 Cricket", "Built a semantic‑analysis pipeline in Python to parse and tag ball‑by‑ball commentary  \n" + "Designed an ontology for match events and populated a knowledge graph using RDF  \n" + "Enabled natural‑language querying of player and team statistics  \n"),
                    Project("Text‑Based Checkers Game", "Programmed full game logic, move validation, and AI opponent using Minimax algorithm\n" + "Implemented a text‑based board renderer and command parser for two‑player and single‑player modes\n" + "Profiled and optimized recursive search to improve move‑generation speed by 40%\n"),
                    Project("Wine Dataset Exploration", " Analyzed a public wine quality dataset: performed EDA, feature engineering, and built a regression model in scikit‑learn  \n" + " Deployed an interactive Streamlit app to visualize data distributions and model predictions    \n"),
                    Project("Pose2caption", "Prayer positions captioned for correctness")
                )
            )
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_projects // Set the current item as selected
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> startActivity(Intent(this, MainActivity::class.java))
                R.id.nav_about -> startActivity(Intent(this, AboutMeActivity::class.java))
                R.id.nav_skills -> startActivity(Intent(this, SkillsActivity::class.java))
                R.id.nav_projects -> {  }
                R.id.nav_contact -> startActivity(Intent(this, ContactActivity::class.java))
            }
            overridePendingTransition(0, 0)
            true
        }
    }
}