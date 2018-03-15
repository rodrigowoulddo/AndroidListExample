package com.example.rodrigofg.unasus_selecao2.Modelo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigofg on 14/03/2018.
 */

public class Usuario {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<Usuario> buscarUsuariosCadastrados(Activity activity){

        // Persistência de dados feita em Shared Preferences
        SharedPreferences sharedPref = activity.getSharedPreferences(
                "GROCERIES", Context.MODE_PRIVATE);

        String jsonUsuarios = sharedPref.getString("USUARIOS","0");

        if(jsonUsuarios.equals("0"))
            return null;

        Gson gson = new Gson();
        List<Usuario> listaUsuarios = gson.fromJson(jsonUsuarios, new TypeToken<List<Usuario>>(){}.getType());

        return listaUsuarios;
    }

    public static void atualizarUsuariosCadastrados(Activity activity, List<Usuario> novalistaUsuarios) {

        // Persistência de dados feita em Shared Preferences
        SharedPreferences sharedPref = activity.getSharedPreferences(
                "GROCERIES", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String jsonUsuarios = gson.toJson(novalistaUsuarios);

        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putString("USUARIOS", jsonUsuarios);
        editor.commit();
    }

    }
