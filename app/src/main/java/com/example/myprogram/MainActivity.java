package com.example.myprogram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StateAdapter.OnClickToMore, StateAdapter1.OnClickToMore, GestureDetector.OnGestureListener{
    boolean stateDarkMode = false;
//    private AppCompatEditText acetStatus;
    private ListPopupWindow statusPopupList;
   int swipe = 1;
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
//    ImageView item1;
//    ImageView item2;
//    ImageView item3;
//    ImageView item4;
//    ImageView item5;
//    ImageView item6;
    ImageView elipse;
    ImageView elipse1;

    EditText edtext;
    EditText acetStatus;

    Switch switch1;

    final String LOG_TAG = "myLogs";
    final int DIALOG = 1;

    Dialog dialog;

    int colorFav = R.drawable.favorite;
    int colorTitle = R.drawable.elipse2;
    int colorDec = R.drawable.elipse3;
    int colorTitle1 = R.color.greenblue1;
    int colorDec1 = R.color.greenblue2;
    int colorBottom = R.color.greenblue3;

    private RecyclerView recyclerViewNotes;
    private RecyclerView recyclerViewFavorite;
    private BottomNavigationView bottomNavigation;


    private static final String TAG = "Swipe position";
    private float x1, x2, y1, y2;
    private static int MIN_DISTANSE = 150;
    private GestureDetector gestureDetector;

    private ViewPager viewPager;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        this.gestureDetector = new GestureDetector (MainActivity.this, this);

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
        StateAdapter stateAdapter = new StateAdapter(this, notatkas, color1, color2, color3, color4,  color5, color6);
        stateAdapter.setOnClickToMore(this);


        List<Notatka> notatkas1 = App.getInstance().getAppDatabase().modelDao().getAllFavorite("");
        Collections.reverse(notatkas1);
        StateAdapter1 stateAdapter1 = new StateAdapter1(this, notatkas, color1, color2, color3, color4,  color5, color6);
        stateAdapter1.setOnClickToMore(this);
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

        acetStatus = findViewById(R.id.acet_status);
        EditText acet_status = findViewById(R.id.acet_status);
        acetStatus.setOnClickListener(this);
//
//        item1 = findViewById(R.id.item1);
//        ImageView item1 = findViewById(R.id.item1);
//        item1.setOnClickListener(this);
//
//        item2 = findViewById(R.id.item2);
//        ImageView item2 = findViewById(R.id.item2);
//        item2.setOnClickListener(this);
//
//        item3 = findViewById(R.id.item3);
//        ImageView item3 = findViewById(R.id.item3);
//        item3.setOnClickListener(this);
//
//        item4 = findViewById(R.id.item4);
//        ImageView item4 = findViewById(R.id.item4);
//        item4.setOnClickListener(this);
//
//        item5 = findViewById(R.id.item5);
//        ImageView item5 = findViewById(R.id.item5);
//        item5.setOnClickListener(this);
//
//        item6 = findViewById(R.id.item6);
//        ImageView item6 = findViewById(R.id.item6);
//        item6.setOnClickListener(this);

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
                    StateAdapter1 stateAdapter1 = new StateAdapter1(MainActivity.this, notatkas,  colorFav, colorTitle, colorDec, colorTitle1,  colorDec1, colorBottom);
                    stateAdapter1.setOnClickToMore(MainActivity.this::onClick);
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
viewPager = findViewById(R.id.viewpager);
setUpViewPager();
        Menu menu = bottomNavigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(

                new BottomNavigationView.OnNavigationItemSelectedListener() {


                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.favorite:
                                plus.setVisibility(View.GONE);
                                plus1.setVisibility(View.GONE);
                                recyclerViewNotes.setVisibility(View.GONE);
                                List<Notatka> notatkas1 = App.getInstance().getAppDatabase().modelDao().getAllFavorite(edtext.getText().toString());
                                Collections.reverse(notatkas1);
                                StateAdapter1 stateAdapter1 = new StateAdapter1(MainActivity.this, notatkas1 , colorFav, colorTitle, colorDec, colorTitle1,  colorDec1, colorBottom);
                                stateAdapter1.setOnClickToMore(MainActivity.this::onClick);
                                edtext.setVisibility(View.GONE);
                                switch1.setVisibility(INVISIBLE);
                                acet_status.setVisibility(INVISIBLE);
                                recyclerViewFavorite.setAdapter(stateAdapter1);
                                recyclerViewFavorite.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

//                                item1.setVisibility(INVISIBLE);
//                                item2.setVisibility(INVISIBLE);
//                                item3.setVisibility(INVISIBLE);
//                                item4.setVisibility(INVISIBLE);
//                                item5.setVisibility(INVISIBLE);
//                                item6.setVisibility(INVISIBLE);
                                systhem.setVisibility(INVISIBLE);

                                recyclerViewFavorite.setVisibility(VISIBLE);
                              swipe = 0;
viewPager.setCurrentItem(0);

                                break;
                            case R.id.settings:
                                plus1.setVisibility(View.GONE);
                                plus.setVisibility(View.GONE);
                                edtext.setVisibility(View.GONE);
                                acet_status.setVisibility(VISIBLE);
                                recyclerViewFavorite.setVisibility(View.GONE);
                                recyclerViewNotes.setVisibility(View.GONE);

                                searchmove.setVisibility(View.GONE);
                                search.setVisibility(View.GONE);
                                elipse.setVisibility(View.GONE);
                                elipse1.setVisibility(View.GONE);

                                switch1.setVisibility(VISIBLE);
                                astonaut.setVisibility(View.GONE);
                                empty.setVisibility(View.GONE);

                                systhem.setVisibility(VISIBLE);
swipe = 2;
                                viewPager.setCurrentItem(2);

                                break;
                            case R.id.home:
                                plus1.setVisibility(View.VISIBLE);
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
                                acet_status.setVisibility(INVISIBLE);
//                                item1.setVisibility(INVISIBLE);
//                                item2.setVisibility(INVISIBLE);
//                                item3.setVisibility(INVISIBLE);
//                                item4.setVisibility(INVISIBLE);
//                                item5.setVisibility(INVISIBLE);
//                                item6.setVisibility(INVISIBLE);
                                swipe = 1;
                                viewPager.setCurrentItem(1);

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



//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        setPopupList();
        //we need to show the list when clicking on the field
        setListeners();
    }

    private void setListeners() {
        acetStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusPopupList.show();
            }
        });
    }

    private void setPopupList() {
        final List<String> status = new ArrayList<>();
        status.add(0, "Light");
        status.add(1, "Dark");
        status.add(2, "Green");
        status.add(3, "Turquoise");
        status.add(4, "Purple");
        status.add(5, "Orange");
        status.add(6, "Red");
        status.add(7, "Blue");

        statusPopupList = new ListPopupWindow(MainActivity.this);
        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.item_simple_status, R.id.tv_element, status);
        statusPopupList.setAnchorView(acetStatus); //this let as set the popup below the EditText
        statusPopupList.setAdapter(adapter);
        statusPopupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                acetStatus.setText(status.get(position));//we set the selected element in the EditText
                statusPopupList.dismiss();

                switch ((int) id) {
                    case 0:
                        colorStyle(R.drawable.favorite_colorblind,R.drawable.elipse2_colorblind,R.drawable.elipse3_colorblind, R.color.white,R.color.color_blind2, R.color.color_blind3);
                        break;
                    case 1:
                        colorStyle(R.drawable.favorite_dark,R.drawable.elipse2_dark,R.drawable.elipse3_dark, R.color.white,R.color.color_dark2, R.color.color_dark3);
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
                        break;
                    case 2:
                        colorStyle(R.drawable.favorite_green,R.drawable.elipse2_green,R.drawable.elipse3_green,R.color.green2, R.color.green1, R.color.green3);
                        break;
                    case 3:
                        colorStyle(R.drawable.favorite_greenblue,R.drawable.elipse2_greenblue,R.drawable.elipse3, R.color.greenblue2,R.color.greenblue1, R.color.greenblue3);
                        break;
                    case 4:
                        colorStyle(R.drawable.favorite_purple,R.drawable.elipse2_purple,R.drawable.elipse3_purple,R.color.purple2, R.color.purple1, R.color.purple3);
                        break;
                    case 5:
                        colorStyle(R.drawable.favorite_orange,R.drawable.elipse2_orange,R.drawable.elipse3_orange, R.color.orange2,R.color.orange1, R.color.orange3);// Метод
                        break;
                    case 6:
                        colorStyle(R.drawable.favorite_red,R.drawable.elipse2_red,R.drawable.elipse3_red,R.color.red2, R.color.red1, R.color.red3);
                        break;
                    case 7:
                        colorStyle(R.drawable.favorite_blue,R.drawable.elipse2_blue,R.drawable.elipse3_blue,R.color.blue2, R.color.blue1, R.color.blue3);
                        break;
                 }

            }
        });




    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.mymenu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.r:
