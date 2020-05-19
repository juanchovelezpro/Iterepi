package com.example.iterepi.view.login;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.LoginStoreController;
import com.google.android.material.textfield.TextInputLayout;

public class LoginStoreActivity extends AppCompatActivity {

    private LoginStoreController controller;
    private Button forgotPassBtn;
    private Button registerHereBtn;
    private TextInputLayout nit;
    private TextInputLayout pass;
    private Button updateDataBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_store);

        forgotPassBtn = findViewById(R.id.forgotPass);
        registerHereBtn = findViewById(R.id.registerHereStore);
        nit = findViewById(R.id.nitTF);
        pass = findViewById(R.id.passwordTF);
        updateDataBtn = findViewById(R.id.update_dataBtn);

        controller = new LoginStoreController(this);


    }

    public Button getForgotPassBtn() {
        return forgotPassBtn;
    }

    public Button getRegisterHereBtn() {
        return registerHereBtn;
    }

    public TextInputLayout getNit() {
        return nit;
    }

    public TextInputLayout getPass() {
        return pass;
    }

    public Button getUpdateDataBtn() {
        return updateDataBtn;
    }
}