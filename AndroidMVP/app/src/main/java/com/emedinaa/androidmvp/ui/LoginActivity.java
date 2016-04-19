package com.emedinaa.androidmvp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import com.emedinaa.androidmvp.R;
import com.emedinaa.androidmvp.presenter.LoginPresenter;
import com.emedinaa.androidmvp.view.LoginView;


public class LoginActivity extends ActionBarActivity  implements LoginView{

    private static final String TAG = "HomeActivity";
    private EditText eteUsername,etePassword;
    private View btnLogin,vLoading,tviSignIn;

    private String username, password;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter= new LoginPresenter(this);
        initiateUI();
    }

    private void initiateUI() {
        eteUsername = (EditText)findViewById(R.id.eteUsername);
        etePassword = (EditText)findViewById(R.id.etePassword);
        btnLogin = findViewById(R.id.btnLogin);
        vLoading = findViewById(R.id.vLoading);
        tviSignIn = findViewById(R.id.tviSignIn);
        vLoading.setVisibility(View.GONE);
        events();
    }

    private void events() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    login();
                }
            }
        });
        tviSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignIn();
            }
        });
    }

    private boolean validate() {

        username = eteUsername.getText().toString().trim();
        password = etePassword.getText().toString().trim();

        eteUsername.setError(null);
        etePassword.setError(null);
        if(username.isEmpty())
        {
            eteUsername.setError(getString(R.string.msg_ingresar));
            return false;
        }
        if(password.isEmpty())
        {
            etePassword.setError(getString(R.string.msg_ingresar));
            return false;
        }
        return true;
    }

    private void gotoSignIn() {

    }

    private void login()
    {
        showLoading(true);
        loginPresenter.login(username,password);
    }

    @Override
    public void showLoading(boolean state) {
        int visibility= (state)?(View.VISIBLE):(View.GONE);
        vLoading.setVisibility(visibility);
    }

    @Override
    public void onRequestSuccess(Object object) {
        showLoading(false);
        gotoHome();
    }


    @Override
    public void onRequestError(Object object) {
        showLoading(false);
        //TODO mostrar mensaje de Error
    }

    @Override
    public void showLoading() {
        vLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vLoading.setVisibility(View.GONE);
    }

    private void gotoHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return false;
    }
}
