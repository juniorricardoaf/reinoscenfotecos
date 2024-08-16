/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Entities.Jugador;
import com.ReinosCenfotecosService.Entities.Partida;
import com.ReinosCenfotecosService.Entities.Tablero;

/**
 *
 * @author jorge
 */
public abstract class BuilderPartida {

    protected Partida obj;

    public void crearNuevo() {
        this.obj = new Partida();
    }

    public Partida getObjeto() {
        return this.obj;
    }

    // Métodos que deberán ser construídos por las clases que hereden de ésta
    public abstract void construir(int id, Jugador jugador1, Jugador jugador2, Tablero tablero, int duracion, String fecha, int estado, int ganador, int turno);

}
