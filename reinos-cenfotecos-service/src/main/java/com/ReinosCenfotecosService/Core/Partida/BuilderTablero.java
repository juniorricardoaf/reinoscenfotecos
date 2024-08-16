/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Entities.Casilla;
import com.ReinosCenfotecosService.Entities.Tablero;
import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public abstract class BuilderTablero {
    
    protected Tablero obj;
    public void crearNuevo() {
        this.obj = new Tablero();
    }
    
    public Tablero getObjeto() {
        return this.obj;
    }

    // Métodos que deberán ser construídos por las clases que hereden de ésta
    public abstract void construir(int id, int idPartida);
    public abstract void construirConCasillas(ArrayList<ArrayList<Casilla>> casillas);
}
