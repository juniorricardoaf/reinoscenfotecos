import React, { useState } from 'react';
import { getItem, setItem } from '../services/UseLocalStorage.js';
import useInterval from "../services/useInterval";
import { Personaje } from "../config/Config";


const InfoPersonaje = ({ gameBoard }) => {
    const [casilla, setCasilla] = useState();


    useInterval(() => {
        // console.log("verificación de turno")
        if (getItem('casillaSelected') != null) {
            setCasilla(getItem('casillaSelected'))
        } else {
            setCasilla(null)
        }
    }, 500)


    return (

        <div className="cardCofre row" >
            <div className="col-2"></div>
            { casilla != null ? (
                casilla.obj != null && casilla.obj != 0 ? <>
                    <div className="col-2 infoPersonaje">
                        {getItem('casillaSelected').obj.jugador == 1 ?
                            <img src={casilla.obj.tipoPersonaje == "Infanteria" ? Personaje.infanteria : (casilla.obj.tipoPersonaje == "Tanque" ? Personaje.tanque : Personaje.artillero)} />
                            : <img src={casilla.obj.tipoPersonaje == "Infanteria" ? Personaje.infanteria2 : (casilla.obj.tipoPersonaje == "Tanque" ? Personaje.tanque2 : Personaje.artillero2)} />
                        }
                    </div>
                    <div className="col-2">
                        <div className="Ccontainer infoPersonaje">

                            <p>Ataque: {casilla.obj.ataque}</p>
                            <p>Defensa: {casilla.obj.defensa}</p>
                            <p>Vida: {casilla.obj.vida}</p>
                        </div>
                    </div>
                </> : "") : ""}

        </div>

    );
}


export default InfoPersonaje;