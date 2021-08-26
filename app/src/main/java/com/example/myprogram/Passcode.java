package com.example.myprogram;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.hanks.passcodeview.PasscodeView;

public class Passcode extends AppCompatActivity {
    public static
PasscodeView passcodeView;
    int colorTitle1 = R.color.greenblue1;
    int colorDec1 = R.color.greenblue2;
    int passcodeView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passcord_activity);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.password));
        colorDec1 = getIntent().getIntExtra("COLOR_TITLE", R.color.greenblue1);
        colorTitle1 = getIntent().getIntExtra("COLOR_DEC", R.color.greenblue2);
        passcodeView1 = getIntent().getIntExtra("PASSWORD", passcodeView1);

        passcodeView = findViewById(R.id.passcodeView);
        passcodeView.setPasscodeLength(5)
                .setLocalPasscode("00000")
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
