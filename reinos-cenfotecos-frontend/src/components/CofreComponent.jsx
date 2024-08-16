import React, { useState, useEffect } from 'react';
import Button from "./ButtonComponent";
import Images from "./ImagesComponent.jsx";
import { FragmentoDado } from "../config/Config.js";
import DadosService from "../services/Cofres/CofreService";
import { getItem, setItem } from '../services/UseLocalStorage.js';
import useInterval from "../services/useInterval";
import { Castillo } from '../config/Config.js';


const CofreComponent = ({ changeGameboardSate }) => {


    const [turn, setTurn] = useState(1);
    const [ataqueEspecial, setAtaqueEspecial] = useState([]);
    const [ataqueNormal, setAtaqueNormal] = useState([]);
    const [movimiento, setMovimiento] = useState([]);
    const [artilleria, setArtilleria] = useState([]);
    const [tanque, setTanque] = useState([]);
    const [infanteria, setInfanteria] = useState([]);
    const [totalMovimientos, setTotalMovimientos] = useState(0);
    const [castillo, setCastillo] = useState([]);

    useInterval(() => {
        // console.log("verificación de turno")
        if (getItem('Partida')) {
            setTurn(getItem("Partida").turno);
            setAtaqueEspecial(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "AtaqueEspecial"))
            setAtaqueNormal(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "AtaqueNormal"))
            setMovimiento(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "Movimiento"))
            setArtilleria(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "Artilleria"))
            setInfanteria(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "Infanteria"))
            setTanque(getItem("Partida")["jugador" + turn].cofre.listaComposicion.filter(partida => partida.tipo == "Tanque"))
            subir();
            let castle;
            let p1 = getItem("Partida").jugador1;
            let p2 = getItem("Partida").jugador2;
            if (turn == 1) {
                setCastillo({ color: 1, vidas: p1.vidaCastillo });
            } else {
                setCastillo({ color: 2, vidas: p2.vidaCastillo })
            }

        }
    }, 500)

    function subir() {
        let total = 0;
        movimiento.map(function (item) {
            total += item.valor;

        })
        setTotalMovimientos(total)
    }


    return (

        <div className="cardCofre row" >
            <div className="col-2"></div>
            <div className="col-3" id="A">
                <div className="Ccontainer">
                    <p>Ataque normal</p>
                    <p>{ataqueNormal.length}</p>
                </div>
            </div>
            <div className="col-3" id="AE">
                <div className="Ccontainer">
                    <p>
                        <img className="castleChest" src={Castillo["castillo" + castillo.color + "" + castillo.vidas]} alt="" />
                    </p>
                </div>
            </div>
            <div className="col-3" id="AE">
                <div className="Ccontainer">
                    <p>Movimientos</p>
                    <p>{totalMovimientos}</p>
                </div>
            </div>


            <div className="col-2"></div>
            <div className="col-3">
                <div className="Ccontainer">
                    <p>Infanteria</p>
                    <p>{infanteria.length}/2</p>
                </div>
            </div>
            <div className="col-3">
                <div className="Ccontainer">
                    <p>Artillero</p>
                    <p>{artilleria.length}/3</p>
                </div>
            </div>
            <div className="col-3">
                <div className="Ccontainer">
                    <p>Tanque</p>
                    <p>{tanque.length}/4</p>
                </div>
            </div>
        </div>

    );
}


export default CofreComponent;