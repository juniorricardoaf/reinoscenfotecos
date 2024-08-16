/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Entities;

import com.ReinosCenfotecosService.Helper.Helper;

/**
 *
 * @author jscru
 */
public class Artillero extends Personaje {

    public Artillero(int jugador) {
        super(jugador);
    }

    @Override
    public void setEstadisticasPersonaje() {
        this.setId(Helper.creadorDeIdentificacion());
        this.setAtaque(6);
        this.setDefensa(10);
        this.setMovsMaximos(Helper.getMovsMaximos(1));
        this.setVida(4);
        this.setTipoPersonaje("Artillero");
    }

    @Override
    public String getInfoPersonaje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
