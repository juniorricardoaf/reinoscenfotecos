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
public class Infanteria extends Personaje {

    public Infanteria(int jugador) {
        super(jugador);
    }

    @Override
    public void setEstadisticasPersonaje() {
        this.setId(Helper.creadorDeIdentificacion());
        this.setAtaque(3);
        this.setDefensa(5);
        this.setMovsMaximos(Helper.getMovsMaximos(2));
        this.setVida(5);
        this.setTipoPersonaje("Infanteria");
    }

    @Override
    public String getInfoPersonaje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
