/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Core.Cofre.ICofre;
import java.util.ArrayList;

/**
 *
 * @author guiss
 */
public class ConstructorCofre extends BuilderCofre {
 private static int classCounter = 0;
    @Override
    public void construir() {
        this.obj.setListaComposicion(new ArrayList<ICofre>());  
        this.obj.setId(classCounter);
        classCounter ++;
    }
    
}
