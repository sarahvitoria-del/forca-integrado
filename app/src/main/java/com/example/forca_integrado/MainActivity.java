package com.example.forca_integrado;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


                    //--> extends AppCompatActivity <-- significa que a classe te uma tela mobile
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b, b1;

    //____________________________________↓METODO onCreate↓_______________________________________________________________________________________________________________________________

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b = findViewById(R.id.button);
        b.setOnClickListener(this);

        b = findViewById(R.id.button2);
        b.setOnClickListener(this);
    }
    //____________________________________↓METODO onClick↓_______________________________________________________________________________________________________________________________

    @Override
    public void onClick(View view) {
        if (view == b) {                                                       //se o usuario clicar no iniciar, ele vai cair na TelaJogo
            startActivity(new Intent(this, TelaJogo.class));
        }
        if (view == b1) {                                                      //se o usuario clicar no Configurações, ele vai cair na Tela3
            startActivity(new Intent(this, Tela3.class));
        }

    }
}