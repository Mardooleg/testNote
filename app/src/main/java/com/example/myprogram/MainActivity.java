package com.example.myprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StateAdapter.OnClickToMore {
    boolean stateDarkMode = false;
    TextView empty;
    TextView empty2;
    TextView systhem;
    TextView empty1;
    TextView background;
    TextView litback;
    TextView title;
    TextView note;

    ImageView plus;
    ImageView search;
    ImageView plus1;
    ImageView searchmove;
    ImageView astonaut;
    ImageView astonaut1;
    ImageView astonaut2;
    ImageView elipse4;
    ImageView item1;
    ImageView item2;
    ImageView item3;
    ImageView item4;
    ImageView item5;
    ImageView item6;
    ImageView elipse;
    ImageView elipse1;

    EditText edtext;

    Switch switch1;

    int colorFav = R.drawable.favorite;
    int colorTitle = R.drawable.elipse2;
    int colorDec = R.drawable.elipse3;
    int colorTitle1 = R.color.greenblue1;
    int colorDec1 = R.color.greenblue2;
    int colorBottom = R.color.greenblue3;

    private RecyclerView recyclerViewNotes;
    private RecyclerView recyclerViewFavorite;
    private BottomNavigationView bottomNavigation;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
       /// R.drawable.favorite_greenblue,R.drawable.elipse2_greenblue,R.drawable.elipse3_greenblue,R.color.greenblue1, R.color.greenblue2);

        int color1 = prefs.getInt("COLOR1", R.drawable.favorite_greenblue);//"No name defined" is the default value.
        int color2 = prefs.getInt("COLOR2",R.drawable.elipse2_greenblue); //0 is the default value.
        int color3 = prefs.getInt("COLOR3", R.drawable.elipse3_greenblue);//0 is the default value.
        int color4 = prefs.getInt("COLOR4",  R.color.greenblue2); //0 is the default value.
        int color5 = prefs.getInt("COLOR5",R.color.greenblue1); //0 is the default value.
        int color6 = prefs.getInt("COLOR6", R.color.greenblue3); //0 is the default value.


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.white));

        List<Notatka> notatkas = App.getInstance().getAppDatabase().modelDao().getAll("");
        Collections.reverse(notatkas);
        StateAdapter stateAdapter = new StateAdapter(this, notatkas, colorFav, colorTitle, colorDec, colorTitle1,  colorDec1, colorBottom);
        stateAdapter.setOnClickToMore(this);
        recyclerViewNotes = findViewById(R.id.rv);
        recyclerViewNotes.setAdapter(stateAdapter);
        recyclerViewNotes.setLayoutManager(new GridLayoutManager(this, 2));

        litback = findViewById(R.id.litback);
        recyclerViewFavorite = findViewById(R.id.fv);
        bottomNavigation = findViewById(R.id.bottom_nav);
        edtext = findViewById(R.id.searchwrite);
        astonaut = findViewById(R.id.astr);
        empty = findViewById(R.id.empty);
        switch1 = findViewById(R.id.switch1);
        background = findViewById(R.id.background1);
        astonaut1 = findViewById(R.id.astr1);
        empty1 = findViewById(R.id.emptyres);
        systhem = findViewById(R.id.systhem);
        astonaut2 = findViewById(R.id.astr2);
        empty2 = findViewById(R.id.emptyfav);
        title = findViewById(R.id.firsttitle);
        note = findViewById(R.id.firstnote);
        plus = findViewById(R.id.plus);

        item1 = findViewById(R.id.item1);
        ImageView item1 = findViewById(R.id.item1);
        item1.setOnClickListener(this);

        item2 = findViewById(R.id.item2);
        ImageView item2 = findViewById(R.id.item2);
        item2.setOnClickListener(this);

        item3 = findViewById(R.id.item3);
        ImageView item3 = findViewById(R.id.item3);
        item3.setOnClickListener(this);

        item4 = findViewById(R.id.item4);
        ImageView item4 = findViewById(R.id.item4);
        item4.setOnClickListener(this);

        item5 = findViewById(R.id.item5);
        ImageView item5 = findViewById(R.id.item5);
        item5.setOnClickListener(this);

        item6 = findViewById(R.id.item6);
        ImageView item6 = findViewById(R.id.item6);
        item6.setOnClickListener(this);

        search = findViewById(R.id.search);
        ImageView search = findViewById(R.id.search);
        search.setOnClickListener(this);

        plus1 = findViewById(R.id.plus1);
        ImageView plus1 = findViewById(R.id.plus1);
        plus1.setOnClickListener(this);

        if (stateAdapter.getItemCount() == 0) {
            empty.setVisibility(VISIBLE);
            astonaut.setVisibility(VISIBLE);
        } else {
            empty.setVisibility(View.GONE);
            astonaut.setVisibility(View.GONE);
        }



        edtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (recyclerViewNotes.getVisibility() == VISIBLE) {
                    List<Notatka> notatkas = App.getInstance().getAppDatabase().modelDao().getAll(edtext.getText().toString());
                    Collections.reverse(notatkas);
                    StateAdapter stateAdapter = new StateAdapter(MainActivity.this, notatkas, colorFav, colorTitle, colorDec, colorTitle1,  colorDec1, colorBottom);
                    stateAdapter.setOnClickToMore(MainActivity.this::onClick);
                    recyclerViewNotes.setAdapter(stateAdapter);
                } else if (recyclerViewFavorite.getVisibility() == VISIBLE) {
                    List<Notatka> notatkas = App.getInstance().getAppDatabase().modelDao().getAllFavorite(edtext.getText().toString());
                    Collections.reverse(notatkas);
                    StateAdapter1 stateAdapter1 = new StateAdapter1(MainActivity.this, notatkas);
                    recyclerViewFavorite.setAdapter(stateAdapter1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                                List<Notatka> notatkas1 = App.getInstance().getAppDatabase().modelDao().getAllFavorite(edtext.getText().toString());
                                Collections.reverse(notatkas1);
                                StateAdapter1 stateAdapter1 = new StateAdapter1(MainActivity.this, notatkas1);
                                edtext.setVisibility(View.GONE);
                                switch1.setVisibility(INVISIBLE);
                                recyclerViewFavorite.setAdapter(stateAdapter1);
                                recyclerViewFavorite.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                                item1.setVisibility(INVISIBLE);
                                item2.setVisibility(INVISIBLE);
                                item3.setVisibility(INVISIBLE);
                                item4.setVisibility(INVISIBLE);
                                item5.setVisibility(INVISIBLE);
                                item6.setVisibility(INVISIBLE);
                                systhem.setVisibility(INVISIBLE);

                                recyclerViewFavorite.setVisibility(VISIBLE);
                            case R.id.history:
                                plus.setVisibility(View.GONE);
                                edtext.setVisibility(View.GONE);

                                break;
                            case R.id.settings:
                                plus.setVisibility(View.INVISIBLE);
                                edtext.setVisibility(View.GONE);

                                recyclerViewFavorite.setVisibility(View.GONE);
                                recyclerViewNotes.setVisibility(View.GONE);

                                searchmove.setVisibility(View.GONE);
                                search.setVisibility(View.GONE);
                                elipse.setVisibility(View.GONE);
                                elipse1.setVisibility(View.GONE);

                                switch1.setVisibility(VISIBLE);
                                astonaut.setVisibility(View.GONE);
                                empty.setVisibility(View.GONE);

                                item1.setVisibility(VISIBLE);
                                item2.setVisibility(VISIBLE);
                                item3.setVisibility(VISIBLE);
                                item4.setVisibility(VISIBLE);
                                item5.setVisibility(VISIBLE);
                                item6.setVisibility(VISIBLE);
                                systhem.setVisibility(VISIBLE);

                                break;
                            case R.id.home:
                                plus.setVisibility(View.VISIBLE);
                                recyclerViewFavorite.setVisibility(View.GONE);
                                edtext.setVisibility(View.GONE);
                                List<Notatka> notatkas = App.getInstance().getAppDatabase().modelDao().getAll(edtext.getText().toString());
                                Collections.reverse(notatkas);
                                StateAdapter stateAdapter = new StateAdapter(MainActivity.this, notatkas, colorFav, colorTitle, colorDec, colorTitle1,  colorDec1, colorBottom);
                                stateAdapter.setOnClickToMore(MainActivity.this::onClick);
                                recyclerViewNotes.setAdapter(stateAdapter);
                                recyclerViewNotes.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                                recyclerViewNotes.setVisibility(VISIBLE);
                                search.setVisibility(VISIBLE);
                                elipse.setVisibility(VISIBLE);
                                switch1.setVisibility(INVISIBLE);
                                systhem.setVisibility(INVISIBLE);

                                item1.setVisibility(INVISIBLE);
                                item2.setVisibility(INVISIBLE);
                                item3.setVisibility(INVISIBLE);
                                item4.setVisibility(INVISIBLE);
                                item5.setVisibility(INVISIBLE);
                                item6.setVisibility(INVISIBLE);
                                break;
                        }
                        return true;
                    }
                });
        Switch onOffSwitch = (Switch) findViewById(R.id.switch1);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            private int[][] bottomNavBarStateList = new int[][]{{android.R.attr.state_checked}, {-android.R.attr.state_checked}};

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                stateDarkMode = isChecked;
                if (isChecked) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(getResources().getColor(R.color.dark));
                    background.setBackgroundColor(getResources().getColor(R.color.dark));
                    litback.setBackgroundColor(getResources().getColor(R.color.dark));
                    switch1.setTextColor(getResources().getColor(R.color.white));
                    search.setColorFilter(getResources().getColor(R.color.dark));
                    searchmove.setColorFilter(getResources().getColor(R.color.dark));
                    systhem.setTextColor(getResources().getColor(R.color.white));
                    plus1.setColorFilter(getResources().getColor(R.color.dark));

                } else {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(getResources().getColor(R.color.white));
                    background.setBackgroundColor(getResources().getColor(R.color.white));
                    litback.setBackgroundColor(getResources().getColor(R.color.white));
                    switch1.setTextColor(getResources().getColor(R.color.black));
                    search.setColorFilter(getResources().getColor(R.color.white));
                    searchmove.setColorFilter(getResources().getColor(R.color.white));
                    systhem.setTextColor(getResources().getColor(R.color.dark));
                    plus1.setColorFilter(getResources().getColor(R.color.white));
                }
            }
        });

        colorStyle(color1,color2,color3,color4,color5, color6);
    }


      @SuppressLint("ResourceType")
      private void colorStyle(int color1, int color2, int color3 , int color4 , int color5, int color6 ) {



          colorFav = color1;
          colorTitle = color2;
          colorDec = color3;
          colorTitle1 = color4;//light
          colorDec1 = color5;//темніший
          colorBottom = color6;//коли не вибрана вкладка
          bottomNavigation.setBackgroundColor(getResources().getColor(colorDec1));
          plus.setColorFilter(getResources().getColor(colorDec1));
          elipse.setColorFilter(getResources().getColor(colorDec1));
          elipse4.setColorFilter(getResources().getColor(colorDec1));
          elipse1.setColorFilter(getResources().getColor(colorDec1));

          int[] colorList = null;
          if (stateDarkMode) {
              colorList = new int[]{ContextCompat.getColor(this, R.color.dark), ContextCompat.getColor(this, color6)};
          } else {
              colorList = new int[]{ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, color6)}; }
          ColorStateList colorStateList = new ColorStateList(bottomNavBarStateList, colorList);
          bottomNavigation.setItemIconTintList(colorStateList);
          bottomNavigation.setItemTextColor(colorStateList);

          SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
          editor.putInt("COLOR1", color1);
          editor.putInt("COLOR2", color2);
          editor.putInt("COLOR3", color3);
          editor.putInt("COLOR4", color4);
          editor.putInt("COLOR5", color5);
          editor.putInt("COLOR6", color6);
          editor.apply();
      }

    private int[][] bottomNavBarStateList = new int[][]{{android.R.attr.state_checked}, {-android.R.attr.state_checked}};
    @Override
    public void onClick(View v) {
        Animation anim = null;
        Animation anim2 = null;
        switch (v.getId()) {
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
                edtext.setText("");
                break;


            case R.id.plus1:
                Intent intent2 = new Intent(this, Note.class);
                intent2.putExtra("COLOR_TITLE" ,colorTitle1 );
                intent2.putExtra("COLOR_DEC" ,colorDec1 );
                startActivity(intent2);
                break;

            case R.id.item1:
                colorStyle(R.drawable.favorite_greenblue,R.drawable.elipse2_greenblue,R.drawable.elipse3_greenblue, R.color.greenblue2,R.color.greenblue1, R.color.greenblue3);

                break;


            case R.id.item2:
                colorStyle(R.drawable.favorite_orange,R.drawable.elipse2_orange,R.drawable.elipse3_orange, R.color.orange2,R.color.orange1, R.color.orange3);// Метод


                break;

            case R.id.item3:
                colorStyle(R.drawable.favorite_purple,R.drawable.elipse2_purple,R.drawable.elipse3_purple,R.color.purple2, R.color.purple1, R.color.purple3);

                break;

            case R.id.item4:
                colorStyle(R.drawable.favorite_green,R.drawable.elipse2_green,R.drawable.elipse3_green,R.color.green2, R.color.green1, R.color.green3);


                break;

            case R.id.item5:
                colorStyle(R.drawable.favorite_blue,R.drawable.elipse2_blue,R.drawable.elipse3_blue,R.color.blue2, R.color.blue1, R.color.blue3);

                break;

            case R.id.item6:
                colorStyle(R.drawable.favorite_red,R.drawable.elipse2_red,R.drawable.elipse3_red,R.color.red2, R.color.red1, R.color.red3);

                break;
        }

        if (anim != null) {
            edtext.startAnimation(anim);
            searchmove.startAnimation(anim);
            elipse1.startAnimation(anim);
            elipse4.startAnimation(anim2);
        }
    }

    @Override
    public void onClick(Notatka notatka) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MyDialogTheme);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_note, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.
                
                setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                App.getInstance().getAppDatabase().modelDao().delete(notatka);

                List<Notatka> notatkas = App.getInstance().getAppDatabase().modelDao().getAll(edtext.getText().toString());
                Collections.reverse(notatkas);
                StateAdapter stateAdapter = new StateAdapter(MainActivity.this, notatkas, colorFav, colorTitle, colorDec, colorTitle1,  colorDec1, colorBottom);
                stateAdapter.setOnClickToMore(MainActivity.this::onClick);
                recyclerViewNotes.setAdapter(stateAdapter);
            }
        }).create();
        alertDialog.show();
    }
}

