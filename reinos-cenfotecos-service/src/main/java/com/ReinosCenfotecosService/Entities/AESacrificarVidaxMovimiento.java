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
public class AESacrificarVidaxMovimiento extends AsbtractClassataqueEspecial {

    public AESacrificarVidaxMovimiento(Personaje estadoBasePersonaje) {
        super(estadoBasePersonaje);
    }

    @Override
    public void accionAtaque() {
        int vida = this.getVida();
        int maxmovs = this.getMovsMaximos();
        vida = vida- 1;
        maxmovs = maxmovs + 2;
    }
}
