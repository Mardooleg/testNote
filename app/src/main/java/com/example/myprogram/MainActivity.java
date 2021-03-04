package com.example.myprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StateAdapter.OnClickToMore{
            ImageView plus;
            ImageView searchmove;
            ImageView searchmove2;

            ImageView elipse4;
EditText edtext;
ImageView elipse;
            ImageView elipse1;
            TextView toptitle;
            ImageView heart;
            ImageView likeheart;
    ImageView heart2;
        ImageView more;

    ImageView likeheart2;
ImageView search;
TextView title;
TextView note;
int hearty = 0;

            private RecyclerView recyclerViewNotes;
            private RecyclerView recyclerViewFavorite;
            private BottomNavigationView bottomNavigation;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                StateAdapter stateAdapter = new StateAdapter(this, App.getInstance().getAppDatabase().modelDao().getAll());
                stateAdapter.setOnClickToMore(this);
                recyclerViewNotes = findViewById(R.id.rv);
                recyclerViewNotes.setAdapter(stateAdapter);
                recyclerViewNotes.setLayoutManager(new GridLayoutManager(this, 2));

                recyclerViewFavorite = findViewById(R.id.fv);

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

//                more = findViewById(R.id.more)  ;
//               ImageView more = findViewById(R.id.more);
//               more.setOnClickListener(this);
                searchmove = findViewById(R.id.searchmove);
                ImageView searchmove = findViewById(R.id.searchmove);
                searchmove.setOnClickListener(this);
                elipse = findViewById(R.id.elipse);
                elipse4 = findViewById(R.id.elipse4);

                elipse1 = findViewById(R.id.elipse1);

                BottomNavigationView bottomNavigationView = (BottomNavigationView)
                        findViewById(R.id.bottom_nav);

                bottomNavigationView.setOnNavigationItemSelectedListener(
                        new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.favorite:
                                        plus.setVisibility(View.GONE);
                                        recyclerViewNotes.setVisibility(View.GONE);

                                        StateAdapter1 stateAdapter1 = new StateAdapter1(MainActivity.this, App.getInstance().getAppDatabase().modelDao().getAllFavorite());
                                        recyclerViewFavorite.setAdapter(stateAdapter1);
                                        recyclerViewFavorite.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                                        recyclerViewFavorite.setVisibility(VISIBLE);
                                    case R.id.history:
                                        plus.setVisibility(View.GONE);
                                        break;
                                    case R.id.settings:
                                       plus.setVisibility(View.INVISIBLE);

                                        break;
                                    case R.id.home:
                                        plus.setVisibility(View.VISIBLE);
                                        recyclerViewFavorite.setVisibility(View.GONE);

                                        StateAdapter stateAdapter = new StateAdapter(MainActivity.this, App.getInstance().getAppDatabase().modelDao().getAll());
                                        stateAdapter.setOnClickToMore(MainActivity.this::onClick);
                                        recyclerViewNotes.setAdapter(stateAdapter);
                                        recyclerViewNotes.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                                        recyclerViewNotes.setVisibility(VISIBLE);
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
                            edtext.setVisibility(View.GONE);
                            anim2 = AnimationUtils.loadAnimation(this, R.anim.elipse_anim);
                            elipse4.setVisibility(View.INVISIBLE);

                            break;

                        case R.id.plus:
                            Intent intent1 = new Intent(this, Note.class);
                            startActivity(intent1);

                                break;
                        default:
                        break;
//                        case R.id.more:

//                            break;
                    }



                    edtext.startAnimation(anim);
                    searchmove.startAnimation(anim);
                    elipse1.startAnimation(anim);
                    elipse4.startAnimation(anim2);
                    }

    @Override
    public void onClick(Notatka notatka) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_note, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

