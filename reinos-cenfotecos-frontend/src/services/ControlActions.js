//import axios from 'axios';
import { BASEURL, ListaMetodos } from "../config/Config";

import Swal from "sweetalert2";

export const SweetAlert = (type, title, text, btn, callBack, img) => {
  let icono = "";
  switch (type) {
    case 1:
      icono = "warning";
      break;
    case 2:
      icono = "success";
      break;
    case 3:
      icono = "error";
      break;
    case 4:
      icono = "info";
      break;
    case 5:
      icono = "question";
      break;
    default:
      icono = "";
      break;
  }

  const alerta = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-primary",
      container: "zindex",
    },
    buttonsStyling: false,
  });
  alerta
    .fire({
      imageUrl: img,
      imageHeight: 200,
      title: text,
      text: title,
      confirmButtonText: btn,
      
                                    
    })
    .then((result) => {
      if (result.value) {
        callBack();
      }
    });
};

const request = async (servicio, metodo, tipo, id, data) => {
 // console.log("request" + `${BASEURL}${servicio}/${metodo}${id ? `/${id}` : ""}`, data);

  let objPeticion = {
    method: tipo,
    body: data ? JSON.stringify(data) : undefined,
    headers: {
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    },
    credentials: "same-origin",
  };

  const response = await fetch(
    `${BASEURL}${servicio}/${metodo}${id ? `/${id}` : ""}`,
    objPeticion
  ).catch((error) => {
    throw error;
  });
  const Jreponse = response.json();
  // console.log("res",Jreponse)
  return Jreponse;
};

export const Create = (servicio, metodo, id, data) => {
  //console.log("data",data )
  return request(servicio, metodo, ListaMetodos.POST, id, data);
};
export const Retrieve = (servicio, metodo, id) => {
  return request(servicio, metodo, ListaMetodos.GET, id);
};
export const Update = (servicio, metodo, id, data) => {
  return request(servicio, metodo, ListaMetodos.PUT, id, data);
};
export const Delete = (servicio, metodo, id, data) => {
  return request(servicio, metodo, ListaMetodos.DELETE, id, data);
};



/**
 * Esta línea es de prueba
 * Es para que accedan a un API externo
 * Este sería el dominio principal al cual después se le concatenará la ruta que deseemos
 * Ver en FigureService línea 5
 */

// Se crea una instancia de axios con su configuración
//const $axios = axios.create({
  // Header necesarios
 // headers: {
 //   'Content-Type': 'application/json',
 //   'Access-Control-Allow-Origin': '*'
 // },
  // Y un url base que se específica en la línea 26 (prueba)
  //baseURL: apiDomain
//});

// Se exporta la instancia
