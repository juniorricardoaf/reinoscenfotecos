/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.exceptions;

import com.ReinosCenfotecosService.Entities.ApplicationMessage;

/**
 *
 * @author jscru
 */
public class BussinessException extends Exception{
    public int exceptionId;
    public ApplicationMessage message;

    public BussinessException() {
    }

    public BussinessException(int exceptionID) {
        this.exceptionId = exceptionID;
    }
    
     public BussinessException(int exceptionID, Exception innerException) {
         this.exceptionId = exceptionId;
    }
}
