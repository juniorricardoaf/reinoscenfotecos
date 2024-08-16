/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Personajes;

import com.ReinosCenfotecosService.Core.Partida.GestorAcciones;
import com.ReinosCenfotecosService.Core.Partida.GestorPartidas;
import com.ReinosCenfotecosService.Entities.Casilla;
import com.ReinosCenfotecosService.Entities.DataInvocar;
import com.ReinosCenfotecosService.Entities.Partida;
import com.ReinosCenfotecosService.Entities.Personaje;
import com.ReinosCenfotecosService.Helper.Helper;
import com.ReinosCenfotecosService.exceptions.BussinessException;
import com.ReinosCenfotecosService.exceptions.ExceptionManager;

import java.util.ArrayList;

/**
 * @author jscru
 */
public class GestorPersonajes {

    private GestorPartidas gesPartidas = new GestorPartidas();
    private GestorAcciones gesAcciones = new GestorAcciones();
    private RealizarAccion handler;

    public GestorPersonajes() {
        iniciarProceso();
    }

    /*####################################Inicio Creación de personajes ##########################################*/
    public Personaje CrearPersonaje(int tpersonaje, int idPartida, int jugador, String[] coordenadas) throws BussinessException, Exception {
        Personaje responseMessage = null;
        try {
            if (tpersonaje < 1 || tpersonaje > 3) {
                throw new BussinessException(500);
            } else {
                FactoryPersonaje factory = new FactoryPersonaje();
                Personaje objPersonaje = factory.crearPersonaje(tpersonaje, jugador);
                responseMessage = objPersonaje;
                asignarPersonajeAJugador(objPersonaje, idPartida, jugador, coordenadas);
            }
        } catch (BussinessException bex) {
            ExceptionManager.GetInstance().Process(bex);
        }
        return responseMessage;
    }

    private void asignarPersonajeAJugador(Personaje objPersonaje, int idPartida, int currectPlayer, String[] coordenadas) throws Exception {
        Partida objPartida = gesPartidas.obtenerPartidaById(idPartida);
        int row, coll;
        int idTablero;
        int i = 0;
        if (objPartida != null) {
            if (currectPlayer == 1) {
                objPartida.getJugador1().addPJtoListPlayer(objPersonaje);
                for (String coord : coordenadas) {
                    String coords[] = coord.split("-");
                    row = Integer.parseInt(coords[0]);
                    coll = Integer.parseInt(coords[1]);
                    if (i == 0) {
                        idTablero = objPartida.getTablero().getId();
                        invocacionPJenTablero(objPersonaje.getId(), idTablero, currectPlayer, row, coll, objPersonaje, idPartida);
                    } else {
                        idTablero = objPartida.getTablero().getId();
                        invocacionPJenTablero(objPersonaje.getId(), idTablero, currectPlayer, row, coll, null, idPartida);
                    }
                    i++;
                }
            } else if (currectPlayer == 2) {
                objPartida.getJugador2().addPJtoListPlayer(objPersonaje);
                for (String coord : coordenadas) {
                    String coords[] = coord.split("-");
                    row = Integer.parseInt(coords[0]);
                    coll = Integer.parseInt(coords[1]);
                    if (i == 0) {
                        idTablero = objPartida.getTablero().getId();
                        invocacionPJenTablero(objPersonaje.getId(), idTablero, currectPlayer, row, coll, objPersonaje, idPartida);
                    } else {
                        idTablero = objPartida.getTablero().getId();
                        invocacionPJenTablero(objPersonaje.getId(), idTablero, currectPlayer, row, coll, null, idPartida);
                    }
                    i++;
                }
            } else {
                throw new BussinessException(505);
            }
        } else {
            throw new BussinessException(504);
        }
    }

    private void invocacionPJenTablero(int idPersonaje, int idTablero, int currectPlayer, int row,
                                       int coll, Personaje objPersonaje, int idPartida) throws Exception {

        Casilla casillaDeInvocacion = new Casilla(Helper.generarIdCasilla(row, coll, idPartida),
                idTablero, row, coll, objPersonaje, currectPlayer, true);
        ArrayList<Casilla> casillas = new ArrayList<Casilla>();
        casillas.add(casillaDeInvocacion);

        DataInvocar datInvocacion = new DataInvocar(idPersonaje, idPartida, currectPlayer, casillas);
        gesAcciones.actualizarTableroInvocar(datInvocacion);
    }

