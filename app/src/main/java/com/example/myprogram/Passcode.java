package com.example.myprogram;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hanks.passcodeview.PasscodeView;

public class Passcode extends AppCompatActivity {
PasscodeView passcodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passcord_activity);
        passcodeView = findViewById(R.id.passcodeView);
        passcodeView.setPasscodeLength(5)
                .setLocalPasscode("12345")
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void onSuccess(String number) {
startActivity(new Intent(Passcode.this,MainActivity.class));
finish();
                    }
                });
    }
}
