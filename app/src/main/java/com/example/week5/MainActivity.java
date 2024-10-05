package com.example.week5;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recentActivitiesRecyclerView;
    private FloatingActionButton fab;
    private FrameLayout newRecyclerViewContainer;
    private boolean isNewRecyclerViewAdded = false;  // Track if RecyclerView is added
    private ImageView settingsIcon;  // Declare the settings icon

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize views
        progressBar = findViewById(R.id.progressBar);
        recentActivitiesRecyclerView = findViewById(R.id.recentActivitiesRecyclerView);
        fab = findViewById(R.id.fab);
        newRecyclerViewContainer = findViewById(R.id.newRecyclerViewContainer);
        settingsIcon = findViewById(R.id.settings_icon);  // Find the settings icon

        // Set click listener for the settings icon to show a popup menu


        findViewById(R.id.idea_card).setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Personal Card Clicked", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.new_card).setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "New Card Clicked", Toast.LENGTH_SHORT).show()
        );

        // Load recent activities into RecyclerView
        loadRecentActivities();

        // Set Floating Action Button (FAB) behavior to add another RecyclerView
        fab.setOnClickListener(v -> {
            if (!isNewRecyclerViewAdded) {
                addNewRecyclerView();
                isNewRecyclerViewAdded = true;  // Mark as added
            } else {
                Toast.makeText(MainActivity.this, "RecyclerView already added!", Toast.LENGTH_SHORT).show();
            }
        });

        // Simulate progress update
        updateTaskProgress(60);  // Can change dynamically based on user actions
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            openSettings();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
}

    private void loadRecentActivities() {
        List<RecentActivity> recentActivities = new ArrayList<>();
        recentActivities.add(new RecentActivity("Completed Work Task", "1 hour ago"));
        recentActivities.add(new RecentActivity("Completed Fitness Task", "3 hours ago"));

        ActivityAdapter adapter = new ActivityAdapter(recentActivities);
        recentActivitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recentActivitiesRecyclerView.setAdapter(adapter);
    }

    private void addNewRecyclerView() {
        // Create a new RecyclerView programmatically
        RecyclerView newRecyclerView = new RecyclerView(this);
        newRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the data for the new RecyclerView
        List<RecentActivity> newActivities = new ArrayList<>();
        newActivities.add(new RecentActivity("New Task 1", "5 minutes ago"));
        newActivities.add(new RecentActivity("New Task 2", "10 minutes ago"));

        ActivityAdapter newAdapter = new ActivityAdapter(newActivities);
        newRecyclerView.setAdapter(newAdapter);

        // Add the RecyclerView to the container
        newRecyclerViewContainer.addView(newRecyclerView);
        Toast.makeText(MainActivity.this, "New RecyclerView added!", Toast.LENGTH_SHORT).show();
    }

    private void updateTaskProgress(int progress) {
        progressBar.setProgress(progress);
    }
}
