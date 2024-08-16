/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Entities;

/**
 *
 * @author jorge
 */
public class Casilla {

    private int id;
    private int idTablero;
    private int row;
    private int column;
    private Personaje obj;//personaje
    private int jugador;// jugador al que pertenece 0- vacio 1= player1, 2= player 2
    private boolean filled;

    public Casilla() {
    }

    public Casilla(int id, int idTablero, int row, int column, Personaje obj, int jugador, boolean filled) {
        this.id = id;
        this.idTablero = idTablero;
        this.row = row;
        this.column = column;
        this.obj = obj;
        this.jugador = jugador;
        this.filled = filled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(int idTablero) {
        this.idTablero = idTablero;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Personaje getObj() {
        return obj;
    }

    public void setObj(Personaje obj) {
        this.obj = obj;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

}
