package com.example.rodrigofg.unasus_selecao2.Modelo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo on 15/03/2018.
 */

public class Grocery {

    private String Nome;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public static List<Grocery> buscarGroceriesCadastrados(Activity activity){

        // Persistência de dados feita em Shared Preferences
        SharedPreferences sharedPref = activity.getSharedPreferences(
                "GROCERIES", Context.MODE_PRIVATE);

        String jsonGroceries = sharedPref.getString("GROCERIES","0");

        if(jsonGroceries.equals("0"))
            return new ArrayList<Grocery>();

        Gson gson = new Gson();
        List<Grocery> listaGroceries = gson.fromJson(jsonGroceries, new TypeToken<List<Grocery>>(){}.getType());

        return listaGroceries;
    }

    public static void atualizarGroceriesCadastrados(Activity activity, List<Grocery> novalistaGroceries) {

        // Persistência de dados feita em Shared Preferences
        SharedPreferences sharedPref = activity.getSharedPreferences(
                "GROCERIES", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String jsonGroceries = gson.toJson(novalistaGroceries);

        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putString("GROCERIES", jsonGroceries);
        editor.commit();
    }

}
