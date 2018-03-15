package com.example.rodrigofg.unasus_selecao2.Controle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rodrigofg.unasus_selecao2.Modelo.Usuario;
import com.example.rodrigofg.unasus_selecao2.R;

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
        instanciaComponentes();
    }

    private void instanciaComponentes() {
        txtEmail = (EditText) findViewById(R.id.txtEmailSignIn);
        txtPassword = (EditText) findViewById(R.id.txtPasswordSignIn);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (TextView) findViewById(R.id.btnSignUpPage);
    }

    public void btnSignUpClick(View view){
        abrirTelaSignUp();
    }

    public void btnSignInClick(View view) {

        if (verificarCredenciais(criarUsuario())) {
            abrirTelaGroceries();
            resetarCampos();
        }
    }

    private void resetarCampos() {
        txtEmail.setText("");
        txtPassword.setText("");
    }

    private boolean verificarCredenciais(Usuario usuarioLogando) {

        if(usuarioLogando.getEmail().equals("") || usuarioLogando.getPassword().equals("")){
            mostrarMsg("Insert the right information to sign in!");
            return false;
        }

        List<Usuario> listaUsuarios = Usuario.buscarUsuariosCadastrados(this);

        if(listaUsuarios != null ){

            for (Usuario possivelUsuario: listaUsuarios) {
                if(possivelUsuario.getEmail().equals(usuarioLogando.getEmail()) && possivelUsuario.getPassword().equals(usuarioLogando.getPassword()))
                    return true;
            }

            mostrarMsg("The user or password you typed is not correct!");

        }else{
            mostrarMsg("The user or password you typed is not correct!");
        }

        return false;
    }


    private void abrirTelaSignUp() {
        Intent intentSignUp = new Intent(MainActivity.this, SingUpActivity.class);
        startActivity(intentSignUp);
    }

    private void abrirTelaGroceries() {
        Intent intentGroceries = new Intent(MainActivity.this, GroceriesActivity.class);
        startActivity(intentGroceries);
    }

    private Usuario criarUsuario(){

        Usuario usuario = new Usuario();
        usuario.setEmail(txtEmail.getText().toString());
        usuario.setPassword(txtPassword.getText().toString());
        return usuario;
    }

    private void mostrarMsg(String mensagem) {
        new AlertDialog.Builder(this).setTitle("Attention!").setMessage(mensagem).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).create().show();
    }


}