//                Toast.makeText(this, "update clicked", Toast.LENGTH_SHORT).show();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

      @SuppressLint("ResourceType")
      private void colorStyle(int color1, int color2, int color3 , int color4 , int color5, int color6/* int color7 , int color8*/) {


          colorFav = color1;
          colorTitle = color2;
          colorDec = color3;
          colorTitle1 = color4;//light
          colorDec1 = color5;//dark
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
                closeKeyboard();
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
                MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.pop);
                ring.start();
                break;

//            case R.id.item1:
//                colorStyle(R.drawable.favorite_greenblue,R.drawable.elipse2_greenblue,R.drawable.elipse3_greenblue, R.color.greenblue2,R.color.greenblue1, R.color.greenblue3);
//
//                break;
//
//
//            case R.id.item2:
//                colorStyle(R.drawable.favorite_orange,R.drawable.elipse2_orange,R.drawable.elipse3_orange, R.color.orange2,R.color.orange1, R.color.orange3);// Метод
//
//
//                break;
//
//            case R.id.item3:
//                colorStyle(R.drawable.favorite_purple,R.drawable.elipse2_purple,R.drawable.elipse3_purple,R.color.purple2, R.color.purple1, R.color.purple3);
//
//                break;
//
//            case R.id.item4:
//                colorStyle(R.drawable.favorite_green,R.drawable.elipse2_green,R.drawable.elipse3_green,R.color.green2, R.color.green1, R.color.green3);
//
//
//                break;
//
//            case R.id.item5:
//                colorStyle(R.drawable.favorite_blue,R.drawable.elipse2_blue,R.drawable.elipse3_blue,R.color.blue2, R.color.blue1, R.color.blue3);
//
//                break;
//
//            case R.id.item6:
//                colorStyle(R.drawable.favorite_red,R.drawable.elipse2_red,R.drawable.elipse3_red,R.color.red2, R.color.red1, R.color.red3);
//
//                break;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
// add a list
        String[] first_dialog = {"Delete", "Edit", "Add Password"};
        builder.setItems(first_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast toast = Toast.makeText(getApplicationContext(), "You delete this note", Toast.LENGTH_SHORT);
                toast.show();
                App.getInstance().getAppDatabase().modelDao().delete(notatka);

                MediaPlayer delete_pop= MediaPlayer.create(MainActivity.this, R.raw.delete);
                delete_pop.start();

                List<Notatka> notatkas = App.getInstance().getAppDatabase().modelDao().getAll(edtext.getText().toString());
                Collections.reverse(notatkas);
                StateAdapter stateAdapter = new StateAdapter(MainActivity.this, notatkas, colorFav, colorTitle, colorDec, colorTitle1,  colorDec1, colorBottom);
                stateAdapter.setOnClickToMore(MainActivity.this::onClick);
                recyclerViewNotes.setAdapter(stateAdapter);
                   break;
                    case 1:
//                        Intent intent = new Intent(MainActivity.this, Note.class);
//                        MainActivity.this.startActivity(intent);

                        Intent intent1 = new Intent(MainActivity.this, Note.class);
                        intent1.putExtra("STRING_NOTE" , notatka);

                        intent1.putExtra("COLOR_TITLE" , colorTitle1);
                        intent1.putExtra("COLOR_DEC" ,colorDec1 );
                        MainActivity.this.startActivity(intent1);

                        break;


                    case 2: Intent intent22 = new Intent(MainActivity.this, Passcode_enter.class);
                        MainActivity.this.startActivity(intent22);
                        break;


                    }
            }
        });
