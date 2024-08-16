package com.ReinosCenfotecosService.Core.Personajes;

import com.ReinosCenfotecosService.Core.Partida.GestorAcciones;
import com.ReinosCenfotecosService.Core.Partida.GestorPartidas;
import com.ReinosCenfotecosService.Entities.Casilla;
import com.ReinosCenfotecosService.Entities.DataInvocar;
import com.ReinosCenfotecosService.Entities.Partida;
import com.ReinosCenfotecosService.Entities.Personaje;
import com.ReinosCenfotecosService.Helper.Helper;
import com.ReinosCenfotecosService.exceptions.BussinessException;

import java.util.ArrayList;

public class RealizarMovimiento extends RealizarAccion {

    @Override
    public void faseDeAccion(Personaje objPersonajeAplica, Personaje objPersonajeAplicar,
            String[] casillas, String accionRealizar,
            Partida partidaActual, GestorAcciones gesAcciones, int jugadorAplica, int jugadorAplicar) throws Exception {
        boolean estadoValidacion = false;
        if (accionRealizar.equals("Mover")) {
            estadoValidacion = validacionMovimiento(casillas);
            if (estadoValidacion == true) {
                int rowMover, colMover;
                int rowMoverOld, colMoverOld;
                rowMover = Integer.parseInt(casillas[1].split("-")[0]);
                colMover = Integer.parseInt(casillas[1].split("-")[1]);

                //casilla vieja
                rowMoverOld = Integer.parseInt(casillas[0].split("-")[0]);
                colMoverOld = Integer.parseInt(casillas[0].split("-")[1]);

                actualizarTableroMovimiento(partidaActual, jugadorAplica, rowMover, colMover, objPersonajeAplicar, gesAcciones, jugadorAplica, rowMoverOld, colMoverOld, null);
            } else {

                throw new BussinessException(508);
            }
        } else {
            if (handler != null) {
                handler.faseDeAccion(objPersonajeAplica, objPersonajeAplicar, casillas, accionRealizar, partidaActual, gesAcciones, jugadorAplica, jugadorAplicar);
            }
        }

    }

    private boolean validacionMovimiento(String[] casillas) {
        boolean estadoValidacion = false;
        int rowActual, colActual;
        int rowMover, colMover;
        rowActual = Integer.parseInt(casillas[0].split("-")[0]);
        colActual = Integer.parseInt(casillas[0].split("-")[1]);
        rowMover = Integer.parseInt(casillas[1].split("-")[0]);
        colMover = Integer.parseInt(casillas[1].split("-")[1]);

        if (rowActual == rowMover) {
            int colIzquierda = colActual - 1;
            int colDerecha = colActual + 1;
            if (colIzquierda == colMover) {
                estadoValidacion = true;
            } else if (colDerecha == colMover) {
                estadoValidacion = true;
            } else {
                estadoValidacion = false;
            }
        } else {
            int rowArriba = rowActual + 1;
            int rowAbajo = rowActual - 1;

            if (rowArriba == rowMover) {
                estadoValidacion = true;
            } else if (rowAbajo == rowMover) {
                estadoValidacion = true;
            } else {
                estadoValidacion = false;
            }
        }
        return estadoValidacion;

    }

    private void actualizarTableroMovimiento(Partida objPartida,
            int jugadorAplica, int newrow, int newcol,
            Personaje objPersonaje, GestorAcciones gesAcciones, int jugadorAplicaOld, int rowMoverOld, int colMoverOld, Personaje objPeronsajeOld) throws Exception {

        Casilla casillaDeInvocacion = new Casilla(Helper.generarIdCasilla(newrow, newcol, objPartida.getId()),
                objPartida.getTablero().getId(), newrow, newcol, objPersonaje, jugadorAplica, true);
        //casilla anterior
        Casilla casillaOrigen = new Casilla(Helper.generarIdCasilla(rowMoverOld, colMoverOld, objPartida.getId()),
                objPartida.getTablero().getId(), rowMoverOld, colMoverOld, objPeronsajeOld, jugadorAplicaOld, true);

        ArrayList<Casilla> casillas = new ArrayList<Casilla>();
        casillas.add(casillaDeInvocacion);
        casillas.add(casillaOrigen);

        DataInvocar datInvocacion = new DataInvocar(objPersonaje.getId(), objPartida.getId(), jugadorAplica, casillas);
        gesAcciones.actualizarTableroInvocar(datInvocacion);
    }
}
