/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Core.Partida;

import com.ReinosCenfotecosService.Entities.Jugador;
import com.ReinosCenfotecosService.Entities.Partida;
import com.ReinosCenfotecosService.Helper.Helper;
import com.ReinosCenfotecosService.exceptions.BussinessException;
import com.ReinosCenfotecosService.exceptions.ExceptionManager;
import java.util.Optional;
import java.util.ArrayList;

import java.util.Random;

/**
 *
 * @author jorge
 */
public class GestorPartidas {

    protected static ArrayList<Partida> partidas = new ArrayList<Partida>();
    protected static ArrayList<MementoPartida> partidasGuardadas = new ArrayList<MementoPartida>();
    private DirectorPartida objDirector = new DirectorPartida();

    private Jugador construcionJugador(String nombre, int rowCastillo, int columnCastillo, int color) {
        objDirector.setBuilderJugador(new ConstructorJugador());
        objDirector.setBuilderCofre(new ConstructorCofre());
        return crearJugador(nombre, rowCastillo, columnCastillo, color);
        //objDirector.construirJugador(getIdJugador(), nombre, rowCastillo, columnCastillo);
    }

    private Jugador crearJugador(String nombre, int rowCastillo, int columnCastillo, int color) {
        objDirector.construirJugador(getIdJugador(), nombre, rowCastillo, columnCastillo, color);
        return objDirector.getBuilderJugador().getObjeto();
    }

    private int getIdJugador() {
        int idR = Helper.creadorDeIdentificacion();
        return idR;
    }

    private int getIdPartida() {
        int idR;
        boolean valid = false;
        do {
            idR = Helper.creadorDeIdentificacion();

            if (obtenerPartidaById(idR) == null) {
                valid = true;
            }
        } while (!valid);

        return idR;

    }

    public Partida obtenerPartidaById(int id) {

        Optional<Partida> obj;
        obj = partidas.stream().filter(e -> e.getId() == id).findFirst();
        if (obj.isPresent()) {
            return obj.get();
        } else {
            return null;
        }

    }

    public Partida construcionPartida(String nombre1, String nombre2, int ganador) throws Exception {
        Partida par = null;
        try {
            objDirector.setBuilderTablero(new ConstructorTablero());
            //generar campo random para castillos
            int row1 = 19, col1, row2 = 0, col2, color1, color2;
            Random random = new Random();
            col1 = random.nextInt(20 - 0) + 0;
            col2 = random.nextInt(20 - 0) + 0;

            if (ganador == 1) {
                color1 = 1;
                color2 = 2;
            } else {
                color1 = 2;
                color2 = 1;
            }

            Jugador jug1 = construcionJugador(nombre1, row1, col1, color1);
            Jugador jug2 = construcionJugador(nombre2, row2, col2, color2);

            objDirector.setBuilderPartida(new ConstructorPartida());
            par = crearPartida(jug1, jug2, ganador);
            partidas.add(par);
            guardarMemento(par.generarMemento());

        } catch (Exception e) {
            ExceptionManager.GetInstance().Process(e);

        }
        return par;
    }

    private Partida crearPartida(Jugador jug1, Jugador jug2, int turno) {
        objDirector.construirPartida(getIdPartida(), jug1, jug2, getIdJugador(), turno);
        return objDirector.getBuilderPartida().getObjeto();
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public Partida actualizarPartida(Partida partida) throws Exception {//actualiza rtodo menos las casillas
        try {
            Optional<Partida> obj;
            obj = partidas.stream().filter(e -> e.getId() == partida.getId()).findFirst();
            if (obj.isPresent()) {

                int index = partidas.indexOf(obj.get());
                if (index != -1) {
                    partida.setTablero(obj.get().getTablero());
                    partida.getJugador1().setPersonajesEnJuego(obj.get().getJugador1().getPersonajesEnJuego());//ignora la lista de jugadores
                    partida.getJugador2().setPersonajesEnJuego(obj.get().getJugador2().getPersonajesEnJuego());
                    partida.getJugador1().setCofre(obj.get().getJugador1().getCofre());//ignore el cofre
                    partida.getJugador2().setCofre(obj.get().getJugador2().getCofre());
                    partidas.set(index, partida);
                    return partidas.get(index);
                } else {
                    throw new BussinessException(300);
                }

            } else {
                throw new BussinessException(300);
            }
        } catch (Exception e) {
            ExceptionManager.GetInstance().Process(e);

        }
        return null;
    }

    public Partida crearPartidaRapida() throws Exception {//fachada
        //generar nombres 

        Partida res = null;
        try {
            Random random = new Random();

            res = construcionPartida(getNombreRandom(), getNombreRandom(), random.nextInt(2 - 0) + 1);
        } catch (Exception e) {
            ExceptionManager.GetInstance().Process(e);

        }
        return res;

    }

    public String getNombreRandom() {
        //generar nombres 
        ArrayList<String> nombres = new ArrayList<String>();
        nombres.add("Beberly");
        nombres.add("Jason");
        nombres.add("Junior");
        nombres.add("Harold");
        nombres.add("Guisell");
        nombres.add("Jorge");
        nombres.add("Hernesto");
        nombres.add("Pedro");
        nombres.add("Ana");
        nombres.add("Lucia");
        nombres.add("Ramona");
        nombres.add("Carlos");

        Random random = new Random();

        return nombres.get(random.nextInt(nombres.size() - 0) + 0);

    }

    public MementoPartida getMemento(int id) throws Exception {
        try {
            Optional<MementoPartida> obj;
            obj = partidasGuardadas.stream().filter(e -> e.getEstadoMemento().getId() == id).findFirst();
            if (obj.isPresent()) {

                return obj.get();
            } else {
                throw new BussinessException(301);
            }

        } catch (Exception e) {

            ExceptionManager.GetInstance().Process(e);
        }
        return null;
    }

    public Partida replacePartidaConMemento(int id) throws Exception {
        try {
            //llamo el memento
            MementoPartida memento = getMemento(id);
            Optional<Partida> obj;
            //busco la partida
            obj = partidas.stream().filter(e -> e.getId() == id).findFirst();
            if (obj.isPresent()) {
                obj.get().restaurarMemento(memento);
                int index = partidas.indexOf(obj.get());
                if (index != -1) {

                    return partidas.get(index);
                } else {
                    throw new BussinessException(300);
                }
                

            } else {
                throw new BussinessException(300);
            }
        } catch (Exception e) {

            ExceptionManager.GetInstance().Process(e);
        }
        return null;
    }

    public void guardarMemento(MementoPartida par) {
        partidasGuardadas.add(par);
    }

}
