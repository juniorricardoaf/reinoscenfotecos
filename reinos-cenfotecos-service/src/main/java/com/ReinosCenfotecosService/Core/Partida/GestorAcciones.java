/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Core.Personajes.GestorPersonajes;
import com.ReinosCenfotecosService.Entities.Casilla;
import com.ReinosCenfotecosService.Entities.Partida;
import com.ReinosCenfotecosService.Entities.DataInvocar;
import com.ReinosCenfotecosService.Entities.Personaje;
import com.ReinosCenfotecosService.exceptions.BussinessException;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author jorge
 */
public class GestorAcciones {

    // GestorPersonajes gpersonajes = new GestorPersonajes();
    GestorPartidas gPartidas = new GestorPartidas();

    public Partida actualizarTableroInvocar(DataInvocar data) throws Exception {//solo actualiza las casillas
        try {
            Partida partida = gPartidas.obtenerPartidaById(data.getIdPartida());

            if (partida == null) {
                throw new BussinessException(300);
            } else {
                // int index;
                //Personaje pj = gpersonajes.CrearPersonaje(data.getIdJugador());

                ArrayList<Casilla> casillas = new ArrayList<Casilla>();

                //Personaje pj = gpersonajes.CrearPersonaje(data.getIdJugador());
                for (Casilla cas : data.getCasillasModificadas()) {
                    ArrayList<Casilla> fila;
                    Casilla obj;
                    fila = partida.getTablero().getCasillas().get(cas.getRow());

                    if (!fila.isEmpty()) {
                        obj = fila.get(cas.getColumn());
                        if (obj != null) {
                            if (obj.isFilled()) {
                                // validacion para no poder remplazar el color de una casilla  no se pueda cambiar si ya estaba llena
                                cas.setJugador(obj.getJugador());
                                fila.set(cas.getColumn(), cas);
                            } else {
                                fila.set(cas.getColumn(), cas);
                            }

                        }

                    }
                }

            }

        } catch (BussinessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

}
