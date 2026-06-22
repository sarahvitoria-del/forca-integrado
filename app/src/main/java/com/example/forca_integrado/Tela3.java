package com.example.forca_integrado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

//↓especifico para criar um metodo para Radio Group
public class Tela3 extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
          //TELA DE CADASTRO DE PALAVRAS
    private EditText textoDaPalavra, textoDaDica;
    private Button btnCadastra, btnListar;
    private String categoriaSelecionada, palavra, nivel;
    private RadioGroup grupo;
    private BD bd;
    private ArrayList<String> ListaFacil, L, ListaMedio, ListaDificul;



    //_________________________________________↓METODO onCreate↓__________________________________________________________________________________________________________________________

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bd = new BD(Tela3.this);
        textoDaPalavra = findViewById(R.id.txPalavra);
        textoDaDica = findViewById(R.id.txDica);
        btnCadastra = findViewById(R.id.button4);
        btnCadastra.setOnClickListener(this);
        btnListar = findViewById(R.id.button6);
        btnListar.setOnClickListener(this);
        grupo = findViewById(R.id.id_grupo);
        grupo.setOnCheckedChangeListener(this);
    }
    //_________________________________________↓METODO onClick↓__________________________________________________________________________________________________________________________
    @Override
    public void onClick(View view) {
        if (view == btnCadastra){
            String texto = textoDaPalavra.getText().toString();  //cata o texto
            int conta = texto.length();  //.length() ==> ele pega a palavra e conta quantos caracter ela possui
            if(conta <= 4){
                nivel = "FACIL";
            }
            else if (conta > 4 && conta <=7){
                nivel = "MEDIO";
            }
            else{
                nivel = "DIFICIL";
            }
            /*_____________________________________*/

            String dica = textoDaDica.getText().toString();
            boolean temDica=false;
            if (dica != null ){
                temDica = true;
            }


            boolean temTextoDigitado = false;   //boolean temTextoDigitado --> começa como false
            if (texto.isEmpty()){     //isEmpty --> verifica se o usuario digitou uma palavra na caixinha
                Toast.makeText(this, "faltou palavra, FI!", Toast.LENGTH_SHORT).show(); //se texto esta vazio, joga esse aviso para o usuario
            }else {
                temTextoDigitado = true; //senao, muda para true
            }

            //**************--vamos testar os radios--**********************
            RadioButton r = findViewById(R.id.radioButton4);                                                      //esporte
            RadioButton r1 = findViewById(R.id.radioButton);                                                      //pais
            RadioButton r2= findViewById(R.id.radioButton2);                                                      //estado
            RadioButton r3 = findViewById(R.id.radioButton3);                                                     //cidade
            RadioButton r4 = findViewById(R.id.radioButton5);
            RadioButton r5 = findViewById(R.id.radioButton7);
            RadioButton r6 = findViewById(R.id.radioButton6);

            //**************************************************************
            boolean temRadioChecado = false; //<--boolean começa false
            if (r.isChecked() || r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked() || r5.isChecked() || r6.isChecked()){
                temRadioChecado = true;                //se um dos 5 tiver selecionado ==> boolean muda para true

            }else{
                Toast.makeText(this, "Marque uma categoria para continuar.", Toast.LENGTH_SHORT).show(); //senao, tem caixinha selecionada! ==> joga esse aviso para o usuario
            }

            if (temTextoDigitado && temRadioChecado && temDica){
                //aqui pode salvar no BD
                Palavra palavra1 = new Palavra();
                palavra1.setPalavraDigitada(texto);
                palavra1.setDica(dica);
                palavra1.setCategoria(categoriaSelecionada);
                palavra1.setNivel(nivel);

                bd.salvarPalavra(palavra1);
                textoDaPalavra.setText("");
                textoDaDica.setText("");

                Toast.makeText(this,"Salvo", Toast.LENGTH_SHORT).show();
            }
            //**************************************************************

        }

        if (view == btnListar){
            startActivity(new Intent(this, TelaRecycler.class));

            //__________________________parte 6___________________--
            ArrayList<Palavra>L = new ArrayList<Palavra>();
            ArrayList<Palavra>ListarFacil = new ArrayList<Palavra>();
            L = bd.listarPalavra();
            for (int i = 0; i<L.size(); i++){
                if(L.get(i).getNivel().compareToIgnoreCase("FACIL")==0){
                    ListarFacil.add(L.get(i));
                }
            }

            ArrayList<Palavra>ListarMedio = new ArrayList<Palavra>();
            L = bd.listarPalavra();
            for (int i = 0; i<L.size(); i++){
                if(L.get(i).getNivel().compareToIgnoreCase("MEDIO")==0){
                    ListarMedio.add(L.get(i));
                }
            }

            ArrayList<Palavra>ListarDificil = new ArrayList<Palavra>();
            L = bd.listarPalavra();
            for (int i = 0; i<L.size(); i++){
                if(L.get(i).getNivel().compareToIgnoreCase("FACIL")==0){
                    ListarDificil.add(L.get(i));
                }
            }

        }

    }

    /*_________________________________________↓METODO RADIO GRUPO (onCheckedChanged)↓________________________________________________________________________________________________________*/

    @Override    //RadioGroup --> nome da classe ↓
    public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {  //i o prorio radio button que foi clicado
        if (radioGroup == grupo){                                          //verifica se o "radioGroup" é igual o MEU radio group ( que nomeamos --> grupo <--)
            RadioButton temporario = findViewById(i);
            categoriaSelecionada = temporario.getText().toString();

            Toast.makeText(Tela3.this, temporario.getText().toString(), Toast.LENGTH_SHORT).show(); //temporario
        }

    }
}