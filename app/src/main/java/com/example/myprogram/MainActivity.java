package com.example.myprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

        public class MainActivity extends AppCompatActivity {

            ImageView setting;
            ImageView favorite;
            ImageView history;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                 setting = (ImageView) findViewById(R.id.settings);
                favorite = (ImageView) findViewById(R.id.favorite);
                history = (ImageView) findViewById(R.id.history);

//                BottomNavigationView bottomNavigationView = (BottomNavigationView)
//                        findViewById(R.id.bottom_nav);

//                bottomNavigationView.setOnNavigationItemSelectedListener(
//                        new BottomNavigationView.OnNavigationItemSelectedListener() {
//                            @Override
//                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                                switch (item.getItemId()) {
//                                    case R.id.settings:
//                                        setting.setVisibility(View.VISIBLE);
//                                        history.setVisibility(View.GONE);
//                                        favorite.setVisibility(View.GONE);
//                                        break;
//                                    case R.id.history:
//                                        setting.setVisibility(View.GONE);
//                                        history.setVisibility(View.VISIBLE);
//                                        history.setVisibility(View.GONE);
//                                        break;
//                                    case R.id.favorite:
//                                        setting.setVisibility(View.GONE);
//                                        history.setVisibility(View.GONE);
//                                        favorite.setVisibility(View.VISIBLE);
//                                        break;
//                                }
//                                return false;
//
//                            }
//                        });
            }

}