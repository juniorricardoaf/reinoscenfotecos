/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Entities.Cofre;

/**
 *
 * @author jorge
 */
public class ConstructorJugador extends BuilderJugador {

    @Override
    public void construir(int id, String nombre, int vidaCastillo, int row, int column, int color, Cofre cofre) {
        this.obj.setId(id);
        this.obj.setNombre(nombre);
        this.obj.setVidaCastillo(vidaCastillo);
        this.obj.setRow(row);
        this.obj.setColumn(column);
        this.obj.setColor(color);
        this.obj.setCofre(cofre);

    }

}
