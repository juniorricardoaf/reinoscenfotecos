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
 * @author guiss
 */
public class MementoPartida {

    private int id;
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private int duracion; 
    private String fecha;
    private int estado;
    private int ganador;
    private int turno;

    MementoPartida(Partida par) {
        this.id = par.getId();
        this.jugador1 = par.getJugador1();
        this.jugador2 = par.getJugador2();
        this.tablero = par.getTablero();
        this.duracion = par.getDuracion();
        this.fecha = par.getFecha();
        this.estado = par.getEstado();
        this.ganador = par.getGanador();
        this.turno = par.getTurno();
    }

    public MementoPartida(int id, Jugador jugador1, Jugador jugador2, Tablero tablero, int duracion, String fecha, int estado, int ganador, int turno) {
        this.id = id;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = tablero;
        this.duracion = duracion;
        this.fecha = fecha;
        this.estado = estado;
        this.ganador = ganador;
        this.turno = turno;
    }
    
    public Partida getEstadoMemento(){
        
        Partida par = new Partida(id,jugador1.clone(),jugador2.clone(),tablero.clone(),duracion,fecha,estado,ganador,turno);
        return par;
    }
}
