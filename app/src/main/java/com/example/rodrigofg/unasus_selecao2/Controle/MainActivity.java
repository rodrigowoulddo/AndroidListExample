package com.example.rodrigofg.unasus_selecao2.Controle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rodrigofg.unasus_selecao2.Modelo.Usuario;
import com.example.rodrigofg.unasus_selecao2.R;
import com.google.gson.Gson;

import java.util.List;

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

    public void btnSignUpClick(View view){
            abrirTelaSignUp();
    }

    public void btnSignInClick(View view){
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(verificarCredenciais(email, password))
            abrirTelaGroceries();
    }

    private boolean verificarCredenciais(String email, String password) {


        List<Usuario> listaUsuarios = buscarUsuariosCadastrados();

        //COMPARAR COM USUARIO INSERIDO


        return true;
    }

    private List<Usuario> buscarUsuariosCadastrados(){

        // Buscar credenciais no shared preferences
        SharedPreferences sharedPref = this.getSharedPreferences(
                "GROCERIES", Context.MODE_PRIVATE);

        String jsonUsuarios = sharedPref.getString("USUARIOS","0");

        if(jsonUsuarios.equals("0"))
            return null;

        Gson gson = new Gson();
        List<Usuario> listaUsuarios = (List<Usuario>) gson.fromJson(jsonUsuarios, Usuario.class);

        return listaUsuarios;
    }

    private void abrirTelaSignUp() {
        Intent intentSignUp = new Intent(MainActivity.this, SingUpActivity.class);
        startActivity(intentSignUp);
    }

    private void abrirTelaGroceries() {
        Intent intentGroceries = new Intent(MainActivity.this, GroceriesActivity.class);
        startActivity(intentGroceries);
    }


}
