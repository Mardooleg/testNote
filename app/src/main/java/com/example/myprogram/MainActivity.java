package com.example.myprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
            ImageView plus;
            ImageView searchmove;
            ImageView searchmove2;
TextView firstnote;
TextView firsttitle;
            BottomNavigationView favorite;
    BottomNavigationView history;
    BottomNavigationView home;
    BottomNavigationView settings;
    BottomNavigationView calendar;

            ImageView elipse4;
EditText edtext;
ImageView elipse;
            ImageView elipse1;
            TextView toptitle;
            ImageView heart;
            ImageView likeheart;
    ImageView heart2;
    ImageView likeheart2;
ImageView search;
TextView title;
TextView note;
int hearty = 0;
            BottomNavigationView bottomNavigation;

            @Override
            protected void onCreate(Bundle savedInstanceState) {



                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                StateAdapter stateAdapter = new StateAdapter(this, App.getInstance().getAppDatabase().modelDao().getAll());

                RecyclerView recyclerView = findViewById(R.id.rv);
                recyclerView.setAdapter(stateAdapter);

                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                RecyclerView favoriteView = findViewById(R.id.fv);
                favoriteView.setAdapter(stateAdapter);

                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


                bottomNavigation = findViewById(R.id.bottom_nav);
                edtext = findViewById(R.id.searchwrite);

                title = findViewById(R.id.firsttitle);
//                TextView title = findViewById(R.id.firsttitle);
//                title.setOnClickListener(this);

                note = findViewById(R.id.firstnote);
//                TextView note = findViewById(R.id.firstnote);
//                note.setOnClickListener(this);

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
                elipse4 = findViewById(R.id.elipse4);

                elipse1 = findViewById(R.id.elipse1);

                toptitle =  findViewById(R.id.toptitle);

                BottomNavigationView bottomNavigationView = (BottomNavigationView)
                        findViewById(R.id.bottom_nav);

                bottomNavigationView.setOnNavigationItemSelectedListener(
                        new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.favorite:
                                        plus.setVisibility(View.GONE);
                                        toptitle.setVisibility(View.VISIBLE);
                                        toptitle.setText(" Favorite             ");
                                        search.setVisibility(View.GONE);
//                                        if() {
//                                            App.getInstance().getAppDatabase().modelDao().save(new Notatka(title.getText().toString(), note.getText().toString(), false));
//                                            break;
//                                        }
                                    case R.id.history:
                                        plus.setVisibility(View.GONE);
                                        toptitle.setVisibility(View.VISIBLE);
                                        toptitle.setText("History           ");
                                        search.setVisibility(View.GONE);
                                        break;
                                    case R.id.settings:
                                       plus.setVisibility(View.INVISIBLE);
                                        toptitle.setVisibility(View.VISIBLE);
                                        toptitle.setText("Settings       ");
                                        search.setVisibility(View.GONE);
                                        break;
                                    case R.id.home:
                                        plus.setVisibility(View.VISIBLE);
                                        toptitle.setVisibility(View.GONE);
                                        search.setVisibility(View.VISIBLE);
                                        break;
                                }
                                return true;
                            }
                        });

            }

                @Override
                public void onClick (View v) {
                    Animation anim = null;
                    Animation anim2 = null;
                    switch (v.getId()) {
//                        case R.id.note:
//                            Intent intent = new Intent(this, Note.class);
//                            startActivity(intent);
//                            break;
//                            default:
//                            break;


                        case R.id.search:
                            anim = AnimationUtils.loadAnimation(this, R.anim.search_anim);
                            search.setVisibility(View.GONE);
                            elipse.setVisibility(View.VISIBLE);
                            elipse1.setVisibility(View.VISIBLE);
                            searchmove.setVisibility(View.VISIBLE);
                            edtext.setVisibility(View.VISIBLE);
                            anim2 = AnimationUtils.loadAnimation(this, R.anim.elipse_anim);
                            break;

                        case R.id.searchmove:
                            anim = AnimationUtils.loadAnimation(this, R.anim.search_anim2);
                            search.setVisibility(View.VISIBLE);
                            elipse.setVisibility(View.VISIBLE);
                            elipse1.setVisibility(INVISIBLE);
                            searchmove.setVisibility(INVISIBLE);
                            edtext.setVisibility(INVISIBLE);
                            anim2 = AnimationUtils.loadAnimation(this, R.anim.elipse_anim);
                            elipse4.setVisibility(View.INVISIBLE);

                            break;

                        case R.id.plus:
                            Intent intent1 = new Intent(this, Note.class);
                            startActivity(intent1);
                                break;
                        default:
                        break;

                    }



                    edtext.startAnimation(anim);
                    searchmove.startAnimation(anim);
                    elipse1.startAnimation(anim);
                    elipse4.startAnimation(anim2);
//                    elipse4.startAnimation(anim2);
                    }
                }

