/**
 * Se importa el ControlActions para poder acceder 
 * a la instacia de axios ya previamente configurada
 */
import { Retrieve } from '../ControlActions';

// se exportan los métodos que se comunican con el API por medio de axios
export default {

    // Este método lo uso en BoardComponent línea 21 para que lo vayan a ver
     getData() {
        /**
         * Aquí es donde se coloca el resto de endpoint que necesitamos
         * Osea la ruta           |
         *                        v      
        service.$axios.get('/api/v1/civilizations').then((response) => {
            console.log(response.data);
        }).catch((error) => {
            console.log(error);
        });
         

        await Retrieve("/api/v1", "civilizations", "").then((res) => {
            console.log(res.data);
        }, (error) => {
            console.error(error);
        }
        );*/
        console.log('geting data...');

    },
    saveData() {
        console.log('Saving data...');
    }
}
