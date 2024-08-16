import { Create, Retrieve } from '../ControlActions.js';
const controller = "/api/dado";
// se exportan los métodos que se comunican con el API por medio de axios
export default {

    obtenerdados() {
        return Retrieve(controller, "obtenerdados");
    },

}