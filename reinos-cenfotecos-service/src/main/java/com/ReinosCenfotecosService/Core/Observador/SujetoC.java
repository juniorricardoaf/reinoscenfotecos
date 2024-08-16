package com.ReinosCenfotecosService.Core.Observador;

import com.ReinosCenfotecosService.Entities.Partida;
import java.util.ArrayList;
import java.util.List;


public class SujetoC implements Sujeto {

    private String Nombre_Producto;
    private List<Observador> observers = new ArrayList<Observador>();
    private Partida value;

    public SujetoC(String pNombre_Producto) {
        setNombre_Producto(pNombre_Producto);
    }

    @Override
    public void addObserver(Observador o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observador o) {
    }
    @Override
    public Partida notifyObservers() {
        Partida nueva = new Partida();
        
        for (Observador o : observers) {
          nueva= o.update(this.value, getNombre_Producto());
        }
        return nueva;
    }

    public Partida setState2(Partida value) {
        this.value = value;
        return notifyObservers();
    }

    public Partida getValue() {
        return value;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    } 

    public void setNombre_Producto(String nombre_Producto) {
        Nombre_Producto = nombre_Producto;
    }

}
