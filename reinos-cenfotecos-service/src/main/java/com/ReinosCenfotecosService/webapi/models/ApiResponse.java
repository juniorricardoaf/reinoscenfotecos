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
public class ApiResponse {
    public String message;
    public Object data;

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponse() {
    }
}
