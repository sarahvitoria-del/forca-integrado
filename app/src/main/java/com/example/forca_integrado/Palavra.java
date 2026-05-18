package com.example.forca_integrado;
//lampada amarela - create getter and setter 'nomeDaVariavel' --> cria o metodo get e set sozinho
//↑↑ Fiz isso ↑↑ com 'palavraDigitada' e 'categoria' ↑↑

public class Palavra {
    //declarei as variaveis
    private String palavraDigitada, categoria;
    //____________________________________↓METODO getter de palavraDigitada↓_______________________________________________________________________________________________________________________________

    public String getPalavraDigitada() { //
        return palavraDigitada;
    }
    //____________________________________↓METODO setter de palavraDigitada↓_______________________________________________________________________________________________________________________________

    public void setPalavraDigitada(String palavraDigitada) {
        this.palavraDigitada = palavraDigitada;
    }
    //____________________________________↓METODO getter de categotia↓_______________________________________________________________________________________________________________________________

    public String getCategoria() {
        return categoria;
    }
    //____________________________________↓METODO setter de catefotia↓_______________________________________________________________________________________________________________________________

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
