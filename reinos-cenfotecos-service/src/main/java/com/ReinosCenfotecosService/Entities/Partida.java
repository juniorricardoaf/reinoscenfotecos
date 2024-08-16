/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Entities;

import com.ReinosCenfotecosService.Core.Partida.MementoPartida;

/**
 *
 * @author jorge
 */
public class Partida {

    private int id;
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private int duracion; //supongo que seránm milisegundos
    private String fecha;// o deberia ser date?
    private int estado;// estado de la partida, 0=terminado,1=en curso, 2= inconcluso
    private int ganador;//0=sin ganador, 1=ganó jugador1, 2=ganó jugador2, 3=empate?
    private int turno;//determiande quien es el siguiente turno 1=player1 2=pllayer2

    public Partida() {
    }

    public Partida(int id, Jugador jugador1,
            Jugador jugador2, Tablero tablero,
            int duracion, String fecha, int estate,
            int ganador, int turno) {
        this.id = id;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = tablero;
        this.duracion = duracion;
        this.fecha = fecha;
        this.estado = estate;
        this.ganador = ganador;
        this.turno = turno;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public MementoPartida generarMemento(){
        
        return new MementoPartida(id,jugador1.clone(),jugador2.clone(),tablero.clone(),duracion,fecha,estado,ganador,turno);
    }
    
    public void restaurarMemento(MementoPartida mp){
        Partida par =  mp.getEstadoMemento();
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
}
