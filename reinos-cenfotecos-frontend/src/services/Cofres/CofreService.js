import { Create, Retrieve } from '../ControlActions.js';
const controller = "/api/partida";
// se exportan los métodos que se comunican con el API por medio de axios
export default {


    mostrarCofre(dados,idPartida, jugador) {
        return Create(controller, "mostrarCofre?idPartida="+idPartida+"&jugador="+jugador, null, dados);
    },
    quitarDado(dados,idPartida, jugador) {
        return Create(controller, "quitarDado?idPartida="+idPartida+"&jugador="+jugador, null, dados);
    },
    quitarMov(dados,idPartida, jugador) {
        return Create(controller, "quitarMov?idPartida="+idPartida+"&jugador="+jugador, null, dados);
    }
}