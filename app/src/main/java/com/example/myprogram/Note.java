package com.example.myprogram;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Note extends AppCompatActivity  implements View.OnClickListener {
    ImageView back;
    EditText title;
    EditText note;
    ImageView tick;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
              setContentView(R.layout.activity_note);
            back = findViewById(R.id.back);
            ImageView back = findViewById(R.id.back);
            back.setOnClickListener(this);

            tick = findViewById(R.id.tick);
            ImageView tick = findViewById(R.id.tick);
            tick.setOnClickListener(this);

            title = findViewById(R.id.title);
        note = findViewById(R.id.note);

        }
    @Override
    public void onClick (View v) {
       switch (v.getId()) {
           case  R.id.back:

               Intent intent = new Intent(this, MainActivity.class);
               App.getInstance().getAppDatabase().modelDao().save(new Notatka(title.getText().toString(), note.getText().toString(), false));

               //App.getInstance().getAppDatabase().modelDao().update(new Notatka(title.getText().toString(), note.getText().toString(), false));
               startActivity(intent);
               break;
           default:
               break;
           case  R.id.tick:
               App.getInstance().getAppDatabase().modelDao().save(new Notatka(title.getText().toString(), note.getText().toString(), false));

               break;

        }

    }
}
