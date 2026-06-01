package com.example.forca_integrado;
//lampada amarela - create getter and setter 'nomeDaVariavel' --> cria o metodo get e set sozinho
//↑↑ Fiz isso ↑↑ com 'palavraDigitada' e 'categoria' ↑↑

public class Palavra {
    //declarei as variaveis
    private String categoria, palavraDigitada, dica, nivel;
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

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
