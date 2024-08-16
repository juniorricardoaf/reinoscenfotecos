package com.ReinosCenfotecosService.Core.Dados;

import com.ReinosCenfotecosService.Helper.Helper;

import java.util.ArrayList;


public class GestorPrototype {
    private static ArrayList<PrototipoDado> arrDados = new ArrayList<PrototipoDado>();

    private PrototipoDado protipoDado;
    

    public GestorPrototype(int pId) {
        //crea el que se va a clonar
        protipoDado = new Dados(pId, "Movimiento", 0);
    }

    public String nuevos_dados(String pDado, int id) {
        String msj = "";

        arrDados.add(protipoDado.clone());

        //vamos a buscar los Id de los militantes y ponerlos en el DNI
        updateCloneDados(pDado, id);
        msj = "Dado "+(id+1)+" creado";

        return msj;
    }

    private static void updateCloneDados(String pDado, int id) {

        PrototipoDado cc = arrDados.get(id);

        //Cambia los atributos de la copia
        cc.setId(id);
        cc.setTipo(pDado);
        
        //valorCantMovs
        if (pDado.equals("Movimiento")) {
            cc.setValor(Helper.ramdomizeCantMovimientos());
        }
    }

    /**
     * **********************************************************
     * Metodo:	obtenerDatos
     * <p>
     * Descripción:	Obtiene los datos de los objetos del arreglo y los devuelve
     * en una variable.
     * <p>
     * Parametros:	pid [int] id del arreglo.
     *
     * @return: mResult [String]
     * ***********************************************************
     */
    public String obtenerDatos(int pid) {
        return obtenerDatosObjeto(arrDados.get(pid)) + "\n";
    }

    /**
     * **********************************************************
     * Metodo:	obtenerDatos
     * <p>
     * Descripción:	Obtiene los datos de las identificaciones y los devuelve en
     * una variable.
     * <p>
     * Parametros:	N/A
     *
     * @return: mResult [String]
     * ***********************************************************
     */
    public String obtenerDatos() {
        String mResult = "";
        for (PrototipoDado mOb : arrDados) {
            mResult += obtenerDatosObjeto(mOb) + "\n";
        }
        return mResult;
    }
    
    public PrototipoDado[] obtenerArreglo() {
        PrototipoDado[] arrdadosconvertidos = new PrototipoDado[3];
        int i=0;
        for (PrototipoDado mOb : arrDados) {
            arrdadosconvertidos[i] = mOb;
            i++;
        }
        return arrdadosconvertidos;
    }

    /**
     * ************************************************************
     * Metodo:	obtenerDatosObjeto
     * <p>
     * Descripción:	Obtiene los datos de un objeto en especifico
     * <p>
     * Parametros:	pObj [PrototipoDado]
     *
     * @return: mResult [String]
     * ***********************************************************
     */
    private String obtenerDatosObjeto(PrototipoDado pObj) {
        return pObj.getData() + "\n";
    }

    public void limpiarLista() {
        arrDados.clear();
    }
}
