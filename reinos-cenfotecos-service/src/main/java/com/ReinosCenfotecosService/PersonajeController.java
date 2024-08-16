/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService;

import com.ReinosCenfotecosService.Core.Personajes.GestorPersonajes;
import com.ReinosCenfotecosService.Entities.Accion;
import com.ReinosCenfotecosService.Entities.Personaje;
import com.ReinosCenfotecosService.exceptions.BussinessException;
import com.ReinosCenfotecosService.exceptions.ExceptionManager;
import com.ReinosCenfotecosService.webapi.models.ApiResponse;
import com.ReinosCenfotecosService.webapi.models.ExceptionResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jscru
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PersonajeController {

    ApiResponse apiResponse;

    @RequestMapping(value = "/api/personaje/crearPersonaje", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApiResponse> crearPersonaje(int tPersonaje, int idPartida, int jugador, String[] coordenadas) {
        ResponseEntity serverResponse;
        try {
            Gson gson = new Gson();
            apiResponse = new ApiResponse();
            GestorPersonajes gestor = new GestorPersonajes();
            String string = gson.toJson(gestor.CrearPersonaje(tPersonaje, idPartida, jugador, coordenadas));
            JsonObject jsonObject = new JsonParser().parse(string).getAsJsonObject();
            apiResponse.data = jsonObject;
            apiResponse.message = "Tropa creada";
            return serverResponse = new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (BussinessException bex) {

            return serverResponse = new ResponseEntity(new ExceptionResponse(bex.message.message,
                    ExceptionManager.StackTraceToString(bex)), HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {

            return serverResponse = new ResponseEntity(new ExceptionResponse(e.getMessage(),
                    ExceptionManager.StackTraceToString(e)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/personaje/accionPersonaje", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApiResponse> accionPersonaje(int idPersonajeAplica, int idPersonajeAplicar, int idPartida, int jugadorAplica, String[] coordenadas, String accionRealizar) {
        ResponseEntity serverResponse;
        try {
            Gson gson = new Gson();
            apiResponse = new ApiResponse();
            GestorPersonajes gestor = new GestorPersonajes();

            Personaje[] response = gestor.realizarAccionPersonaje(idPersonajeAplica, idPersonajeAplicar, idPartida, jugadorAplica, coordenadas, accionRealizar);
            apiResponse.data = response;
            apiResponse.message = "Acción realizada";
            return serverResponse = new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (BussinessException bex) {

            return serverResponse = new ResponseEntity(new ExceptionResponse(bex.message.message,
                    ExceptionManager.StackTraceToString(bex)), HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {

            return serverResponse = new ResponseEntity(new ExceptionResponse(e.getMessage(),
                    ExceptionManager.StackTraceToString(e)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
