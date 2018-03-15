package com.example.rodrigofg.unasus_selecao2.Controle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rodrigofg.unasus_selecao2.Modelo.Usuario;
import com.example.rodrigofg.unasus_selecao2.R;

import java.util.ArrayList;
import java.util.List;

public class SingUpActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    Button btnSingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Sign up");

        /*INSTÂNCIAS*/
        txtEmail = (EditText) findViewById(R.id.txtEmailSignUp);
        txtPassword = (EditText) findViewById(R.id.txtPasswordSignUp);
        btnSingUp = (Button) findViewById(R.id.btnSignUp);

    }

    public void btnSignUpClick(View view){
        efetuarSignUp();
    }

    private void efetuarSignUp(){

        if(verificarCampos()){
            if(atualizarRegistro(criarUsuario()))
                resetarCampos();
        }else{
            mostrarMsg("Insert the information to sign up!");
        }
    }

    private void resetarCampos() {
        txtEmail.setText("");
        txtPassword.setText("");
        mostrarMsg("Sucess!");
    }

    private boolean atualizarRegistro(Usuario usuario){

        List<Usuario> listaUsuarios = Usuario.buscarUsuariosCadastrados(this);

        if(verificaExistencia(usuario, listaUsuarios)){
            mostrarMsg("This user already exists, type another email aderess!");
            return false;
        }

        if(listaUsuarios == null) {
            listaUsuarios = new ArrayList<Usuario>();
            listaUsuarios.add(usuario);
        }
        else{
            listaUsuarios.add(usuario);
        }

        Usuario.atualizarUsuariosCadastrados(this,listaUsuarios);
        return true;
    }

    private boolean verificaExistencia(Usuario novoUsuario, List<Usuario> listaUsuarios) {

        for (Usuario usuarioCadastrado : listaUsuarios) {
            if(usuarioCadastrado.getEmail().equals(novoUsuario.getEmail()))
                return true;
        }
        return false;
    }


    private Usuario criarUsuario(){

            Usuario usuario = new Usuario();
            usuario.setEmail(txtEmail.getText().toString());
            usuario.setPassword(txtPassword.getText().toString());
            return usuario;
    }

    private boolean verificarCampos() {
        if(!(txtEmail.getText().toString().equals("") || txtPassword.getText().toString().equals("")))
            return true;
        else
            return false;
    }

    private void mostrarMsg(String mensagem) {
        new AlertDialog.Builder(this).setTitle("Attention!").setMessage(mensagem).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).create().show();
    }

}
