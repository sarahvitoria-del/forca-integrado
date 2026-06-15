package com.example.forca_integrado;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//BD --> banco de dados abreviado
public class BD extends SQLiteOpenHelper {
    private String palavra, categoria, dica, nivel;
    private static final int DATABASE_VERSION = 1;  //estou declarando a versao do meu database que sera 1
    private static final String DATABASE_NAME = "banco1.bd"; //Estou declarando que o nome do meu banco de dados se chama "banco.db"

    public BD(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //____________________________________↓METODO onCreate↓__________------->>>>>>java com limquagem SQL<<<<<<<<<-----------____________________________________________________________________________________
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS tabelaPalavra("+                         //contatenando = quebrando linha. '+' --> somando o conteudo das proximas linhas --> abre parentese para fechar depois la em baixo
                 "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "palavra TEXT,"+
                        "categoria TEXT,"+
                        "dica TEXT,"+
                        "nivel TEXT)"                                           //FECHA AQUI
        );

    }
    //____________________________________↓METODO listar palavra↓________________________________________________________________________________________________________________________________________________

    public ArrayList<Palavra> listarPalavra(){
        ArrayList<Palavra> lista = new ArrayList<Palavra>();
        SQLiteDatabase db = getReadableDatabase(); //Read --> poder de leitura
        Cursor cursor = db.query("tabelaPalavra", null, null, null, null, null, null);
        while (cursor.moveToNext()){ //enquanto ele poder mover para o procimo o while vai ficar rodando

             palavra = cursor.getString(cursor.getColumnIndexOrThrow("palavra")).toUpperCase();
             categoria = cursor.getString(cursor.getColumnIndexOrThrow("categoria"));
             dica = cursor.getString(cursor.getColumnIndexOrThrow("dica"));
             nivel = cursor.getString(cursor.getColumnIndexOrThrow("nivel"));

            Palavra p = new Palavra();       //possivel erro
            p.setPalavraDigitada(palavra);   //possivel erro
            p.setCategoria(categoria);       //possivel erro
            p.setDica(dica);
            p.setNivel(nivel);
            lista.add(p);
        }
        cursor.close();     //esta fechando
        db.close();
        return lista;
    }

    public ArrayList<Palavra>ListarPalavrasFacil(){
        ArrayList<Palavra> listaGeral = new ArrayList<Palavra>();
        ArrayList<Palavra> lista = new ArrayList<Palavra>();
        listaGeral = listarPalavra();

        for (int i = 0; i<lista.size(); i++){
            if(listaGeral.get(i).getNivel().compareToIgnoreCase("FACIL")==0){
                lista.add(listaGeral.get(i));
            }
        }
        return listaGeral;
    }

    public ArrayList<Palavra>ListarPalavrasMedio(){
        ArrayList<Palavra> listaGeral = new ArrayList<Palavra>();
        ArrayList<Palavra> listaMedio = new ArrayList<Palavra>();
        listaGeral = listarPalavra();

        for (int i = 0; i<listaMedio.size(); i++){
            if(listaGeral.get(i).getNivel().compareToIgnoreCase("MEDIO")==0){
                listaMedio.add(listaGeral.get(i));
            }
        }
        return listaGeral;
    }

    public ArrayList<Palavra>ListarPalavrasDificil(){
        ArrayList<Palavra> listaGeral = new ArrayList<Palavra>();
        ArrayList<Palavra> listaDificil = new ArrayList<Palavra>();
        listaGeral = listarPalavra();

        for (int i = 0; i<listaDificil.size(); i++){
            if(listaGeral.get(i).getNivel().compareToIgnoreCase("DIFICIL")==0){
                listaDificil.add(listaGeral.get(i));
            }
        }
        return listaGeral;
    }



    //____________________________________↓METODO salvar palavra↓________________________________________________________________________________________________________________________________________________

    public void salvarPalavra(Palavra p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("palavra", p.getPalavraDigitada());
        valores.put("categoria", p.getCategoria());
        valores.put("dica", p.getDica());
        valores.put("nivel", p.getNivel());
        db.insert("tabelaPalavra", null, valores);
        db.close();
    }

    //____________________________________↓METODO onUpgrade↓____________________________________________________________________________________________________________________________________________________
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
