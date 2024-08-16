/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Personajes;

import com.ReinosCenfotecosService.Entities.Artillero;
import com.ReinosCenfotecosService.Entities.Infanteria;
import com.ReinosCenfotecosService.Entities.Personaje;
import com.ReinosCenfotecosService.Entities.Tanque;

/**
 * @author jscru
 */
public class FactoryPersonaje implements IAbstractFactoryMethodPersonaje {

    @Override
    public Personaje crearPersonaje(int tPersonaje, int jugador) {
        Personaje objPersonaje = null;
        switch (tPersonaje) {
            case 1:
                objPersonaje = new Artillero(jugador);
                objPersonaje.setEstadisticasPersonaje();
                break;
            case 2:
                objPersonaje = new Infanteria(jugador);
                objPersonaje.setEstadisticasPersonaje();
                break;
            case 3:
                objPersonaje = new Tanque(jugador);
                objPersonaje.setEstadisticasPersonaje();
                break;
            default:
                break;
        }
        return objPersonaje;
    }

}
