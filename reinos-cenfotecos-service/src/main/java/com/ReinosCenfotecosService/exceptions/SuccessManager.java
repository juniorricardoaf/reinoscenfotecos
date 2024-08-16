/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ReinosCenfotecosService.exceptions;

import com.ReinosCenfotecosService.Entities.ApplicationMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author jscru
 */
public class SuccessManager {

    protected static final String PATH = "C:\\Users\\jscru\\Documents\\GitHub\\reinos-cenfotecos-service";
    protected static HashMap<String, ApplicationMessage> listMessages = new HashMap<String, ApplicationMessage>();

    private SuccessManager() throws Exception {

    }

    private static void loadSuccessMessages() throws Exception {

        try {
            FileReader reader = new FileReader(PATH + "\\config\\ProofMessages.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String datos;

            while ((datos = buffer.readLine()) != null) {
                ApplicationMessage mensaje = new ApplicationMessage();
                String[] parts = datos.split(":");
                mensaje.id = Integer.parseInt(parts[0]);
                mensaje.message = parts[1];
                listMessages.put(parts[0], mensaje);
            }
            reader.close();
        } catch (IOException e) {
            throw e;
        }
    }

    private static ApplicationMessage getMessage(int proofId) {

        ApplicationMessage appMessage = new ApplicationMessage();
        appMessage.message = "Mesage not found";

        String key = String.valueOf(proofId);

        if (listMessages.containsKey(key)) {
            appMessage = listMessages.get(key);
        }
        return appMessage;
    }

    @Async
    public static void proceessSuccessProof(int proofid, boolean status) throws IOException, Exception {
        loadSuccessMessages();

        String pattern = "yyyy_MM_dd__hh_mm_ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String today = simpleDateFormat.format(new Date());
        String logName = "\\Proofs\\"
                + today
                + "_" + "proof.txt";
        String logPath = PATH + logName;
        String exceptionMessage = logger(getMessage(proofid), status);
        File logFile = new File(logPath);
        try {

            logFile.createNewFile();
            FileWriter myWriter = new FileWriter(logPath);
            myWriter.write(exceptionMessage);
            myWriter.close();

        } catch (IOException ex) {
            throw ex;
        }

    }

    private static String logger(ApplicationMessage proof, boolean status) {
        String logTrace = "";

        String pattern = "dd-M-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String today = simpleDateFormat.format(new Date());
        if (status) {
            logTrace = "######  Log Entry   ####\n"
                    + " " + today + ":\n\n\n"
                    + "     La prueba de tipo " + proof.message + " de codigo " + proof.id
                    + " terminó en estado de éxito, realizando la acción solicitada";
        } else {
            logTrace = "######  Log Entry   ####\n"
                    + " " + today + ":\n\n\n"
                    + "     La prueba de tipo " + proof.message + " de codigo " + proof.id
                    + " terminó en estado fallido, sin poder realoizar la acción esperada";
        }

        return logTrace;
    }
}
