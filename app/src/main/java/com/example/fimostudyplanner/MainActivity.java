package com.example.fimostudyplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(this);
        bottomNavView.setSelectedItemId(R.id.homeMenu);

        Intent i = getIntent();
        String selectedFragment;
        try {
            selectedFragment = Objects.requireNonNull(i.getStringExtra("GoTo")).toLowerCase();
        } catch (NullPointerException e) {
            Log.e("NullPointerException",
                    "selectedFragment is null. proceeding to homeFragment.");
            selectedFragment = "homefragment";
        }

        Log.d("info", "onCreate: selectedFragment: " + selectedFragment);
        switch (selectedFragment) {
            case "tasksfragment":
                bottomNavView.setSelectedItemId(R.id.tasksMenu);
                startFragment(tasksFragment);
                break;
            case "flashcardsfragment":
                bottomNavView.setSelectedItemId(R.id.flashcardsMenu);
                startFragment(flashcardsFragment);
                break;
            case "pomodorofragment":
                bottomNavView.setSelectedItemId(R.id.pomodoroMenu);
                startFragment(pomodoroFragment);
                break;
            case "profilefragment":
                bottomNavView.setSelectedItemId(R.id.profileMenu);
                startFragment(profileFragment);
                break;
            default:
                bottomNavView.setSelectedItemId(R.id.homeMenu);
                startFragment(homeFragment);
                break;
        }
    }
    HomeFragment homeFragment = new HomeFragment();
    TasksFragment tasksFragment = new TasksFragment();
    FlashcardsFragment flashcardsFragment = new FlashcardsFragment();
    PomodoroFragment pomodoroFragment = new PomodoroFragment();
    ProfileFragment profileFragment = new ProfileFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.homeMenu) {
            return startFragment(homeFragment);
        } else if (item.getItemId() == R.id.tasksMenu) {
            return startFragment(tasksFragment);
        } else if (item.getItemId() == R.id.flashcardsMenu) {
            return startFragment(flashcardsFragment);
        } else if (item.getItemId() == R.id.pomodoroMenu) {
            return startFragment(pomodoroFragment);
        } else if (item.getItemId() == R.id.profileMenu) {
            return startFragment(profileFragment);
        }
        return false;
    }

    public boolean startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .commit();
        return true;
    }
}