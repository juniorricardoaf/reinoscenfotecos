package com.ReinosCenfotecosService.Core.Personajes;

import com.ReinosCenfotecosService.Core.Partida.GestorAcciones;
import com.ReinosCenfotecosService.Core.Partida.GestorPartidas;
import com.ReinosCenfotecosService.Entities.Accion;
import com.ReinosCenfotecosService.Entities.Casilla;
import com.ReinosCenfotecosService.Entities.Partida;
import com.ReinosCenfotecosService.Entities.Personaje;
import com.ReinosCenfotecosService.exceptions.BussinessException;

public abstract class RealizarAccion {
    RealizarAccion handler;

    public void setAccionHandler(RealizarAccion accionHandler){
        this.handler = accionHandler;
    }

    public abstract void faseDeAccion(Personaje objPersonajeAplica, Personaje objPersonajeAplicar,
                                      String[] casillas, String accionRealizar,
                                      Partida partidaActual, GestorAcciones gesAcciones, int jugadorAplica, int jugadorAplicar) throws Exception;
}
