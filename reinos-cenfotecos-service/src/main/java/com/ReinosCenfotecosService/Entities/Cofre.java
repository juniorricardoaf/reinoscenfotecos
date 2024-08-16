/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Entities;

import com.ReinosCenfotecosService.Core.Cofre.ICofre;
import java.util.ArrayList;

/**
 *
 * @author jscru
 */
public class Cofre implements ICofre {

    private ArrayList<ICofre> listaComposicion; //Contiene los dados
    private int id;
    private static int classCounter = 0;

    public Cofre() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ICofre> mostrarDatos() {
        return listaComposicion;
    }

    public void agregarComponente(ICofre composicion) {
        this.listaComposicion.add(composicion);
    }

    public void quitarComponente(String tipo) {
        boolean salir = false;
        int i = 0;
        do {
            ICofre dado = listaComposicion.get(i);
            if (dado instanceof ICofre) {
                Dado da = (Dado) dado;
                if (da.getTipo().equals(tipo)) {
                    this.listaComposicion.remove(i);
                    salir = true;
                } 
            }
            i++;//importanticimo este i

        } while (salir == false);

    }
    
    public void quitarMov(String tipo) {
        boolean salir = false;
        int i = 0;
        do {
            ICofre dado = listaComposicion.get(i);
            if (dado instanceof ICofre) {
                Dado da = (Dado) dado;
                if(da.getTipo().equals(tipo) && da.getValor() == 1){
                    this.listaComposicion.remove(i);
                    salir = true;
                }else if (da.getTipo().equals(tipo)) {
                    da.setValor(da.getValor()-1);
                    this.listaComposicion.set(i, (ICofre)da);
                    salir = true;
                } 
            }
            i++;//importanticimo este i

        } while (salir == false);

    }

    public ArrayList<ICofre> getListaComposicion() {
        return listaComposicion;
    }

    public void setListaComposicion(ArrayList<ICofre> listaComposicion) {
        this.listaComposicion = listaComposicion;
    }

    @Override
    public String ejecutar() {
        String data = "";

        for (ICofre nodo : listaComposicion) {
            data += nodo.ejecutar();
        }
        return data;
    }

}
