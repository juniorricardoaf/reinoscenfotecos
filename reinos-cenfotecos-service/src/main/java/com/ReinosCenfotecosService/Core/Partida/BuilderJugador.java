/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Entities.Cofre;
import com.ReinosCenfotecosService.Entities.Jugador;

/**
 *
 * @author jorge
 */
public abstract class BuilderJugador {
        
    protected Jugador obj;
    public void crearNuevo() {
        this.obj = new Jugador();
    }
    
    public Jugador getObjeto() {
        return this.obj;
    }

    // Métodos que deberán ser construídos por las clases que hereden de ésta
    public abstract void construir(int id,String nombre, int vidaCastillo, int row, int column, int color, Cofre cofre);

}
