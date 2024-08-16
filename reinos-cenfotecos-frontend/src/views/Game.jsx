import React, { useState, useEffect } from 'react';
import { getItem } from "../services/UseLocalStorage";
import BoardComponent from "../components/BoardComponent";
import SeleccionAccion from "../components/SeleccionAccion";
import DadoActions from '../components/DadoActions';
import CofreComponent from '../components/CofreComponent';
import ActionsComponent from "../components/ActionsComponent";
import Modal from "react-modal";
import useInterval from "../services/useInterval";
import { MODAL_STYLES, Estados } from "../config/Config"
import ActualizarPartida from '../services//ActualizarPartida';
const Game = (props) => {

    const { history, match } = props;
    const [modalGanador, setModalGanador] = useState(false);
    const [turno, setTurno] = useState();
    const [estadoP, setEstadoP] = useState();
    const [figurePositionState, setFigurePositionState] = useState(0);
    const [figureNumberState, setFigureNumberState] = useState(0);
    const [pjNumberState, setPjNumberState] = useState(0);
    const [mirrorFigureState, setMirrorFigureState] = useState(false);
    const [gameBoardState, setgameBoardState] = useState({ accion: '', permitido: false });

    /*const armyList = ["Infantería", "Artillero", "Tanque", "Infantería", "Artillero", "Infantería"];
    const actionsList = ["Movimiento", "Ataque", "Ataque especial", "Movimiento", "Ataque", "Ataque especial"];

    const [showNumericDiceState, setShowNumericDiceState] = useState(false);
    const [blockDicesState, setBlockDicesState] = useState(false);
    const [blockMovDiceState, setBlockMovDiceState] = useState(false);
    */

    useEffect(() => {
        if (!getItem('Partida')) {

            history.goBack() ? history.goBack() : history.push("/")
        }
    });
    const terminar = () => {
        localStorage.clear();
        history.push("/");
    };
    useInterval(() => {
        // console.log("verificación de turno")
        if (window.location.href == "http://localhost:3000/#/game" || window.location.href == "https://reinos-cenfotecos-develop.herokuapp.com/#/game") {
            if (getItem('Partida')) {
                if (estadoP != getItem('Partida').estado) {
                    setEstadoP(getItem('Partida').estado);
                    if (getItem('Partida').estado == 0) {
                        setModalGanador(true);
                    }
                }
            }
        }
    }, 1000)

    const reiniciarPartida = async () => {
        let id = getItem('Partida').id + 0;

        await ActualizarPartida.reiniciarPartida(id);
        setModalGanador(false);
    };
    return (
        <>
            <div className="mainContainer">
                <div className="mainContainerContent">
                    <div className="mainContainerContentLeft">
                        <div className="mainContainerContentLeftBoard">
                            <BoardComponent
                                figurePosition={figurePositionState}
                                setFigurePositionState={setFigurePositionState}
                                figureNumber={figureNumberState}
                                mirrorFigure={mirrorFigureState}
                                gameBoardState={gameBoardState}
                                changeGameboardSate={(board) => setgameBoardState(board)}
                                personajeSelected={pjNumberState}

                            />
                        </div>
                        <SeleccionAccion
                            figureNumberState={figureNumberState}
                            figurePositionState={figurePositionState}
                            mirrorFigureState={mirrorFigureState}
                            setFigurePositionState={setFigurePositionState}
                            setMirrorFigureState={setMirrorFigureState}
                            setFigureNumberState={setFigureNumberState}
                            gameBoardState={gameBoardState}
                            changeGameboardSate={setgameBoardState}
                            setPjNumberState={setPjNumberState}
                            pjNumberState={pjNumberState}

                        />
                    </div>
                    <div className="mainContainerContentRight">
                        <div className="mainContainerContentRightDices"></div>
                        <div className="mainContainerContentRightDices">
                            <DadoActions />
                        </div>
                        <div className="mainContainerContentRightChest">
                            <div className="chestinside">


                                <div className="mainContainerContentRightChestInfo">
                                    <CofreComponent />
                                </div>
                                <div className="mainContainerContentRightChestActions">
                                    <ActionsComponent
                                        setFigurePositionState={setFigurePositionState}
                                        setMirrorFigureState={setMirrorFigureState}
                                        changeGameboardSate={(board) => setgameBoardState(board)}
                                    />
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {
                <Modal
                    isOpen={modalGanador}
                    shouldCloseOnOverlayClick={false}
                    onRequestClose={() => setModalGanador(false)}
                    style={MODAL_STYLES}
                    className="modal-dialog"
                >
                    <div className="modal-content">
                        <div className="modal-header">
                            <div className="modal-title">{"Partida terminada"}</div>
                            <button
                                type="button"
                                onClick={() => terminar()}
                                className="close"
                            >
                                &times;
                            </button>
                        </div>
                        <div className="modal-body">

                            <fieldset>
                                <div className="d-flex justify-content-center">
                                    <img src={Estados.ganador} alt="img winner" />

                                </div>
                                <div className="d-flex justify-content-center">
                                    {getItem('Partida') ? ( getItem('Partida').ganador > 0 ? <p className="h2 d-inline">Ganó: {getItem('Partida')["jugador" + getItem('Partida').ganador].nombre}</p> : ""):""}

                                </div>
                                <div className="d-flex justify-content-center">
                                    <p className="h6 d-inline">EZ mid</p>

                                </div>
                            </fieldset>



                        </div>
                        <div className="modal-footer d-flex justify-content-center">
                            <div className="m-0">
                                <button type="button" onClick={() => terminar()} className="btn btn-primary p-3 mr-2">
                                    Terminar
                                    </button>
                                <button type="button" onClick={() => reiniciarPartida()} className=" btn btn-primary p-3 ml-2">
                                    Reiniciar
                                    </button>
                            </div>
                        </div>
                    </div>
                </Modal>
            }
        </>
    );
}

export default Game;