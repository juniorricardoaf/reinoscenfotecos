/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Entities.Cofre;

/**
 *
 * @author guiss
 */
public abstract class BuilderCofre {

    protected Cofre obj;

    public void crearNuevo() {
        this.obj = new Cofre();
    }

    public Cofre getObjeto() {
        return this.obj;
    }

    // Métodos que deberán ser construídos por las clases que hereden de ésta
    public abstract void construir();

}
