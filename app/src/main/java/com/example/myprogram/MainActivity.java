package com.example.myprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

        public class MainActivity extends AppCompatActivity implements View.OnClickListener {

            ImageView plus;

            BottomNavigationView bottomNavigation;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                bottomNavigation = findViewById(R.id.bottom_nav);

                plus = findViewById(R.id.plus);
                ImageView plus = findViewById(R.id.plus);
                plus.setOnClickListener(this);
            }

                @Override
                public void onClick (View v){
                    switch (v.getId()) {
                        case R.id.plus:

                            break;

                    }

                }
            }
