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
public class AEAutoBuffDefensa extends AsbtractClassataqueEspecial {

    public AEAutoBuffDefensa(Personaje estadoBasePersonaje) {
        super(estadoBasePersonaje);
    }

    @Override
    public void accionAtaque() {
        int defensa = this.getDefensa();
        defensa = defensa * 2 ;
    }
}
