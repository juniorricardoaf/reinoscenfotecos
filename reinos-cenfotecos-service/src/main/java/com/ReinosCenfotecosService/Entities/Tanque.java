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
public class Tanque extends Personaje {

    public Tanque(int jugador) {
        super(jugador);
    }

    @Override
    public void setEstadisticasPersonaje() {
        this.setId(Helper.creadorDeIdentificacion());
        this.setAtaque(10);
        this.setDefensa(10);
        this.setMovsMaximos(Helper.getMovsMaximos(2));
        this.setVida(2);
        this.setTipoPersonaje("Tanque");
    }

    @Override
    public String getInfoPersonaje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
