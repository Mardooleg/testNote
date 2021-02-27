package com.example.myprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

        public class MainActivity extends AppCompatActivity implements View.OnClickListener {

            ImageView plus;
            ImageView searchmove;
            ImageView searchmove2;
            ImageView elipse2;
EditText edtext;
ImageView elipse;
            ImageView elipse1;
            ImageView history;
            ImageView home;
            ImageView calendar;
            TextView toptitle;
ImageView search;
int loc = 0;
            BottomNavigationView bottomNavigation;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                bottomNavigation = findViewById(R.id.bottom_nav);
                edtext = findViewById(R.id.searchwrite);


                plus = findViewById(R.id.plus);
                ImageView plus = findViewById(R.id.plus);
                plus.setOnClickListener(this);

                search = findViewById(R.id.search);
                ImageView search = findViewById(R.id.search);
                search.setOnClickListener(this);

                searchmove = findViewById(R.id.searchmove);
                ImageView searchmove = findViewById(R.id.searchmove);
                searchmove.setOnClickListener(this);


                elipse = findViewById(R.id.elipse);
                ImageView elipse = findViewById(R.id.elipse);
                elipse.setOnClickListener(this);

                elipse1 = findViewById(R.id.elipse1);
                ImageView elipse1 = findViewById(R.id.elipse1);
                elipse1.setOnClickListener(this);

                elipse2 = findViewById(R.id.elipse2);
                ImageView elipse2 = findViewById(R.id.elipse2);
                elipse2.setOnClickListener(this);

                toptitle =  findViewById(R.id.toptitle);

                BottomNavigationView bottomNavigationView = (BottomNavigationView)
                        findViewById(R.id.bottom_nav);

//                bottomNavigationView.setOnNavigationItemSelectedListener(
//                        new BottomNavigationView.OnNavigationItemSelectedListener() {
//                            @Override
//                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                                switch (item.getItemId()) {
//                                    case R.id.favorite:
//                                        plus.setVisibility(View.GONE);
//                                        toptitle.setVisibility(View.VISIBLE);
//                                        toptitle.setText(" Favorite             ");
//                                        search.setVisibility(View.GONE);
//                                        break;
//                                    case R.id.history:
//                                        plus.setVisibility(View.GONE);
//                                        toptitle.setVisibility(View.VISIBLE);
//                                        toptitle.setText("History           ");
//                                        search.setVisibility(View.GONE);
//                                        break;
//                                    case R.id.settings:
//                                       plus.setVisibility(View.INVISIBLE);
//                                        toptitle.setVisibility(View.VISIBLE);
//                                        toptitle.setText("Settings       ");
//                                        search.setVisibility(View.GONE);
//                                        break;
//                                    case R.id.home:
//                                        plus.setVisibility(View.VISIBLE);
//                                        toptitle.setVisibility(View.GONE);
//                                        search.setVisibility(View.VISIBLE);
//                                        break;
//                                }
//                                return false;
//                            }
//                        });

            }

                @Override
                public void onClick (View v){
                    Animation anim = null;
                    Animation anim1 = null;
                    Animation anim2 = null;
                    Animation anim3 = null;

                    switch (v.getId()) {
                        case R.id.search:
                            anim = AnimationUtils.loadAnimation(this, R.anim.search_anim);
                            search.setVisibility(View.GONE);
elipse.setVisibility(View.GONE);

                            anim1 = AnimationUtils.loadAnimation(this, R.anim.elipse_anim);
                            elipse1.setVisibility(View.VISIBLE);
                            searchmove.setVisibility(View.VISIBLE);
                            edtext.setVisibility(View.VISIBLE);

                        break;

                        case R.id.elipse1:
//                            anim2 = AnimationUtils.loadAnimation(this, R.anim.elipse_anim);
//                            elipse2.setVisibility(View.VISIBLE);
//                            searchmove2.setVisibility(View.VISIBLE);
//                            elipse1.setVisibility(View.INVISIBLE);
//                            searchmove.setVisibility(View.INVISIBLE);
//                            anim3 = AnimationUtils.loadAnimation(this, R.anim.search_anim2);
//                            elipse.setVisibility(View.VISIBLE);
//                            search.setVisibility(View.VISIBLE);
                            break;


                }

                    search.startAnimation(anim);
                    elipse.startAnimation(anim);
                    searchmove.startAnimation(anim1);
                    elipse1.startAnimation(anim1);
                    edtext.startAnimation(anim1);

//                    searchmove2.startAnimation(anim2);
//                    elipse2.startAnimation(anim2);
//                    search.startAnimation(anim3);
//                    elipse.startAnimation(anim3);
                    // запускаем анимацию для компонента tv

                    }
                }