    public Personaje[] realizarAccionPersonaje(int idPersonajeAplica, int idPersonajeAplcar, int idPartida, int jugadorAplica, String[] listCeldas, String accionRealizar) throws Exception, BussinessException {
        Personaje objPersonajeAplica = null;
        Personaje objPersonajeAplicar = null;
        Personaje[] listPersonajes = new Personaje[2];
        int jugadorAplicar = 0;
        Partida partidaActual = gesPartidas.obtenerPartidaById(idPartida);
        try{
            if (accionRealizar.equals("Mover")) {
                jugadorAplicar = jugadorAplica;
                objPersonajeAplica = buscarPersonajebyID(idPersonajeAplica, idPartida, jugadorAplica);
                objPersonajeAplicar = buscarPersonajebyID(idPersonajeAplcar, idPartida, jugadorAplicar);
                realizaciondeAccion(objPersonajeAplica, objPersonajeAplicar, listCeldas, accionRealizar, partidaActual, jugadorAplica, jugadorAplicar);

                listPersonajes[0] = objPersonajeAplica;
                listPersonajes[1] = objPersonajeAplicar;
            } else {
                if (jugadorAplica == 1) {
                    jugadorAplicar = 2;
                    objPersonajeAplica = buscarPersonajebyID(idPersonajeAplica, idPartida, jugadorAplica);
                    objPersonajeAplicar = buscarPersonajebyID(idPersonajeAplcar, idPartida, jugadorAplicar);
                    realizaciondeAccion(objPersonajeAplica, objPersonajeAplicar, listCeldas, accionRealizar, partidaActual, jugadorAplica, jugadorAplicar);

                    listPersonajes[0] = objPersonajeAplica;
                    listPersonajes[1] = objPersonajeAplicar;

                } else {
                    jugadorAplicar = 1;
                    objPersonajeAplica = buscarPersonajebyID(idPersonajeAplica, idPartida, jugadorAplica);
                    objPersonajeAplicar = buscarPersonajebyID(idPersonajeAplcar, idPartida, jugadorAplicar);
                    realizaciondeAccion(objPersonajeAplica, objPersonajeAplicar, listCeldas, accionRealizar, partidaActual, jugadorAplica, jugadorAplicar);

                    listPersonajes[0] = objPersonajeAplica;
                    listPersonajes[1] = objPersonajeAplicar;
                }
            }
        }catch (BussinessException bex){
            ExceptionManager.GetInstance().Process(bex);
        }

        if(listPersonajes[1].getVida() <= 0){
            listPersonajes[1] = null;
        }

        return listPersonajes;
    }

    private Personaje buscarPersonajebyID(int idPersonaje, int idPartida, int jugador) throws BussinessException {
        Partida objPartida = gesPartidas.obtenerPartidaById(idPartida);
        Personaje personajeEncontrado = null;
        if (objPartida != null) {
            if (jugador == 1) {
                for (Personaje objPerPersonaje : objPartida.getJugador1().getPersonajesEnJuego()) {
                    if (objPerPersonaje.getId() == idPersonaje) {
                        personajeEncontrado = objPerPersonaje;
                        break;
                    }
                }
            } else if (jugador == 2) {
                for (Personaje objPerPersonaje : objPartida.getJugador2().getPersonajesEnJuego()) {
                    if (objPerPersonaje.getId() == idPersonaje) {
                        personajeEncontrado = objPerPersonaje;
                        break;
                    }
                }
            } else {
                throw new BussinessException(505);
            }
        } else {
            throw new BussinessException(504);
        }
        if (personajeEncontrado == null) {
            throw new BussinessException(506);
        }
        return personajeEncontrado;
    }

    private RealizarAccion configurarCadena() {
        RealizarAccion accionAtaque = new RealizarAtaque();
        RealizarAccion accionMovimiento = new RealizarMovimiento();
        RealizarAccion accionAtaqueEspecial = new RealizarAtaqueEspecial();

        accionAtaque.setAccionHandler(accionMovimiento);
        accionMovimiento.setAccionHandler(accionAtaqueEspecial);

        return accionAtaque;
    }

    public void iniciarProceso() {
        this.handler = configurarCadena();
    }

    public void realizaciondeAccion(Personaje objPersonajeAplica, Personaje objPersonajeAplicar,
                                    String[] casillas, String accionRealizar, Partida partidaActual, int jugadorAplica, int jugadorAlicar) throws Exception {
        handler.faseDeAccion(objPersonajeAplica, objPersonajeAplicar, casillas, accionRealizar, partidaActual, gesAcciones, jugadorAplica, jugadorAlicar);

    }
}
