/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.AtaqueEspecial;

import com.ReinosCenfotecosService.Entities.Personaje;

/**
 *
 * @author jscru
 */
public abstract class AsbtractClassataqueEspecial extends Personaje {

    private Personaje estadoBasePersonaje;

    public AsbtractClassataqueEspecial(Personaje estadoBasePersonaje) {
        this.estadoBasePersonaje = estadoBasePersonaje;
    }

    public Personaje getEstadoBasePersonaje() {
        return estadoBasePersonaje;
    }

    @Override
    public void setEstadisticasPersonaje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInfoPersonaje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public abstract void accionAtaque();
}
