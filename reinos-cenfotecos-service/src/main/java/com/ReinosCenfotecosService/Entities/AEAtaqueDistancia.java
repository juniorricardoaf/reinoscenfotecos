/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Entities;

import com.ReinosCenfotecosService.Core.AtaqueEspecial.AsbtractClassataqueEspecial;

/**
 *
 * @author jscru
 */
public class AEAtaqueDistancia extends AsbtractClassataqueEspecial {

    public AEAtaqueDistancia(Personaje estadoBasePersonaje) {
        super(estadoBasePersonaje);
    }

    @Override
    public void accionAtaque() {
        int rango = this.getRangoAtaque();
        rango = 2;
        this.setRangoAtaque(rango);
    }
}
