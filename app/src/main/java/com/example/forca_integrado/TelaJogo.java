package com.example.forca_integrado;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class TelaJogo extends AppCompatActivity implements View.OnClickListener  {
    private Button b1, btDica;

    private ImageView imagem;

    private ArrayList <Integer> listaImagens, listaIdsButtons;
    //lista de inteiros (imagens == numero)

    private ArrayList <String> listaPalavras;

    private int indiceListaImagens, contaAcerto, contaErro;

    private TextView texto, txAcerto, txErro;

    private String palavra, nivelAtual, dica; //String --> aspas dupla

    private char[] estado;
    //ajudar a monitoriar o jogo --> monitoriar qual letra ja foi descoberta <--
    private int vitoria;
    private BD bd;

    @SuppressLint("MissingInflatedId")
    @Override
    //___________________________________________________________________________________________________________________________________________________________________


    //metodo onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_jogo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nivelAtual = "FACIL";
        bd = new BD(this);

        imagem = findViewById(R.id.imageView2);
        indiceListaImagens = -1;

        //imagens do bonequinho da forca
        imagem = findViewById(R.id.imageView2);
        btDica = findViewById(R.id.btDica);
        txAcerto =findViewById(R.id.txAcerto);
        txErro = findViewById(R.id.textView4);
        contaAcerto = 0;
        contaErro = 0;
        indiceListaImagens = 0;
        dica = "";
        listaImagens= new ArrayList<Integer>();
        listaImagens.add(R.drawable.forca_1_9);
        listaImagens.add(R.drawable.forca_2_9);
        listaImagens.add(R.drawable.forca_3_9);
        listaImagens.add(R.drawable.forca_4_9);
        listaImagens.add(R.drawable.forca_5_9);
        listaImagens.add(R.drawable.forca_6_9);
        listaImagens.add(R.drawable.forca_7_9);
        listaImagens.add(R.drawable.forca_9_9);
        listaImagens.add(R.drawable.forca_10_9);
        listaImagens.add(R.drawable.forca_11_9);


        //palavras que sera sorteadas
        listaPalavras = new ArrayList<String>();
        listaPalavras.add("QUIMICA");
        listaPalavras.add("PARALELEPIPEDO");
        listaPalavras.add("CARRO");
        listaPalavras.add("BANANA");
        listaPalavras.add("MUNICIPIO");
        listaPalavras.add("TECLADO");
        listaPalavras.add("DRAGAO");
        listaPalavras.add("MACARRAO");
        listaPalavras.add("CELULAR");
        listaPalavras.add("MESA");
        listaPalavras.add("MATEMATICA");
        listaPalavras.add("OCEANO");
        listaPalavras.add("NEBULOSA");
        listaPalavras.add("ASTRONALTA");
        listaPalavras.add("GALAXIA");


        //serve para conectar um componente visual (widget) definido no arquivo XML de layout a uma variável no código Java
        texto = findViewById(R.id.textView3);


        //fazer o teclado
        listaIdsButtons = new ArrayList<Integer>();
        listaIdsButtons.add(R.id.id1);
        listaIdsButtons.add(R.id.id2);
        listaIdsButtons.add(R.id.id3);
        listaIdsButtons.add(R.id.id4);
        listaIdsButtons.add(R.id.id5);
        listaIdsButtons.add(R.id.id6);
        listaIdsButtons.add(R.id.id7);
        listaIdsButtons.add(R.id.id8);
        listaIdsButtons.add(R.id.id9);
        listaIdsButtons.add(R.id.id10);
        listaIdsButtons.add(R.id.id11);
        listaIdsButtons.add(R.id.id12);
        listaIdsButtons.add(R.id.id13);
        listaIdsButtons.add(R.id.id14);
        listaIdsButtons.add(R.id.id15);
        listaIdsButtons.add(R.id.id16);
        listaIdsButtons.add(R.id.id17);
        listaIdsButtons.add(R.id.id18);
        listaIdsButtons.add(R.id.id19);
        listaIdsButtons.add(R.id.id20);
        listaIdsButtons.add(R.id.id21);
        listaIdsButtons.add(R.id.id22);
        listaIdsButtons.add(R.id.id23);
        listaIdsButtons.add(R.id.id24);
        listaIdsButtons.add(R.id.id25);
        listaIdsButtons.add(R.id.id26);

        // simples 'for' para colocar acao de toque nos botoes
        for (int j = 0; j<listaIdsButtons.size(); j++){
            Button b = findViewById(listaIdsButtons.get(j));
            b.setOnClickListener(this);
        }

        inicializaJogo(nivelAtual);

    } //_________________________________↓ FIM DO METODO onCreate ↓____________________________________________________________________________________________________________



    //iniciazar o jogo novamente depois de uma partida
    public void inicializaJogo(String nivel) {
        ArrayList<Palavra> lista = new ArrayList<>();
        indiceListaImagens = 0;
        if(nivel.compareToIgnoreCase("FACIL")==0){
            lista = bd.ListarPalavrasFacil();
            Collections.shuffle(lista);
            palavra = lista.get(indiceListaImagens).getPalavraDigitada();
            dica = lista.get(indiceListaImagens).getDica();
        }

        ArrayList<Palavra> listaMedio = new ArrayList<>();
        indiceListaImagens =0;
        if(nivel.compareToIgnoreCase("MEDIO")==0){
            listaMedio = bd.ListarPalavrasFacil();
            Collections.shuffle(listaMedio);
            palavra = listaMedio.get(indiceListaImagens).getPalavraDigitada();
            dica = listaMedio.get(indiceListaImagens).getDica();
        }

        ArrayList<Palavra> listaDificil = new ArrayList<>();
        indiceListaImagens =0;
        if(nivel.compareToIgnoreCase("DIFICIL")==0){
            listaDificil = bd.ListarPalavrasFacil();
            Collections.shuffle(listaDificil);
            palavra = listaDificil.get(indiceListaImagens).getPalavraDigitada();
            dica = listaDificil.get(indiceListaImagens).getDica();
        }


        imagem.setImageResource(R.drawable.forca_0_9);                //chamar a imagem
        indiceListaImagens = 0;
        palavra = sorteiaPalavra();                                   //sortear a palavra
        estado = new char[palavra.length()];                          //chamar o estado
        for(int i=0; i<estado.length; i++){                           //interação
            estado[i] = '_';                                          //char: aspas simples
        }
        contaAcerto = 0;
        contaErro = 0;
        txAcerto.setText(Integer.toString(contaAcerto));                                             //valor da variavel sendo exibida
        txErro.setText(Integer.toString(contaErro)+"/"+Integer.toString(listaImagens.size()));       //valor dos erros sendo exibida
        atualizaTexto();
        for (int j = 0; j<listaIdsButtons.size(); j++){
            Button b = findViewById(listaIdsButtons.get(j));
            b.setEnabled(true);
        }
    }//____________________________________________↓ METODO CHECA SE TERMINOU ↓_______________________________________________________________________________________________________________________

       public void checaSeTerminou(){
        boolean verifica = false;
        for (int i = 0; i<estado.length; i++){
            if(estado [i]=='_'){
                //↓se der true, é pq aindatem jogo↓
                verifica = true;
            }
        }
        //se o verifica estiver false significa que o usuario ganhou


           if (!verifica){//variavel boolean verifica - false e true =====>> if(verifica==true) => if(verifica) (igual)
               NivelVitoria();
               AlertDialog.Builder caixa = new AlertDialog.Builder(this);
               caixa.setTitle("Você Venceu!");
               caixa.setMessage("Deseja jogar novamente?");
               caixa.setPositiveButton("jogar", new DialogInterface.OnClickListener() {

                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       vitoria++;
                       NivelVitoria();
                   }
               });
               caixa.show();

           }
           if (contaErro >= listaImagens.size())
           {
               AlertDialog.Builder caixa = new AlertDialog.Builder(this);
               caixa.setTitle("Você perdeu e não sobrou nada p betinha!");
               caixa.setMessage("Deseja jogar novamente?");
               caixa.setPositiveButton("jogar", new DialogInterface.OnClickListener() {

                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       NivelVitoria();
                   }
               });
               caixa.show();

           }


    }//__________________________________________↓ METODO VETIFICA LETRA ↓_________________________________________________________________________________________________________________________
    //voltar dps
    public void NivelVitoria(){
        if(vitoria<=3){
            nivelAtual = "FACIL";
        }

        if(vitoria>=4 && vitoria <=6){
            nivelAtual = "MEDIO";
        }

        else if(vitoria >6){
            nivelAtual = "DIFICIL";

        }
        inicializaJogo(nivelAtual);
    }
      //verifica se a letra esta na palavra
        public void verificaLetra(char c){
        boolean status = false;                           //começa falso
        for (int i =0; i<palavra.length(); i++)     {     //roda a palavra toda
            if (palavra.charAt(i)==c){                   //compara com o char c
                status = true;                           //passa para verdadeiro
                estado[i] = c;
    }
        }
        if (!status){           //verifica se erro --> se errou... <--
            atualizaForca();
            contaErro++;
            if(contaErro>= 2){
                btDica.setVisibility(VISIBLE);
                btDica.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(TelaJogo.this, dica, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            txErro.setText(Integer.toString(contaErro)+"/"+Integer.toString(listaImagens.size()));       //valor dos --> erros <--- sendo exibida
        }
        else {                //--> se não errou... <-- ou seja, acertou.
            atualizaTexto();
            contaAcerto++;
            txAcerto.setText(Integer.toString(contaAcerto));                                             //valor da variavel --> acerto <-- sendo exibida
        }
        checaSeTerminou();

        }//________________________________________↓ METODO ATUALIZA TEXTO ↓___________________________________________________________________________________________________________________________


    // explicação do metodo: palavra sorteada tem 4 letras, por ex, entao tera 4 '_'
    //adiciona o espaço entre os '_'
    public void atualizaTexto(){
        String temporaria = new String();
        temporaria="";
        for (int i = 0; i<estado.length; i++){
            temporaria+= estado[i] + "";
        }
        texto.setText(temporaria);
    }//_______________________________________________↓METODO SORTEIA PALAVRAS↓____________________________________________________________________________________________________________________


    //sortear as palavras
    public String sorteiaPalavra(){
        String retorno = new String();
        Collections.shuffle(listaPalavras);
        retorno = listaPalavras.get(0);
        return retorno;
    }//_______________________________________________↓ METODO ATUALIZA FORCA ↓____________________________________________________________________________________________________________________


    //enforcar o bonequinho
    public void atualizaForca(){
        imagem.setImageResource(listaImagens.get(indiceListaImagens));
        indiceListaImagens++;
    }//___________________________________________________________________________________________________________________________________________________________________


    @Override
    public void onClick(View view) {                                                   //herança: classe -> view(ou V, no meu caso) (classe mae --> dentro tem classe filhas)
       Button b = (Button) view;                                                       //entre parentese, estou forçando ele a ser um Button
       verificaLetra(b.getText().toString().charAt(0));                                //pegando o texto o texto que ta no botao - transformando em string -
       b.setEnabled(false);}                                                           //o usuario ja clicou no botao, estao irei desativar essa letra. vai estar mostrando que ja chutou essa letra





    //_________________________________TEMPORARIOS____________________________________________________________________________________________________________________________




      // texto.setText(b.getText().toString());    //so pra ver se a ação de toque esta funcionando
      //  texto.setText(sorteiaPalavra()); //sortear a palavra *temporariamente*


}//___________________________________________________________________________________________________________________________________________________________________
