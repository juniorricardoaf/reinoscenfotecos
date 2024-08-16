/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.webapi.models;

/**
 *
 * @author jscru
 */
public class ExceptionResponse {
    public String tituloExcepcion;
    public String pilaError;

    public ExceptionResponse(String tituloExcepcion, String pilaError) {
        this.tituloExcepcion = tituloExcepcion;
        this.pilaError = pilaError;
    }

    public String getTituloExcepcion() {
        return tituloExcepcion;
    }

    public void setTituloExcepcion(String tituloExcepcion) {
        this.tituloExcepcion = tituloExcepcion;
    }

    public String getPilaError() {
        return pilaError;
    }

    public void setPilaError(String pilaError) {
        this.pilaError = pilaError;
    }

    @Override
    public String toString() {
        return "ExceptionMessage{" + "tituloExcepcion=" + tituloExcepcion + ", pilaError=" + pilaError + '}';
    }
    
    
}
