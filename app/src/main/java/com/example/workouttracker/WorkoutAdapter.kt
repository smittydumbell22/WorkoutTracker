package com.example.workouttracker

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_workout.view.*

class WorkoutAdapter(

    private val workouts: MutableList<Workout>
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        return WorkoutViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_workout,
                parent,
                false
            )
        )
    }

    fun addWorkout(workout : Workout){
        workouts.add(workout)
        notifyItemInserted(workouts.size -1)
    }

    fun deleteFinishedWorkouts(){
        workouts.removeAll{workout ->
            workout.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvWorkoutTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvWorkoutTitle.paintFlags = tvWorkoutTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvWorkoutTitle.paintFlags = tvWorkoutTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val curWorkout = workouts[position]
        holder.itemView.apply {
            tvWorkoutTitle.text = curWorkout.title
            cbComplete.isChecked = curWorkout.isChecked
            toggleStrikeThrough(tvWorkoutTitle, curWorkout.isChecked)
            cbComplete.setOnCheckedChangeListener{_, isChecked ->
                toggleStrikeThrough(tvWorkoutTitle, isChecked)
                curWorkout.isChecked = !curWorkout.isChecked
            }

        }

    }

    override fun getItemCount(): Int {
        return workouts.size

    }
}