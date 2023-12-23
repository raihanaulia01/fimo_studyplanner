package com.example.fimostudyplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(this);
        bottomNavView.setSelectedItemId(R.id.homeMenu);
    }
    HomeFragment homeFragment = new HomeFragment();
    TasksFragment tasksFragment = new TasksFragment();
    FlashcardsFragment flashcardsFragment = new FlashcardsFragment();
    PomodoroFragment pomodoroFragment = new PomodoroFragment();
    ProfileFragment profileFragment = new ProfileFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.homeMenu) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.tasksMenu) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, tasksFragment)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.flashcardsMenu) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, flashcardsFragment)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.pomodoroMenu) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, pomodoroFragment)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.profileMenu) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, profileFragment)
                    .commit();
            return true;
        }
        return false;
    }
}