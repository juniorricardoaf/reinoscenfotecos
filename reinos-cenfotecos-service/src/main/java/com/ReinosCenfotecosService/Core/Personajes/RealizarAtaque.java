package com.ReinosCenfotecosService.Core.Personajes;

import com.ReinosCenfotecosService.Core.Partida.GestorAcciones;
import com.ReinosCenfotecosService.Entities.Casilla;
import com.ReinosCenfotecosService.Entities.DataInvocar;
import com.ReinosCenfotecosService.Entities.Partida;
import com.ReinosCenfotecosService.Entities.Personaje;
import com.ReinosCenfotecosService.Helper.Helper;
import com.ReinosCenfotecosService.exceptions.BussinessException;

import java.util.ArrayList;

public class RealizarAtaque extends RealizarAccion {

    @Override
    public void faseDeAccion(Personaje objPersonajeAplica, Personaje objPersonajeAplicar, String[] casillas, String accionRealizar, Partida partidaActual, GestorAcciones gesAcciones, int jugadorAplica, int jugadorAplicar) throws Exception {
        boolean estadoValidacion = false;
        boolean estadosAtaque = false;
        if (accionRealizar.equals("Atacar")) {
            estadoValidacion = validacionMovimiento(casillas);
            if (estadoValidacion) {
                int rowAtacado, colAtacado;


                if (objPersonajeAplicar.getDefensa() >= objPersonajeAplica.getAtaque()) {
                    int defensaAtacado = objPersonajeAplicar.getDefensa();
                    int ataqueAtacante = objPersonajeAplica.getAtaque();
                    defensaAtacado = defensaAtacado - ataqueAtacante;
                    objPersonajeAplicar.setDefensa(defensaAtacado);
                    estadosAtaque = true;
                } else if (objPersonajeAplicar.getDefensa() == objPersonajeAplica.getAtaque()) {
                    objPersonajeAplicar.setDefensa(0);
                    estadosAtaque = true;
                } else if (objPersonajeAplicar.getDefensa() == 0 && estadosAtaque != true) {
                    int vidaAtacado = objPersonajeAplicar.getVida();
                    int ataqueAtacante = objPersonajeAplica.getAtaque();
                    vidaAtacado = vidaAtacado-ataqueAtacante;
                    objPersonajeAplicar.setVida(vidaAtacado);
                    estadosAtaque = true;
                }else if(objPersonajeAplicar.getDefensa() > 0 && estadosAtaque != true){
                    int vidaAtacado = objPersonajeAplicar.getVida();
                    int ataqueAtacante = objPersonajeAplica.getAtaque();
                    int defensaAtacado = objPersonajeAplicar.getDefensa();
                    defensaAtacado = defensaAtacado - ataqueAtacante;
                    if(defensaAtacado < 0){
                        vidaAtacado = vidaAtacado + defensaAtacado;
                        defensaAtacado = 0;
                    }
                    objPersonajeAplicar.setDefensa(defensaAtacado);
                    objPersonajeAplicar.setVida(vidaAtacado);
                }

                rowAtacado = Integer.parseInt(casillas[1].split("-")[0]);
                colAtacado = Integer.parseInt(casillas[1].split("-")[1]);

                if (objPersonajeAplicar.getVida() <= 0) {
                    objPersonajeAplicar = null;
                    actualizarTableroAtaque(partidaActual, jugadorAplicar, rowAtacado, colAtacado, objPersonajeAplicar, gesAcciones);
                } else {
                    actualizarTableroAtaque(partidaActual, jugadorAplicar, rowAtacado, colAtacado, objPersonajeAplicar, gesAcciones);
                }
            }else{
                throw new BussinessException(509);
            }
        } else {
            if (handler != null) {
                handler.faseDeAccion(objPersonajeAplica, objPersonajeAplicar, casillas, accionRealizar, partidaActual, gesAcciones, jugadorAplicar, jugadorAplicar);
            }
        }
    }

    private void actualizarTableroAtaque(Partida partidaActual, int jugadorAplica, int row, int col, Personaje objPersonaje, GestorAcciones gesAcciones) throws Exception {
        Casilla casillaDeInvocacion = new Casilla(Helper.generarIdCasilla(row, col, partidaActual.getId()),
                partidaActual.getTablero().getId(), row, col, objPersonaje, jugadorAplica, true);
        ArrayList<Casilla> casillas = new ArrayList<Casilla>();
        casillas.add(casillaDeInvocacion);
        DataInvocar datInvocacion = null;
        if (objPersonaje == null) {
            datInvocacion = new DataInvocar(0, partidaActual.getId(), jugadorAplica, casillas);
        } else {
            datInvocacion = new DataInvocar(objPersonaje.getId(), partidaActual.getId(), jugadorAplica, casillas);
        }

        gesAcciones.actualizarTableroInvocar(datInvocacion);
    }

    private boolean validacionMovimiento(String[] casillas) {
        boolean estadoValidacion;
        int rowAtacante, colAtacante;
        int rowAtacado, colAtacado;

        rowAtacante = Integer.parseInt(casillas[0].split("-")[0]);
        colAtacante = Integer.parseInt(casillas[0].split("-")[1]);
        rowAtacado = Integer.parseInt(casillas[1].split("-")[0]);
        colAtacado = Integer.parseInt(casillas[1].split("-")[1]);

        if (rowAtacante == rowAtacado) {
            int colIzquierda = colAtacante - 1;
            int colDerecha = colAtacante + 1;
            if (colIzquierda == colAtacado) {
                estadoValidacion = true;
            } else if (colDerecha == colAtacado) {
                estadoValidacion = true;
            } else {
                estadoValidacion = false;
            }
        } else {
            int rowArriba = rowAtacante + 1;
            int rowAbajo = rowAtacante - 1;

            if (rowArriba == rowAtacado) {
                estadoValidacion = true;
            } else if (rowAbajo == rowAtacado) {
                estadoValidacion = true;
            } else {
                estadoValidacion = false;
            }
        }
        return estadoValidacion;

    }
}
