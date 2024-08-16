import React, { Fragment, useState } from 'react';
import '../assets/sass/SelectFigureComponent.scss';
import { getItem, setItem } from '../services/UseLocalStorage.js'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Personaje } from "../config/Config";
import useInterval from "../services/useInterval";
import CofreService from '../services/Cofres/CofreService';
import ActualizarPartida from '../services/ActualizarPartida.js';


const SelectPersonaje = ({ setPjNumberState, pjNumber, gameBoardState, changeGameboardSate }) => {
    const [artilleria, setArtilleria] = useState([]);
    const [tanque, setTanque] = useState([]);
    const [infanteria, setInfanteria] = useState([]);
    const [turn, setTurn] = useState(1);
    useInterval(() => {
        if (getItem('Partida')) {

            setTurn(getItem("Partida").turno);
            setArtilleria(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "Artilleria"))
            setInfanteria(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "Infanteria"))
            setTanque(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "Tanque"))

        }
    }, 200)
    const invocar = (num) => {
        let length = 0;
        let min = 7;


        if (num == 1) {
            length = artilleria.length;
            min = 3;

        } else if (num == 2) {
            length = infanteria.length;
            min = 2;

        } else if (num == 3) {
            length = tanque.length;
            min = 4;

        }

        if (length >= min) {
            setPjNumberState(num);
            changeGameboardSate({ accion: '', permitido: false });
            changeGameboardSate({ accion: 'Invocar', permitido: true });

        } else {
            toast.error("No tiene suficientes piezas para invocar");
        }

    }




    return (
        <Fragment>
            <div className='selectFigureContainer' id="ContenedorPjs">

                <button
                    className={['selectFigureBtn', pjNumber == 2 ? 'selectedFigure' : ''].join(' ')}
                    key={2}
                    onClick={() => invocar(2)}
                >
                    <img src={turn == 1?Personaje.infanteria_info : Personaje.infanteria2_info} />
                </button>
                <button
                    className={['selectFigureBtn', pjNumber == 1 ? 'selectedFigure' : ''].join(' ')}
                    key={1}
                    onClick={() => invocar(1)}
                >
                    <img src={turn == 1?Personaje.artillero_info : Personaje.artillero2_info} />
                </button>
                <button
                    className={['selectFigureBtn', pjNumber == 3 ? 'selectedFigure' : ''].join(' ')}
                    key={3}
                    onClick={() => invocar(3)}
                >
                    <img src={turn == 1?Personaje.tanque_info : Personaje.tanque2_info} />
                </button>

            </div>
            <ToastContainer autoClose={2000} />
        </Fragment>
    );
}

export default SelectPersonaje;