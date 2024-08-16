package com.ReinosCenfotecosService.Core.Observador;

import com.ReinosCenfotecosService.Entities.Partida;
import java.util.HashMap;


public class Gestor_Observador {
    private HashMap<String, SujetoC> ListaProductos;

    public Gestor_Observador() {
        ListaProductos = new HashMap<String, SujetoC>();
    }

    public void NuevoProducto(String pKey) {
        ListaProductos.put(pKey, new SujetoC(pKey));
    }

    public void NuevoObservador(String pObservador, String pProducto) {
        ListaProductos.get(pProducto).addObserver(new ObservadorC(pObservador));
    }

    public Partida NuevoValor(String pProducto, Partida pValor) {
        return ListaProductos.get(pProducto).setState2(pValor);
    }
}
