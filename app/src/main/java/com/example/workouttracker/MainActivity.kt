package com.example.workouttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var workoutAdapter: WorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        workoutAdapter = WorkoutAdapter(mutableListOf())
        rvWorkoutTracker.adapter = workoutAdapter
        rvWorkoutTracker.layoutManager = LinearLayoutManager(this)

        btnAddWorkout.setOnClickListener{
          val workoutTitle = etWorkoutTitle.text.toString()
            if(workoutTitle.isNotEmpty()){
                val workout = Workout(workoutTitle)
                workoutAdapter.addWorkout(workout)
               etWorkoutTitle.text.clear()
            }
        }
        btnDeleteFinishedWorkouts.setOnClickListener{
            workoutAdapter.deleteFinishedWorkouts()
        }
    }
}