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