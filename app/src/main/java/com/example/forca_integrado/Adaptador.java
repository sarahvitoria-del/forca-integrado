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

    //--------------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull RecyHolder holder, int position) {


    }

    //
    @Override
    public int getItemCount() {
        return 0;
    }
}
