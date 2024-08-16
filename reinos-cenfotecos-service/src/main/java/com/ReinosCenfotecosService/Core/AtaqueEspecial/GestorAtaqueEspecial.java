/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.AtaqueEspecial;

import com.ReinosCenfotecosService.Entities.*;
import com.ReinosCenfotecosService.exceptions.BussinessException;
import com.ReinosCenfotecosService.exceptions.ExceptionManager;

import java.util.ArrayList;

/**
 * @author jscru
 */
public class GestorAtaqueEspecial {

    Jugador player;

    public GestorAtaqueEspecial() {
        player = new Jugador(123456, "Bryan");
        player.setColor(1);
        player.agregarPersonajealistadeJugador(new Infanteria(player.getColor()));
        player.agregarPersonajealistadeJugador(new Infanteria(player.getColor()));
        player.agregarPersonajealistadeJugador(new Artillero(player.getColor()));
        player.agregarPersonajealistadeJugador(new Artillero(player.getColor()));
        player.agregarPersonajealistadeJugador(new Tanque(player.getColor()));
        player.agregarPersonajealistadeJugador(new Tanque(player.getColor()));
        player.getPersonajesEnJuego().get(0).setId(123);
        player.getPersonajesEnJuego().get(1).setId(234);
        player.getPersonajesEnJuego().get(2).setId(345);
        player.getPersonajesEnJuego().get(3).setId(567);
        player.getPersonajesEnJuego().get(4).setId(789);
        player.getPersonajesEnJuego().get(5).setId(890);
    }

    public void asignarAtaqueEspcial(AtaqueEspecial specialAtack, int idPersonajeAplica,
                                     int idPersonajeaAplicar, int idPartida, int turnoplayer) throws BussinessException,
            Exception {
        Personaje objPersonajeAplica = buscarPersonajeByID(idPersonajeAplica);
        Personaje objPersonajeaAplicar = buscarPersonajeByID(idPersonajeaAplicar);

        try {
            if (objPersonajeAplica instanceof Infanteria) {
                switch (specialAtack.getNombre()) {
                    case "Altar del guardián":
                        replaceInArray(idPersonajeaAplicar, new AEHealerlvl1(objPersonajeaAplicar));
                        break;
                    case "Plegaria":
                        for (Personaje objPj : player.getPersonajesEnJuego()) {
                            replaceInArray(objPj.getId(), new AEHealerlvl2(objPj));
                        }
                        break;
                    case "Horno del alma":
                        replaceInArray(idPersonajeaAplicar, new AEBufferAtaque(objPersonajeaAplicar));
                        break;
                    case "Refugio del espiritu":
                        replaceInArray(idPersonajeaAplicar, new AEBufferDefensa(objPersonajeaAplicar));
                        break;
                    default:
                        throw new BussinessException(502);
                }
            } else if (objPersonajeAplica instanceof Artillero) {
                switch (specialAtack.getNombre()) {
                    case "Embate imparable":
                        replaceInArray(idPersonajeaAplicar, new AEAtaqueDistancia(objPersonajeaAplicar));
                        break;
                    case "Indestuctrible":
                        replaceInArray(idPersonajeaAplicar, new AEAutoBuffDefensa(objPersonajeaAplicar));
                        break;
                    case "Heraldo de la Destrucción":
                        replaceInArray(idPersonajeaAplicar, new AEAutoBuffAtaque(objPersonajeaAplicar));
                        break;
                    default:
                        throw new BussinessException(502);
                }
            } else if (objPersonajeAplica instanceof Tanque) {
                switch (specialAtack.getNombre()) {

                    case "Descarga de Adrenalina ":
                        if (objPersonajeaAplicar.getVida() == 2) {
                            replaceInArray(idPersonajeaAplicar, new AESacrificarVidaxMovimiento(objPersonajeaAplicar));
                        } else {
                            //Agregar msg de error
                            throw new BussinessException(502);
                        }
                        break;
                    case "Gloria en la muerte":
                        replaceInArray(idPersonajeaAplicar, new AEAtaqueBomba(objPersonajeaAplicar));
                        break;
                    case "Embajador de Hierro":
                        objPersonajeAplica.setDefensa(objPersonajeAplica.getDefensa() / 2);
                        replaceInArray(idPersonajeaAplicar, new AEProteccionEquivalente(objPersonajeaAplicar));
                        break;
                    case "Tormenta Creciente ":
                        replaceInArray(idPersonajeaAplicar, new AEDebuffAoE(objPersonajeaAplicar));
                        break;
                    default:
                        throw new BussinessException(502);
                }
            } else {
                throw new BussinessException(501);
            }
        } catch (BussinessException bex) {
            ExceptionManager.GetInstance().Process(bex);
        } catch (Exception e) {
            ExceptionManager.GetInstance().Process(e);
        }
    }

    //    public void finalizacionAtaqueEspecial() {
//        AsbtractClassEvolucion personajeAtacando = (AsbtractClassEvolucion) objPersonajeAtacando;
//        Personaje estadoBase = personajeAtacando.getEstadoBasePersonaje();
//    }
    private Personaje buscarPersonajeByID(int idPersonaje) throws Exception {
        Personaje personajeEncontrado = null;
        try {
            boolean found = false;

            for (Personaje objPersonaje : player.getPersonajesEnJuego()) {
                if (objPersonaje.getId() == idPersonaje) {
                    personajeEncontrado = objPersonaje;
                    found = true;
                    break;
                }
            }

            if (found == false) {
                throw new BussinessException(503);
            }
        } catch (BussinessException bex) {
            ExceptionManager.GetInstance().Process(bex);
        }

        return personajeEncontrado;
    }

    private void replaceInArray(int id, Personaje pjDecorado) throws Exception {
        ArrayList<Personaje> personajes = this.player.getPersonajesEnJuego();
        Personaje objPersonaje = buscarPersonajeByID(id);
        int index = personajes.indexOf(objPersonaje);
        personajes.set(index, pjDecorado);
    }
}