// create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//   builder.setTitle("Do you want delete this note?");
//        AlertDialog alertDialog = builder.
//
//                setNegativeButton("No", new DialogInterface.OnClickListener() {
////                    Intent intent5 = new Intent( Passcode.class);
////                    startActivity(intent5);
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast toast = Toast.makeText(getApplicationContext(), "You delete this note", Toast.LENGTH_SHORT);
//                toast.show();
//                App.getInstance().getAppDatabase().modelDao().delete(notatka);
//
//                MediaPlayer delete_pop= MediaPlayer.create(MainActivity.this, R.raw.delete);
//                delete_pop.start();
//
//                List<Notatka> notatkas = App.getInstance().getAppDatabase().modelDao().getAll(edtext.getText().toString());
//                Collections.reverse(notatkas);
//                StateAdapter stateAdapter = new StateAdapter(MainActivity.this, notatkas, colorFav, colorTitle, colorDec, colorTitle1,  colorDec1, colorBottom);
//                stateAdapter.setOnClickToMore(MainActivity.this::onClick);
//                recyclerViewNotes.setAdapter(stateAdapter);
//            }
//        }).create();
//        alertDialog.show();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Animation anim_swipe = null;
        Animation anim_swipe2 = null;
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float valueX = x2 - x1;
                float valueY = y2 - y1;


                if (Math.abs(valueX) > MIN_DISTANSE) {

                    if (x2 > x1) {
                        if (swipe == 1) {


                            plus.setVisibility(View.GONE);
                            plus1.setVisibility(View.GONE);
                            recyclerViewNotes.setVisibility(View.GONE);
                            List<Notatka> notatkas1 = App.getInstance().getAppDatabase().modelDao().getAllFavorite(edtext.getText().toString());
                            Collections.reverse(notatkas1);
                            StateAdapter1 stateAdapter1 = new StateAdapter1(MainActivity.this, notatkas1, colorFav, colorTitle, colorDec, colorTitle1, colorDec1, colorBottom);
                            stateAdapter1.setOnClickToMore(MainActivity.this::onClick);
                            edtext.setVisibility(View.GONE);
                            switch1.setVisibility(INVISIBLE);
                            recyclerViewFavorite.setAdapter(stateAdapter1);
                            recyclerViewFavorite.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

//                            item1.setVisibility(INVISIBLE);
//                            item2.setVisibility(INVISIBLE);
//                            item3.setVisibility(INVISIBLE);
//                            item4.setVisibility(INVISIBLE);
//                            item5.setVisibility(INVISIBLE);
//                            item6.setVisibility(INVISIBLE);
                            systhem.setVisibility(INVISIBLE);


                            recyclerViewFavorite.setVisibility(VISIBLE);

                            Menu menu = bottomNavigation.getMenu();
                            MenuItem menuItem = menu.getItem(0);
                            menuItem.setChecked(true);
                        } else if (swipe == 2) {
                            anim_swipe = AnimationUtils.loadAnimation(this, R.anim.swipe_anim);
                            anim_swipe2 = AnimationUtils.loadAnimation(this, R.anim.swipe_anim2);

                            plus1.setVisibility(View.VISIBLE);
                            plus.setVisibility(View.VISIBLE);
                            recyclerViewFavorite.setVisibility(View.GONE);
                            edtext.setVisibility(View.GONE);
                            List<Notatka> notatkas = App.getInstance().getAppDatabase().modelDao().getAll(edtext.getText().toString());
                            Collections.reverse(notatkas);
                            StateAdapter stateAdapter = new StateAdapter(MainActivity.this, notatkas, colorFav, colorTitle, colorDec, colorTitle1, colorDec1, colorBottom);
                            stateAdapter.setOnClickToMore(MainActivity.this::onClick);
                            recyclerViewNotes.setAdapter(stateAdapter);
                            recyclerViewNotes.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                            recyclerViewNotes.setVisibility(VISIBLE);
                            search.setVisibility(VISIBLE);
                            elipse.setVisibility(VISIBLE);
                            switch1.setVisibility(INVISIBLE);
                            systhem.setVisibility(INVISIBLE);

//                            item1.setVisibility(INVISIBLE);
//                            item2.setVisibility(INVISIBLE);
//                            item3.setVisibility(INVISIBLE);
//                            item4.setVisibility(INVISIBLE);
//                            item5.setVisibility(INVISIBLE);
//                            item6.setVisibility(INVISIBLE);

                            Menu menu1 = bottomNavigation.getMenu();
                            MenuItem menuItem1 = menu1.getItem(1);
                            menuItem1.setChecked(true);
                        }

                    } else {

//

                    }
                } else if (Math.abs(valueY) > MIN_DISTANSE) ;
            {
                if (y2 > y1) {


                } else {


                }
            }
        }
        if (anim_swipe != null) {
//            item1.startAnimation(anim_swipe);
//            item2.startAnimation(anim_swipe);
//            item3.startAnimation(anim_swipe);
//            item4.startAnimation(anim_swipe);
//            item5.startAnimation(anim_swipe);
//            item6.startAnimation(anim_swipe);
      switch1.startAnimation(anim_swipe);
            systhem.startAnimation(anim_swipe);

            recyclerViewNotes.startAnimation(anim_swipe2);
            elipse.startAnimation(anim_swipe2);
            search.startAnimation(anim_swipe2);
            plus.startAnimation(anim_swipe2);
            plus1.startAnimation(anim_swipe2);
        }
            return super.onTouchEvent(event);


    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
