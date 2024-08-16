/**
 * Se importa el ControlActions para poder acceder 
 * a la instacia de axios ya previamente configurada
 */
//import service from '../ControlActions.js';
import { Create, Update } from '../ControlActions.js';
const controller = "/api/personaje";
// se exportan los métodos que se comunican con el API por medio de axios
export default {

    // Este método lo uso en BoardComponent línea 21 para que lo vayan a ver

    /*  crearPersonaje(tPersonaje) {
          let uri = '/api/personaje/crearPersonaje?tPersonaje=';
  
          service.$axios.post(uri + tPersonaje).then((response) => {   
              return response;
  
          }).catch((error) => {
              console.log(error);
          });
  
      },*/


    crearPersonaje(tPersonaje, idPartida, jugador, listCeldasLlenas) {
    //    console.log("tPersonaje", tPersonaje)
        return Create(controller,
            'crearPersonaje?tPersonaje=' + tPersonaje +
            '&idPartida=' + idPartida +
            '&jugador=' + jugador +
            '&coordenadas=' + listCeldasLlenas);
    },


    realizarAccion(idPersonajeAplica, idPersonajeAplicar, idPartida, jugadorAplica, listCeldas, accion) {
        return Create(controller,
            'accionPersonaje?idPersonajeAplica=' + idPersonajeAplica +
            '&idPersonajeAplicar=' + idPersonajeAplicar +
            '&idPartida=' + idPartida +
            '&jugadorAplica=' + jugadorAplica +
            '&coordenadas=' + listCeldas + '&accionRealizar=' + accion);
    }
}
