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

/**
 *
 * @author jscru
 */
public class ExceptionManager {

    private static final String PATH = "C:\\Users\\jscru\\Documents\\GitHub\\reinos-cenfotecos-service";
    private static ExceptionManager instance;
    private static HashMap<String, ApplicationMessage> listMessages = new HashMap<String, ApplicationMessage>();

    private ExceptionManager() throws Exception {
        loadMessages();
    }

    public static ExceptionManager GetInstance() throws Exception {
        if (instance == null) {
            instance = new ExceptionManager();
        }

        return instance;
    }

    public void Process(Exception ex) throws BussinessException, Exception {

        BussinessException bex = new BussinessException();

        if (ex instanceof BussinessException) {
            bex = (BussinessException) ex;
            ProcessBussinesException(bex);
        } else {
            ProcessGenericException(ex);
        }
    }

    private void loadMessages() throws Exception {
        String data;

        try {
            FileReader reader = new FileReader("config//ConfigExceptions.txt");
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
            ProcessGenericException(e);
        }
    }

    private void ProcessGenericException(Exception ex) throws Exception {
        String pattern = "yyyy_MM_dd__hh_mm_ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String today = simpleDateFormat.format(new Date());
        String logName = "Logs//"
                + today
                + "_" + "log.txt";
        String logPath = logName;

        String exceptionMessage = logger(ex);
        File logFile = new File(logPath);
        try {
            logFile.createNewFile();
            FileWriter myWriter = new FileWriter(logPath);
            myWriter.write(exceptionMessage);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        throw ex;
    }

    private void ProcessBussinesException(BussinessException bex) throws BussinessException, Exception {
        String pattern = "yyyy_MM_dd__hh_mm_ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String today = simpleDateFormat.format(new Date());
        String logName = "Logs//"
                + today
                + "_" + "log.txt";
        String logPath = logName;
        bex.message = getMessage(bex);
        String exceptionMessage = logger(bex);
        File logFile = new File(logPath);
        try {
            logFile.createNewFile();
            FileWriter myWriter = new FileWriter(logPath);
            myWriter.write(exceptionMessage);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        throw bex;
    }

    private ApplicationMessage getMessage(BussinessException bex) {
        ApplicationMessage appMessage = new ApplicationMessage();
        appMessage.message = "Mesage not found";

        String key = String.valueOf(bex.exceptionId);

        if (listMessages.containsKey(key)) {
            appMessage = listMessages.get(key);
        }
        return appMessage;
    }

    private String logger(Exception ex) {
        String logTrace = "";

        String pattern = "dd-M-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String today = simpleDateFormat.format(new Date());

        if (ex instanceof BussinessException) {
            logTrace = "######  Log Entry   ####\n"
                    + " " + today + ":\n"
                    + ((BussinessException) ex).exceptionId + ": " 
                    + ((BussinessException) ex).message.message
                    + "\n " +  StackTraceToString(ex);

        } else {
            logTrace = "######  Log Entry   ####\n"
                    + " " + today + ":\n"
                    + ex.getMessage()
                    + "\n " +  StackTraceToString(ex);
        }

        return logTrace;
    }

    
    public static String StackTraceToString(Exception ex) {
        String result = ex.toString() + "\n";
        StackTraceElement[] trace = ex.getStackTrace();
        for (int i=0;i<trace.length;i++) {
            result += trace[i].toString() + "\n";
        }
        return result;
    }
}
