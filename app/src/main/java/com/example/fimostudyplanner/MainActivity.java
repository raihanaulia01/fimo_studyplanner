package com.example.fimostudyplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
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
            return startFragment(R.id.flFragment, homeFragment);
        } else if (item.getItemId() == R.id.tasksMenu) {
            return startFragment(R.id.flFragment, tasksFragment);
        } else if (item.getItemId() == R.id.flashcardsMenu) {
            return startFragment(R.id.flFragment, flashcardsFragment);
        } else if (item.getItemId() == R.id.pomodoroMenu) {
            return startFragment(R.id.flFragment, pomodoroFragment);
        } else if (item.getItemId() == R.id.profileMenu) {
            return startFragment(R.id.flFragment, profileFragment);
        }
        return false;
    }

    public boolean startFragment(int id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .commit();
        return true;
    }
}