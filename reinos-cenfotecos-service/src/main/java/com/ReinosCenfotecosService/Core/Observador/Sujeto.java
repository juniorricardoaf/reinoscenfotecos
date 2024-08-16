package com.ReinosCenfotecosService.Core.Observador;

import com.ReinosCenfotecosService.Entities.Partida;

public interface Sujeto {
    void addObserver(Observador o);

    void removeObserver(Observador o);

    Partida notifyObservers();
}