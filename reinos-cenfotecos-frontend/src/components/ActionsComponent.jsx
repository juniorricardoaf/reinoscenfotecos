import React, { useState } from 'react';
import "../assets/sass/FigureComponent.scss"
import figPosList from '../assets/figPosList';
import useInterval from "../services/useInterval";
import { getItem, setItem } from '../services/UseLocalStorage.js'
import { ToastContainer, toast } from 'react-toastify';
import $ from 'jquery';

const ActionsComponent = ({ changeGameboardSate, setFigurePositionState, setMirrorFigureState }) => {

    const [movimientos, setMovimientos] = useState([]);
    const [ataques, setAtaques] = useState([]);
    const [turn, setTurn] = useState(1);
    useInterval(() => {
        if (getItem('Partida')) {

            setTurn(getItem("Partida").turno);
            setMovimientos(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "Movimiento"))
            setAtaques(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "AtaqueNormal"))


        }
    }, 1000)

    const hablitarInvocacion = () => {
        changeGameboardSate({ accion: '', permitido: false });
        changeGameboardSate({ accion: 'SelectPJ', permitido: false });
        setFigurePositionState(0);
        setMirrorFigureState(0);
    }

    const habilitarMover = () => {
        let length = 0;

        length = movimientos.length;

        if (length >= 1) {
            changeGameboardSate({ accion: '', permitido: false });
            changeGameboardSate({ accion: 'Mover', permitido: true });
            toast.success('Seleccione la tropa que desea mover.');

        } else {
            toast.error("No tiene dados para moverte");
        }



    }

    const habilitarAtacar = () => {
        let length = 0;

        length = ataques.length;

        if (length >= 1) {
            changeGameboardSate({ accion: '', permitido: false });
            changeGameboardSate({ accion: 'Atacar', permitido: true });
            toast.success('Seleccione un personaje con el que atacar.');

        } else {
            toast.error("No tiene dados para atacar");
        }


    }

    return (
        <>
            <div className="containerButtonInvocacion" id="botonInvocar">
                <button className="invocacionButton" id="btnInv" onClick={hablitarInvocacion}>Invocar</button>
                <button className="invocacionButton" id="btnMov" onClick={habilitarMover}>Moverse</button>
                <button className="invocacionButton" id="btnAtk" onClick={habilitarAtacar}>Atacar</button>
            </div>
        </>
    );
}


export default ActionsComponent;