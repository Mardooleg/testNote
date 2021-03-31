package com.example.myprogram;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Note extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
    EditText title;
    EditText note;
    ImageView tick;
    Notatka notatka;
TextView background2;

    int colorTitle1 = R.color.greenblue1;
    int colorDec1 = R.color.greenblue2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

//        SharedPreferences.Editor editor = getSharedPreferences("Colrtool", MODE_PRIVATE).edit();
//        editor.putString("name", "Elena");
//        editor.putInt("idName", 12);
//        editor.apply();


        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.greenblue2));
        background2 = findViewById(R.id.backgroun1);
        tick = findViewById(R.id.tick);
        back = findViewById(R.id.back);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(this);
        title = findViewById(R.id.title);
        note = findViewById(R.id.note);
        tick = findViewById(R.id.tick);
        ImageView tick = findViewById(R.id.tick);
        tick.setOnClickListener(this);

        notatka = getIntent().getParcelableExtra("STRING_NOTE");

        colorDec1   = getIntent().getIntExtra("COLOR_TITLE", R.color.greenblue1);
        colorTitle1 = getIntent().getIntExtra("COLOR_DEC",  R.color.greenblue2);


        Window window1 = getWindow();
        window1.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window1.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window1.setStatusBarColor(getResources().getColor(colorDec1));
        background2.setBackgroundColor(getResources().getColor(colorDec1));
        title.setHintTextColor(getResources().getColor(colorTitle1));
        note.setHintTextColor(getResources().getColor(colorTitle1));
        tick.setColorFilter(getResources().getColor(colorTitle1));
        back.setColorFilter(getResources().getColor(colorTitle1));




        if (notatka != null) {
            title.setText(notatka.getTitle());
            note.setText(notatka.getNote());
        }
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (getIntent().getParcelableExtra("STRING_NOTE") != null) {
                    notatka.setTitle(title.getText().toString());
                    notatka.setNote(note.getText().toString());

                    App.getInstance().getAppDatabase().modelDao().update(notatka);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();

                    break;
                } else if (notatka != null) {
                    Intent intent = new Intent(this, MainActivity.class);
                    App.getInstance().getAppDatabase().modelDao().save(new Notatka(title.getText().toString(), note.getText().toString(), false));
                    startActivity(intent);
                    finish();

                    break;

                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    App.getInstance().getAppDatabase().modelDao().save(new Notatka(title.getText().toString(), note.getText().toString(), false));
                    startActivity(intent);
                    finish();

                    break;
                }
            case R.id.tick:
//                App.getInstance().getAppDatabase().modelDao().save(new Notatka(title.getText().toString(), note.getText().toString(), false));
                closeKeyboard();
                break;

        }

    }
}
