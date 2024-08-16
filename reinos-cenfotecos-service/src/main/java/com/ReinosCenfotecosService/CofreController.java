/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService;

import com.ReinosCenfotecosService.Core.Cofre.GestorCofre;
import com.ReinosCenfotecosService.Core.Dados.GestorPrototype;
import com.ReinosCenfotecosService.Entities.Dado;
import com.ReinosCenfotecosService.exceptions.BussinessException;
import com.ReinosCenfotecosService.exceptions.ExceptionManager;
import com.ReinosCenfotecosService.webapi.models.ApiResponse;
import com.ReinosCenfotecosService.webapi.models.ExceptionResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guiss
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CofreController {

    private static GestorCofre gestorComposite = new GestorCofre();
    
    ApiResponse apiResponse;
    @RequestMapping(value = "/api/partida/mostrarCofre", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ApiResponse> mostrarCofre(HttpEntity<String> httpEntityd, int idPartida, int jugador) {
        ResponseEntity serverResponse;
        try {
            Gson gson = new Gson();
            apiResponse = new ApiResponse();
            String jsonDado = httpEntityd.getBody();

            Dado[] dado = gson.fromJson(jsonDado, Dado[].class);
            ArrayList<Dado> da = new ArrayList<Dado>();
            da.addAll(Arrays.asList(dado));
            apiResponse.data = gestorComposite.nuevo_cofre_Dado(da, idPartida, jugador);
            apiResponse.message = "Se agregó";
            return serverResponse = new ResponseEntity(apiResponse, HttpStatus.OK);

        } catch (Exception e) {

            return serverResponse = new ResponseEntity(new ExceptionResponse(e.getMessage(),
                    ExceptionManager.StackTraceToString(e)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/partida/quitarDado", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ApiResponse> quitarDadoCofre(HttpEntity<String> httpEntityd, int idPartida, int jugador) {
        ResponseEntity serverResponse;
        try {
            Gson gson = new Gson();
            apiResponse = new ApiResponse();
            String jsonDado = httpEntityd.getBody();

            Dado[] dado = gson.fromJson(jsonDado, Dado[].class);
            ArrayList<Dado> da = new ArrayList<Dado>();
            da.addAll(Arrays.asList(dado));
            apiResponse.data = gestorComposite.quitar_cofre_Dado(da, idPartida, jugador);
            apiResponse.message = "Se eliminó";
            return serverResponse = new ResponseEntity(apiResponse, HttpStatus.OK);

        } catch (Exception e) {

            return serverResponse = new ResponseEntity(new ExceptionResponse(e.getMessage(),
                    ExceptionManager.StackTraceToString(e)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/api/partida/quitarMov", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ApiResponse> quitarMov(HttpEntity<String> httpEntityd, int idPartida, int jugador) {
        ResponseEntity serverResponse;
        try {
            Gson gson = new Gson();
            apiResponse = new ApiResponse();
            String jsonDado = httpEntityd.getBody();

            Dado[] dado = gson.fromJson(jsonDado, Dado[].class);
            ArrayList<Dado> da = new ArrayList<Dado>();
            da.addAll(Arrays.asList(dado));
            apiResponse.data = gestorComposite.quitar_mov_Dado(da, idPartida, jugador);
            apiResponse.message = "Se restó un movimiento";
            return serverResponse = new ResponseEntity(apiResponse, HttpStatus.OK);

        } catch (Exception e) {

            return serverResponse = new ResponseEntity(new ExceptionResponse(e.getMessage(),
                    ExceptionManager.StackTraceToString(e)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
