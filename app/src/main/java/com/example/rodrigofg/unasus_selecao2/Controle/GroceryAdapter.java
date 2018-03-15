package com.example.rodrigofg.unasus_selecao2.Controle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rodrigofg.unasus_selecao2.Modelo.Grocery;
import com.example.rodrigofg.unasus_selecao2.R;

import java.util.List;

public class GroceryAdapter extends BaseAdapter {

    private final List<Grocery> groceries;
    private final Context context;

    public GroceryAdapter(Context context, List<Grocery> groceries) {
        this.context = context;
        this.groceries = groceries;
    }

    @Override
    public int getCount() {
        return groceries.size();
    }

    @Override
    public Object getItem(int position) {
        return groceries.get(position);
    }

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Grocery grocery = groceries.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null) { //convertView otimiza o carregamento de listas muito grandes
            view = inflater.inflate(R.layout.list_item_grocery, parent, false);
        }

        TextView txtGrocery = view.findViewById(R.id.txtGrocery);
        txtGrocery.setText(grocery.getNome());

        return view;
    }
}
