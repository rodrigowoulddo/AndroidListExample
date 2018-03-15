package com.example.rodrigofg.unasus_selecao2.Controle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rodrigofg.unasus_selecao2.Modelo.Grocery;
import com.example.rodrigofg.unasus_selecao2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GroceriesActivity extends AppCompatActivity {

    ListView listaGroceries;
    EditText txtGrocery;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Groceries");
        instanciaComponentes();
        carregarGroceriesCadastradas();
    }

    private void instanciaComponentes() {
        listaGroceries = (ListView) findViewById(R.id.listGroceries);
        txtGrocery = (EditText) findViewById(R.id.txtGrocery);
        btnAdd = (Button) findViewById(R.id.btnAdd);
    }

    public void btnAddClick(View view){
        adicionaGrocery();
    }

    private void carregarGroceriesCadastradas(){

        List<Grocery> groceries = Grocery.buscarGroceriesCadastrados(this);
        Collections.reverse(groceries); // Para mostrar as mais novas primeiro

        GroceryAdapter adapter = new GroceryAdapter(this, groceries);
        listaGroceries.setAdapter(adapter);

    }

    private void adicionaGrocery(){

        Grocery novaGrocery = criarGrocery();

        if(validarGrocery(novaGrocery)){
            ArrayList<Grocery> groceriesCadastradas = (ArrayList<Grocery>) Grocery.buscarGroceriesCadastrados(this);
            groceriesCadastradas.add(novaGrocery);
            Grocery.atualizarGroceriesCadastrados(this,groceriesCadastradas);
            resetarCampos();
            carregarGroceriesCadastradas();
        }
    }

    private void resetarCampos() {
        txtGrocery.setText("");
    }

    private Grocery criarGrocery(){

        Grocery grocery = new Grocery();
        grocery.setNome(txtGrocery.getText().toString());
        return grocery;
    }

    private boolean validarGrocery(Grocery novaGrocery) {

        if(novaGrocery.getNome().equals("")){
            mostrarMsg("Type a name!");
            return false;
        }

        List<Grocery> groceries = Grocery.buscarGroceriesCadastrados(this);

        for (Grocery groceryCadastrada: groceries) {
            if(groceryCadastrada.getNome().equals(novaGrocery.getNome())){
                mostrarMsg("This grocery is already on the database!");
                return false;
            }
        }
        return true;
    }

    public void btnDeletarClick(View view) {

        //Busca o nome da grocery na própria view clicada
        view = (View) view.getParent();
        String nomeGrocery = ((TextView) view.findViewById(R.id.txtGrocery)).getText().toString();

        Grocery grocery = new Grocery();
        grocery.setNome(nomeGrocery);

        removeGrocery(grocery);
    }

    private void removeGrocery(Grocery grocery) {

        ArrayList<Grocery> groceriesCadastradas = (ArrayList<Grocery>) Grocery.buscarGroceriesCadastrados(this);

        for(Iterator<Grocery> iterator = groceriesCadastradas.iterator(); iterator.hasNext(); ) {
            if(iterator.next().getNome().equals(grocery.getNome()))
                iterator.remove();
        }
        Grocery.atualizarGroceriesCadastrados(this, groceriesCadastradas);

        carregarGroceriesCadastradas();
    }

    private void mostrarMsg(String mensagem) {
        new AlertDialog.Builder(this).setTitle("Attention!").setMessage(mensagem).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).create().show();
    }

}
