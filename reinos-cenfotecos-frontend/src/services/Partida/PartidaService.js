import { Create, Retrieve } from '../ControlActions.js';
const controller = "/api/partida";
// se exportan los métodos que se comunican con el API por medio de axios
export default {


    actualizarPartida(partida) {
        return Create(controller, `actualizarPartida`, null, partida);
    },
    crearPartida(nombre1, nombre2, ganador) {
        return Create(controller, `crearPartida?nombre1=${nombre1}&nombre2=${nombre2}&ganador=${ganador}`);
    },
    fachadaCrearPartidaRapida() {
        return Create(controller, `fachadaCrearPartidaRapida?`);
    },
    getPartidaById(idPartida) {
        return Retrieve(controller, `getPartidaById?idPartida=${idPartida}`);
    },
    reiniciarPartidaMemento(id) {
        return Retrieve(controller, `mementoReiniciarPartida?id=${id}`);
    },
}