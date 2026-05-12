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

    private EditText textoDaPalavra;
    private Button btnCadastra, btnListar;
    private String categoriaSelecionada, palavra;
    private RadioGroup grupo;



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

        }
        if (view == btnListar){

        }

    }

    //_________________________________________↓METODO RADIO GRUPO (onCheckedChanged)↓__________________________________________________________________________________________________________________________

    @Override    //RadioGroup --> nome da classe ↓
    public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {  //i o prorio radio button que foi clicado
        if (radioGroup == grupo){                                          //verifica se o "radioGroup" é igual o MEU radio group ( que nomeamos --> grupo <--)
            RadioButton temporario = findViewById(i);


            Toast.makeText(Tela3.this, temporario.getText().toString(), Toast.LENGTH_SHORT).show(); //temporario
        }

    }
}