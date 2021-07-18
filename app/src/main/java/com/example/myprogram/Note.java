package com.example.myprogram;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class Note extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
    EditText title;
    EditText note;
    ImageView tick;
    Notatka notatka;
    TextView background2;

    int colorTitle1 = R.color.greenblue1;
    int colorDec1 = R.color.greenblue2;
    final String LOG_TAG = "myLogs";
    final int DIALOG = 1;

    Dialog dialog;
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

        colorDec1 = getIntent().getIntExtra("COLOR_TITLE", R.color.greenblue1);
        colorTitle1 = getIntent().getIntExtra("COLOR_DEC", R.color.greenblue2);




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

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Log.d(LOG_TAG, "Create");
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Do you want to save notes?");
            adb.setNegativeButton("Don't save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (TextUtils.isEmpty(note.getText().toString())){
                        App.getInstance().getAppDatabase().modelDao().delete(notatka);
                        Intent intent = new Intent(Note.this, MainActivity.class);
                        Note.this.startActivity(intent);
                    }else{
                    Intent intent = new Intent(Note.this, MainActivity.class);
                    Note.this.startActivity(intent);}
                }
            }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which1) {

                            Toast.makeText(getApplicationContext(), "Note has been saved",  Toast.LENGTH_SHORT).show();

                            if (getIntent().getParcelableExtra("STRING_NOTE") != null) {
                                notatka.setTitle(title.getText().toString());
                                notatka.setNote(note.getText().toString());

                                App.getInstance().getAppDatabase().modelDao().update(notatka);
                                Intent intent = new Intent(Note.this, MainActivity.class);
                                Note.this.startActivity(intent);
                                finish();

                            } else if (notatka != null) {
                                Intent intent = new Intent(Note.this, MainActivity.class);
                                Note.this.startActivity(intent);
                                App.getInstance().getAppDatabase().modelDao().save(new Notatka(title.getText().toString(), note.getText().toString(), false));

                                finish();


                            } else {
                                Intent intent = new Intent(Note.this, MainActivity.class);
                                Note.this.startActivity(intent);
                                App.getInstance().getAppDatabase().modelDao().save(new Notatka(title.getText().toString(), note.getText().toString(), false));

                                finish();

                            }


                        }

                    });
            dialog = adb.create();
            return dialog;
        }
        return super.onCreateDialog(id);
    }

    public void onclick(View v) {
        showDialog(DIALOG);
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

                showDialog(DIALOG);

                break;

            case R.id.tick:
//                App.getInstance().getAppDatabase().modelDao().save(new Notatka(title.getText().toString(), note.getText().toString(), false));
//                closeKeyboard();
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


        }
    }


}



