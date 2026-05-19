package com.example.forca_integrado;

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
                                                                               //↓especifico para criar um metodo para Radio Group
public class Tela3 extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
          //TELA DE CADASTRO DE PALAVRAS
    private EditText textoDaPalavra;
    private Button btnCadastra, btnListar;
    private String categoriaSelecionada, palavra;
    private RadioGroup grupo;
    private BD bd;



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
        textoDaPalavra = findViewById(R.id.textPalavra);
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
            RadioButton r4 = findViewById(R.id.radioButton5);                                                     //alimento

            //**************************************************************
            boolean temRadioChecado = false; //<--boolean começa false
            if (r.isChecked() || r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked()){
                temRadioChecado = true;                //se um dos 5 tiver selecionado ==> boolean muda para true

            }else{
                Toast.makeText(this, "Marque uma categoria para continuar.", Toast.LENGTH_SHORT).show(); //senao tem caixinha selecionada ==> joga esse aviso para o usuario
            }
            if (temTextoDigitado && temRadioChecado){
                //aqui pode salvar no BD
                Palavra palavra1 = new Palavra();
                palavra1.setPalavraDigitada(texto);
                bd.salvarPalavra(palavra1);
            }
            //**************************************************************

        }

        if (view == btnListar){

        }

    }

    //_________________________________________↓METODO RADIO GRUPO (onCheckedChanged)↓________________________________________________________________________________________________________

    @Override    //RadioGroup --> nome da classe ↓
    public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {  //i o prorio radio button que foi clicado
        if (radioGroup == grupo){                                          //verifica se o "radioGroup" é igual o MEU radio group ( que nomeamos --> grupo <--)
            RadioButton temporario = findViewById(i);


            Toast.makeText(Tela3.this, temporario.getText().toString(), Toast.LENGTH_SHORT).show(); //temporario
        }

    }
}