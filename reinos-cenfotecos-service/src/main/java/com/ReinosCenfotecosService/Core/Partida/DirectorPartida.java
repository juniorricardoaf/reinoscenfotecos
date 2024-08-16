/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Entities.Casilla;
import com.ReinosCenfotecosService.Entities.Jugador;
import com.ReinosCenfotecosService.Entities.Tablero;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jorge
 */
public class DirectorPartida {

    private BuilderPartida builderPartida;
    private BuilderJugador builderJugador;
    private BuilderCofre builderCofre;
    private BuilderTablero builderTablero;

    public DirectorPartida() {
    }

    public void construirPartida(int id, Jugador jugador1, Jugador jugador2, int idTablero,int turno) {
        builderPartida.crearNuevo();
        construirTablero(idTablero, id);
        builderPartida.construir(id, jugador1, jugador2, getBuilderTablero().getObjeto(), 0, new Date().toString(), 1, 0, turno);

    }

    public void construirTablero(int id, int idPartida) {
        builderTablero.crearNuevo();
        builderTablero.construir(id, idPartida);
        ArrayList<ArrayList<Casilla>> casillas = new ArrayList< ArrayList<Casilla>>();
        for (int i = 0; i < 20; i++) {
            casillas.add(new ArrayList<Casilla>());
            for (int j = 0; j < 20; j++) {
                casillas.get(i).add(new Casilla(Integer.parseInt(i + "" + j + "" + idPartida), id, i, j, null, 0, false));
            }
        }
        builderTablero.construirConCasillas(casillas);
    }
    public void construirCofre() {
        builderCofre.crearNuevo();
        builderCofre.construir(); 
    }

    public void construirJugador(int id, String nombre, int row, int column, int color) {
        builderJugador.crearNuevo();
        construirCofre();
        builderJugador.construir(id, nombre, 3, row, column,color, builderCofre.getObjeto());// aqui falta el cofre  builderCofre.getObjeto();
    }

    public BuilderPartida getBuilderPartida() {
        return builderPartida;
    }

    public void setBuilderPartida(BuilderPartida builderPartida) {
        this.builderPartida = builderPartida;
    }

    public BuilderJugador getBuilderJugador() {
        return builderJugador;
    }

    public void setBuilderJugador(BuilderJugador builderJugador) {
        this.builderJugador = builderJugador;
    }

    public BuilderTablero getBuilderTablero() {
        return builderTablero;
    }

    public void setBuilderTablero(BuilderTablero builderTablero) {
        this.builderTablero = builderTablero;
    }

    public BuilderCofre getBuilderCofre() {
        return builderCofre;
    }

    public void setBuilderCofre(BuilderCofre builderCofre) {
        this.builderCofre = builderCofre;
    }

}
