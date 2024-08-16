import { getItem, setItem } from '../services/UseLocalStorage.js';
import PartidaService from "../services/Partida/PartidaService";


export default {

    actualizarPartida(partidaNew) {
        if (partidaNew != null && partidaNew != undefined) {
            //let data = {};
            //data.Partida = partidaNew
            partidaNew.jugador1.personajesEnJuego = null;
            partidaNew.jugador2.personajesEnJuego = null;
            partidaNew.jugador1.cofre = null;
            partidaNew.jugador2.cofre = null;
            partidaNew.tablero = null;
            PartidaService.actualizarPartida(partidaNew).then((res) => {
                if (res.data != null) {
                    // console.log("Partida Actualizada", res.data)
                    setItem("Partida", res.data)
                } else {
                    console.log("no se pudo actualizar", res)
                }


            }).catch((e) => {
                console.log("Error", e)
                /*setMensajeError({
                    error: true,
                    mensaje: "Ocurrió un error",
                });*/

            });
        }
    },

    getPartidaById(idPartida) {
        if (idPartida > 0) {
            //let data = {};
            //data.Partida = partidaNew
            PartidaService.getPartidaById(idPartida).then((res) => {
                if (res.data != null) {
                    // console.log("get partida", res.data)
                    setItem("Partida", res.data)
                } else {
                    console.log("no se pudo traer la partida", res)
                }


            }).catch((e) => {
                console.error("Error", e)
                /*setMensajeError({
                    error: true,
                    mensaje: "Ocurrió un error",
                });*/

            });
        }
    },
    
    reiniciarPartida(idPartida) {
        if (idPartida > 0) {
            //let data = {};
            //data.Partida = partidaNew
            PartidaService.reiniciarPartidaMemento(idPartida).then((res) => {
                if (res.data != null) {
                    // console.log("get partida", res.data)
                    localStorage.clear();
                    setItem("Partida", res.data)
                } else {
                    console.log("no se pudo traer la partida", res)
                }


            }).catch((e) => {
                console.error("Error", e)
                /*setMensajeError({
                    error: true,
                    mensaje: "Ocurrió un error",
                });*/

            });
        }
    },
}