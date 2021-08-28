package com.example.myprogram;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hanks.passcodeview.PasscodeView;

public class Passcode_enter extends AppCompatActivity {
    public static int password;
    EditText title;
    EditText note;
    Notatka notatka;
    Notatka passcode;
    PasscodeView passcodeView1;

    int colorTitle1 = R.color.greenblue1;
    int colorDec1 = R.color.greenblue2;
    int bool = 0;
    final String SAVED_TEXT = "saved_text";
    SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passcord_activity);









        passcodeView1 = findViewById(R.id.passcodeView);
//        if (passcode == null) {
////            App.getInstance().getAppDatabase().modelDao().update(passcode);
//
//            passcode.setPasscode(notatka.getPasscode());
//        }else {
//            passcodeView1.setLocalPasscode(String.valueOf(passcode));
//        }

//
    passcodeView1.setPasscodeLength(5) .setListener(new PasscodeView.PasscodeViewListener()

                {

                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void onSuccess(String number) {
//                        Intent intent1 = new Intent(Passcode_enter.this, Passcode.class);
//                        intent1.putExtra("PASSWORD" , String.valueOf(passcodeView1));

//                        startActivity(new Intent(Passcode_enter.this,MainActivity.class));
//                        App.getInstance().getAppDatabase().modelDao().update(notatka);


                        saveText();



                    }
                });
        if(passcodeView1.getLocalPasscode() == "" ){
            loadText();
        }else {


        }
//        if (notatka != null) {
//            title.setText(notatka.getTitle());
//            note.setText(notatka.getNote());
//            passcode.setPasscode(notatka.getPasscode());
//        }
      }
    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, passcodeView1.getLocalPasscode());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }
    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        passcodeView1.setLocalPasscode(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}
