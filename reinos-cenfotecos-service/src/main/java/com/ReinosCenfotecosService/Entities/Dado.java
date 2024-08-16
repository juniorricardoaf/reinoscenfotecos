package com.ReinosCenfotecosService.Entities;

import com.ReinosCenfotecosService.Core.Cofre.ICofre;

public class Dado implements ICofre {

    private int id;
    private String tipo;
    private int valor;

    public Dado() {
    }

    public Dado(int id, String tipo, int valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String ejecutar() {
        return this.tipo;
    }

}
