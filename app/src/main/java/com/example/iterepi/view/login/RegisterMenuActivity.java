package com.example.iterepi.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.login.RegisterMenuController;

public class RegisterMenuActivity extends AppCompatActivity {

    private Button facebookBtn;
    private Button googleBtn;
    private Button emailBtn;
    private Button loginBtn;
    private ImageButton backBtn;
    private RegisterMenuController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);

        facebookBtn = findViewById(R.id.facebookBtn);
        googleBtn = findViewById(R.id.googleBtn);
        emailBtn = findViewById(R.id.emailBtn);
        loginBtn = findViewById(R.id.loginBtn);
        backBtn = findViewById(R.id.backBtnMain);

        controller = new RegisterMenuController(this);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        controller.onActivityResult(requestCode, resultCode, data);

        controller.getmCallbackManager().onActivityResult(requestCode,resultCode,data);


    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public Button getFacebookBtn() {
        return facebookBtn;
    }

    public Button getGoogleBtn() {
        return googleBtn;
    }

    public Button getEmailBtn() {
        return emailBtn;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }
}
