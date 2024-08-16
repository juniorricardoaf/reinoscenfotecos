/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Entities.Casilla;
import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public class ConstructorTablero extends BuilderTablero {

    @Override
    public void construir(int id, int idPartida) {
        this.obj.setId(id);
        this.obj.setIdPartida(idPartida);
    }

    @Override
    public void construirConCasillas(ArrayList<ArrayList<Casilla>> casillas) {
        this.obj.setCasillas(casillas);
    }

}
