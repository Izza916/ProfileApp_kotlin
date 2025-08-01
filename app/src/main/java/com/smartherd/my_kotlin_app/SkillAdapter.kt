package com.smartherd.my_kotlin_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SkillAdapter(
    private val skills: List<Skill>
) : RecyclerView.Adapter<SkillAdapter.SkillViewHolder>() {

    inner class SkillViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvSkillName)
        val tvPercent: TextView = view.findViewById(R.id.tvSkillPercent)
        val pb: ProgressBar = view.findViewById(R.id.pbSkill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_skill, parent, false)
        return SkillViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val skill = skills[position]
        holder.tvName.text = skill.name
        holder.tvPercent.text = "${skill.percent}%"
        holder.pb.max = 100
        holder.pb.progress = 0
        holder.pb.animate()
            .setDuration(800)
            .setInterpolator(DecelerateInterpolator())
            .alpha(1f)
            .withStartAction { holder.pb.progress = 0 }
            .start()
        holder.pb.post {
            holder.pb.setProgress(skill.percent, true)
        }
    }

    override fun getItemCount() = skills.size
}
