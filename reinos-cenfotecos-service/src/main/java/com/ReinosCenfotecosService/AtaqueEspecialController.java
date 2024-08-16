/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService;

import com.ReinosCenfotecosService.Core.AtaqueEspecial.GestorAtaqueEspecial;
import com.ReinosCenfotecosService.Entities.AtaqueEspecial;
import com.ReinosCenfotecosService.exceptions.BussinessException;
import com.ReinosCenfotecosService.exceptions.ExceptionManager;
import com.ReinosCenfotecosService.webapi.models.ApiResponse;
import com.ReinosCenfotecosService.webapi.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jscru
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AtaqueEspecialController {


    ApiResponse apiResponse;

    @RequestMapping(value = "/api/ataqueEspecial/asignarAtaqueEspecial", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApiResponse> asignarAtaqueEspecial(AtaqueEspecial specialAttack,
                                                             int idPersonajeAplica, int idPersonajeaAplicar,
                                                             int idPartida, int turnoplayer) {
        ResponseEntity serverResponse;
        try {
            apiResponse = new ApiResponse();
            GestorAtaqueEspecial gestor = new GestorAtaqueEspecial();
            gestor.asignarAtaqueEspcial(specialAttack, idPersonajeAplica, idPersonajeaAplicar, idPartida, turnoplayer);
            apiResponse.message = "Ataque especial Exitoso";
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (BussinessException bex) {

            return new ResponseEntity(new ExceptionResponse(bex.message.message,
                    ExceptionManager.StackTraceToString(bex)), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(new ExceptionResponse(e.getMessage(),
                    ExceptionManager.StackTraceToString(e)), HttpStatus.OK);
        }
    }
}
