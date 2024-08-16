/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.Helper;


import javax.swing.text.html.HTML;
import java.util.Random;

/**
 *
 * @author jscru
 */
public class Helper {

    public static int creadorDeIdentificacion() {
        Random random = new Random();
        int rnd = random.nextInt(9999 - 1000) + 1000;
        return rnd;
    }

    public static int getMovsMaximos(int codPersonaje) {
        Random random = new Random();
        int rnd = 0;

        switch (codPersonaje) {
            case 1:
                rnd = random.nextInt(4 - 1) + 1;
                break;
            case 2:
                rnd = random.nextInt(6 - 1) + 1;
                break;
            case 3:
                rnd = random.nextInt(2 - 1) + 1;
                break;
        }

        return rnd;
    }
    //Dados
    public static int ramdomizeCantMovimientos() {
        return (int) (Math.random() * (6 - 0) + 1);
    }

    public static String ramdomizeAtaquesEspeciales() {
        String valor = "";
        int random = (int) (Math.random() * (6 - 0) + 1);
        switch (random) {
            case 1:
                valor = "volar";
                break;
            case 2:
                valor = "correr";
                break;
            case 3:
                valor = "fuerza";
                break;
            case 4:
                valor = "disparar";
                break;
            case 5:
                valor = "curar";
                break;
            case 6:
                valor = "nadar";
                break;
            case 7:
                valor = "Domingo";
                break;
            default:
                valor = "";
                break;
        }

        return valor;
    }

    //FIn dados


    public static int generarIdCasilla(int row, int coll, int idPartida){
        String rowString, collString, idPartidatring;
        rowString = String.valueOf(row);
        collString = String.valueOf(coll);
        idPartidatring = String.valueOf(idPartida);
        String idCasilla= rowString+collString+idPartidatring;
        return Integer.parseInt(idCasilla);
    }
}
