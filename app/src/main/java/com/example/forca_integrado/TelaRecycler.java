package com.example.forca_integrado;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TelaRecycler extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_recycler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //---------------------------------------------------------------------------------------------------------------------------------

        bd = new BD(TelaRecycler.this);
        Adaptador adaptador = new Adaptador(bd.listarPalavra());       //estancio o adaptador e pego uma lista de palavra que esta vindo do proprio banco de dados➣

        recyclerView = findViewById(R.id.id_recycler);   //espelhamos um RecycçerView
        RecyclerView.LayoutManager maneger = new LinearLayoutManager(this); //Fizemos um meneger de como os dados vao ser organizador
        recyclerView.setLayoutManager(maneger);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adaptador);  //➣mando a lista vde palavras pegas do banco de dados e mando para o adaptador
    }
}