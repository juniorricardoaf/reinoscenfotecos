/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Entities.Jugador; 
import com.ReinosCenfotecosService.Entities.Tablero;

/**
 *
 * @author jorge
 */
public class ConstructorPartida extends BuilderPartida {

    @Override
    public void construir(int id, Jugador jugador1, Jugador jugador2, Tablero tablero, int duracion, String fecha, int estado, int ganador, int turno) {
        this.obj.setId(id);
        this.obj.setJugador1(jugador1);
        this.obj.setJugador2(jugador2);
        this.obj.setTablero(tablero);
        this.obj.setDuracion(duracion);
        this.obj.setFecha(fecha);
        this.obj.setEstado(estado);
        this.obj.setGanador(ganador);
        this.obj.setTurno(turno);
    }
}