private  void setUpViewPager(){
ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
viewPager.setAdapter(viewPageAdapter);

viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
switch (position){
    case 0:
        Menu menu1 = bottomNavigation.getMenu();
        MenuItem menuItem1 = menu1.getItem(0);
        menuItem1.setChecked(true);

        plus.setVisibility(View.GONE);
        plus1.setVisibility(View.GONE);
        recyclerViewNotes.setVisibility(View.GONE);
        List<Notatka> notatkas1 = App.getInstance().getAppDatabase().modelDao().getAllFavorite(edtext.getText().toString());
        Collections.reverse(notatkas1);
        StateAdapter1 stateAdapter1 = new StateAdapter1(MainActivity.this, notatkas1 , colorFav, colorTitle, colorDec, colorTitle1,  colorDec1, colorBottom);
        stateAdapter1.setOnClickToMore(MainActivity.this::onClick);
        edtext.setVisibility(View.GONE);
        switch1.setVisibility(INVISIBLE);
        recyclerViewFavorite.setAdapter(stateAdapter1);
        recyclerViewFavorite.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

//        item1.setVisibility(INVISIBLE);
//        item2.setVisibility(INVISIBLE);
//        item3.setVisibility(INVISIBLE);
//        item4.setVisibility(INVISIBLE);
//        item5.setVisibility(INVISIBLE);
//        item6.setVisibility(INVISIBLE);
        systhem.setVisibility(INVISIBLE);

        recyclerViewFavorite.setVisibility(VISIBLE);
        break;
    case 1:
        Menu menu2 = bottomNavigation.getMenu();
        MenuItem menuItem2 = menu2.getItem(1);
        menuItem2.setChecked(true);
        plus1.setVisibility(View.VISIBLE);
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

//        item1.setVisibility(INVISIBLE);
//        item2.setVisibility(INVISIBLE);
//        item3.setVisibility(INVISIBLE);
//        item4.setVisibility(INVISIBLE);
//        item5.setVisibility(INVISIBLE);
//        item6.setVisibility(INVISIBLE);
        viewPager.setCurrentItem(1);

        break;
    case 2:
        Menu menu3 = bottomNavigation.getMenu();
        MenuItem menuItem3 = menu3.getItem(2);
        menuItem3.setChecked(true);

        plus1.setVisibility(View.GONE);
        plus.setVisibility(View.GONE);
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

//        item1.setVisibility(VISIBLE);
//        item2.setVisibility(VISIBLE);
//        item3.setVisibility(VISIBLE);
//        item4.setVisibility(VISIBLE);
//        item5.setVisibility(VISIBLE);
//        item6.setVisibility(VISIBLE);
        systhem.setVisibility(VISIBLE);
        break;

}




    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}


