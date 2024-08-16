import { Create, Retrieve } from '../ControlActions.js';
const controller = "/api/timer";
// se exportan los métodos que se comunican con el API por medio de axios
export default {

    iniciarTimer() {
        return Retrieve(controller, "iniciarTimer");
    },
    cambiarTimer() {
        return Retrieve(controller, "cambiarTurno");
    },

}