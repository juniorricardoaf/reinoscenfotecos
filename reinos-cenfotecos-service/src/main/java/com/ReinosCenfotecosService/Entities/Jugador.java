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
public class Jugador {

    private int id;
    private String nombre;
    private int vidaCastillo;
    private int row;//del castillo
    private int column;//del castillo
    private Cofre cofre;//falsta cmabiar el tipo de 2
    private int color;//1=blanco 2=rojo
    private ArrayList<Personaje> personajesEnJuego;

    public Jugador() {
        this.personajesEnJuego = new ArrayList<>();
    }

    public Jugador(int id, String nombre, int vidaCastillo, int row, int column, int color, Cofre cofre) {
        this.id = id;
        this.nombre = nombre;
        this.vidaCastillo = vidaCastillo;
        this.row = row;
        this.column = column;
        this.cofre = cofre;
        this.color = color;
        this.personajesEnJuego = new ArrayList<>();
    }

    public Jugador(int id, String nombre) {//metodo usado solo por jason
        this.id = id;
        this.nombre = nombre;
        this.vidaCastillo = 4;
        this.personajesEnJuego = new ArrayList<>();
    }

    public void agregarPersonajealistadeJugador(Personaje newPersonaje) {//metodo usado por jaason
        personajesEnJuego.add(newPersonaje);
    }

    public ArrayList<Personaje> getPersonajesEnJuego() {
        return personajesEnJuego;
    }

    public void setPersonajesEnJuego(ArrayList<Personaje> personajesEnJuego) {
        this.personajesEnJuego = personajesEnJuego;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getVidaCastillo() {
        return vidaCastillo;
    }

    public void setVidaCastillo(int vidaCastillo) {
        this.vidaCastillo = vidaCastillo;
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

    public Cofre getCofre() {
        return cofre;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }

    public void addPJtoListPlayer(Personaje objPersonaje) {
        this.personajesEnJuego.add(objPersonaje);
    }

    public Jugador clone() {
        Cofre cofre = new Cofre();
        cofre.setListaComposicion(new ArrayList<ICofre>());
        cofre.setId(this.cofre.getId());

        return new Jugador(this.id, this.nombre, this.vidaCastillo, this.row, this.column, this.color, cofre);
    }

}
