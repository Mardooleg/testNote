package com.example.myprogram;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Note extends AppCompatActivity  implements View.OnClickListener {
    ImageView back;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
              setContentView(R.layout.activity_note);
            back = findViewById(R.id.back);
            ImageView back = findViewById(R.id.back);
            back.setOnClickListener(this);

        }
    @Override
    public void onClick (View v) {
       switch (v.getId()) {
           case  R.id.back:
               Intent intent = new Intent(this, MainActivity.class);
               startActivity(intent);
               break;


           default:
               break;
        }

    }
}
