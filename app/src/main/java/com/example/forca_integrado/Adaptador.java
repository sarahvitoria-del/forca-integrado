package com.example.forca_integrado;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<RecyHolder> {
    private ArrayList<Palavra> lista;

    @NonNull
    @Override
    public RecyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //oncreat vai pegar o layout vai inflar
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false); /* LayoutInflater ==> inflar layout dentro de outro layout*/
        return new RecyHolder(view);
    }
    //----------------------------METODO construtor----------------------------------------------------------------------

    public Adaptador(ArrayList<Palavra> lista) {
        this.lista = lista;

    }

    //----------------------------METODO onBindViewHolder----------------------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull RecyHolder holder, int position) {
        holder.txPalavra.setText(lista.get(position).getPalavraDigitada());
        holder.txCategroia.setText(lista.get(position).getCategoria());



    }

    //----------------------------METODO getItemCount----------------------------------------------------------------------
    @Override
    public int getItemCount() {
        return lista.size();
    }
}
