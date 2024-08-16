package com.ReinosCenfotecosService.Core.Observador;

import com.ReinosCenfotecosService.Entities.Partida;


public interface Observador {
    Partida update(Partida value, String nombre);

}