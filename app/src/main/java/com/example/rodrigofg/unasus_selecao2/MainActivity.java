package com.example.rodrigofg.unasus_selecao2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnSignIn;
    private TextView btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Sign in");

        /*INSTÂNCIAS*/
        txtEmail = (EditText) findViewById(R.id.txtEmailSignIn);
        txtPassword = (EditText) findViewById(R.id.txtPasswordSignIn);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (TextView) findViewById(R.id.btnSignUpPage);

    }

    public void btnSignInClick(View view){

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(verificarCredenciais(email, password))
            abrirTelaSignUp();
    }

    private boolean verificarCredenciais(String email, String password) {

        return true;
    }


    private void abrirTelaSignUp() {
        Intent intentSignUp = new Intent(MainActivity.this, SingUpActivity.class);
        startActivity(intentSignUp);
    }

}
