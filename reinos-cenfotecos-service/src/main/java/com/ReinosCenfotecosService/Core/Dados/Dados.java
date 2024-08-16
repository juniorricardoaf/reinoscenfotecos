package com.ReinosCenfotecosService.Core.Dados;

//agregar  naves,campamentos, nombre y profesion.

import com.ReinosCenfotecosService.Entities.AtaqueEspecial;
import com.ReinosCenfotecosService.Entities.AtaqueNormal;


public class Dados extends PrototipoDado {

    public Dados(int pId, String pTipo, int cantMovimientos) {
        this.setId(pId);
        this.setTipo(pTipo);
        this.setValor(cantMovimientos);

    }

    @Override
    public PrototipoDado clone() {
        return new Dados(this.getId(), this.getTipo(), this.getValor());
    }

}
