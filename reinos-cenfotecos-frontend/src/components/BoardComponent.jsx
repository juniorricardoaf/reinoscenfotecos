import React, { useState, useEffect, Fragment, useRef } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import CellComponent from './CellComponent';
import CellCastillo from './CellCastillo';
import figPosList from '../assets/figPosList';
//import figureService from '../services/Figure/FigureService.js';
import '../assets/sass/BoardComponent.scss';
import 'react-toastify/dist/ReactToastify.css';
import Spinner from '../components/visuales/Spinner.js';
import useInterval from "../services/useInterval";
//import del servicio de Personajes
import CharacterService from '../services/Character/CharacterService.js';
import ActualizarPartida from '../services/ActualizarPartida.js';
//import del localStorage
import { getItem, setItem } from '../services/UseLocalStorage.js';
import { Personaje } from '../config/Config.js';
import CofreService from '../services/Cofres/CofreService';

const BoardComponent = ({ figurePosition, figureNumber, mirrorFigure, gameBoardState, changeGameboardSate, personajeSelected, setFigurePositionState }) => {

    const [turn, setTurn] = useState(1);
    const [estado, setEstado] = useState(1);
    // Estados que se manejan en la aplicación para que la información sea persistente
    const [boardState, setBoardState] = useState([]);
    const [castillosBoard, setcastillosBoard] = useState([]);
    const [spinner, showSpinner, hideSpinner] = Spinner();


    useInterval(() => {
        // console.log("verificación de turno")
        //if (getItem('Partida')) {
        if (turn != getItem("Partida").turno) {
            setTurn(getItem("Partida").turno);
            fillBoard();
        }
        if (estado != getItem("Partida").estado) {
            setEstado(getItem("Partida").estado);
            fillBoard();
        }
        /*else if (turn != getItem("Partida").turno) {

            fillBoard();
        }*/

        //}
    }, 1000)

    // El useEffect es para que ejecute una acción después de haberse creado el componente
    useEffect(() => {
        fillBoard();

        //funcion para utilizar las teclas
        document.addEventListener("keyup",function (e) {
            let key = e.keyCode;
            const btnMov= document.getElementById("btnMov");
            const btnInv= document.getElementById("btnInv");
            const btnAtk= document.getElementById("btnAtk");
            if (key === 77) {
                if(btnMov){
                    console.log("Mov");
                    btnMov.click();
                }
            }else if(key === 65){
                if(btnAtk){
                    console.log("Attack");
                    btnAtk.click();
                }

            }else if(key===73){
                if(btnInv){
                    console.log("Inv");
                    btnInv.click();
                }
            }
        });
    }, [])

    // Rellena el tablero en forma de matriz con las coordenadas de las casillas, si esta está rellena y por cual jugador fue rellenada 
    const fillBoard = () => {
        if (getItem("Partida")) {
            let arrayCopy = getItem("Partida").tablero.casillas;
            let castillos = [];
            let p1 = getItem("Partida").jugador1;
            let p2 = getItem("Partida").jugador2;
            setTurn(getItem("Partida").turno);
            //console.log(arrayCopy); 
            // arrayCopy[p1.row][p1.column]["castle"] = { color: 1, top: '20px', left: "-2px", vidas: p1.vidaCastillo };
            //arrayCopy[p2.row][p2.column]["castle"] = { color: 2, top: '-20px', left: "-22px", vidas: p2.vidaCastillo };
            castillos[1] = arrayCopy[p1.row];
            castillos[0] = arrayCopy[p2.row];

            castillos[1][p1.column]["castle"] = { color: 1, top: '-44px', left: "-2px", vidas: p1.vidaCastillo };
            castillos[0][p2.column]["castle"] = { color: 2, top: '-44px', left: "-22px", vidas: p2.vidaCastillo };

            setBoardState(arrayCopy);
            setcastillosBoard(castillos);
        }
    }
    // Método que marca la casilla seleccionada junto con las coordenadas de la figura seleccionada
    const markCell = async (casilla) => {


        let row = casilla.row;
        let column = casilla.column;
        let boardCopy = boardState.slice();
        let tPersonaje = personajeSelected;
        let coords = casilla.row + '-' + casilla.column;
        let partida = getItem('Partida');
        let jugador = turn;

        if (gameBoardState.accion == 'Invocar' && gameBoardState.permitido == true) {
            let listCeldasLlenas = [];

            /**
             *   Se obtiene la lista de coordenadas de una figura en figPosList apartir del número de figura seleccionada 
             *   y después de eso en .positions se obtiene un objeto apartir de que si la figura esta en modo espejo o no
             *   y en qué posición fue seleccionada 
             * */
            ;
            let cerocero = [[0, 0]];
            let pots = figPosList[turn == 1 ? 0 : 1][figureNumber].positions[!mirrorFigure ? 0 : 1][figurePosition];
            // Añade una posición más para que se valide la casilla que fue seleccionada
            //console.log(pots);
            // console.log(cerocero);
            let pos = cerocero.concat(pots);
            //console.log(pos);
            // Se hace una validación de las posiciones 
            if (verifyPositions(boardCopy, pos, row, column)) {
                let validationResponse = false;

                for (let i = 0; i < pos.length; i++) {
                    // Si todo está bien se establece la casilla como "llena" y se le asigna el jugador que está en turno.
                    let rowFilled;
                    let collFilled;
                    let coord;
                    boardCopy[row + pos[i][0]][column + pos[i][1]].filled = true;
                    boardCopy[row + pos[i][0]][column + pos[i][1]].jugador = turn;

                    rowFilled = boardCopy[row + pos[i][0]][column + pos[i][1]].row;
                    collFilled = boardCopy[row + pos[i][0]][column + pos[i][1]].column;
                    coord = rowFilled + '-' + collFilled;
                    listCeldasLlenas[i] = coord;

                }
                // console.log(pos);
                await CharacterService.crearPersonaje(tPersonaje, partida.id, jugador, listCeldasLlenas).then((res => {
                    if (typeof res.data !== 'undefined') {
                        validationResponse = true;
                        listCeldasLlenas = '';
                        casilla.obj = res.data;

                        let array = [];

                        if (tPersonaje == 1) {

                            array = Array(3).fill({ tipo: "Artilleria" })
                        } else if (tPersonaje == 2) {

                            array = Array(2).fill({ tipo: "Infanteria" })
                        } else if (tPersonaje == 3) {

                            array = Array(4).fill({ tipo: "Tanque" })
                        }

                        let idPartida = getItem("Partida").id;
                        let jugador = turn;
                        changeGameboardSate({ accion: '', permitido: false });
                        changeGameboardSate({ accion: 'Invocar', permitido: true });
                        //restar en el composite
                        CofreService.quitarDado(array, idPartida, jugador)
                            .then((data) => {
                                ActualizarPartida.getPartidaById(getItem("Partida").id);
                            })
                            .catch(error => console.log(error));


                        //  ActualizarPartida.getPartidaById(getItem("Partida").id);

                        //  fillBoard();

                    } else {
                        toast.error(res.tituloExcepcion);
                        for (let i = 0; i < pos.length; i++) {
                            listCeldasLlenas = '';
                            // Si todo está bien se establece la casilla como "llena" y se le asigna el jugador que está en turno.
                            boardCopy[row + pos[i][0]][column + pos[i][1]].filled = false;
                            boardCopy[row + pos[i][0]][column + pos[i][1]].jugador = 0;
                            // boardCopy[row + pos[i][0]][column + pos[i][1]].image = '';
                        }
                    }

                    changeGameboardSate({ accion: '', permitido: false });
                })).catch((error => {
                    toast.error("Algo ocurrió mal, porfavor inténtelo nuevamente.");
                }));


            } else {
                listCeldasLlenas = '';
                pos = null;
            }
            setFigurePositionState(0);
        } else if (gameBoardState.accion == 'Mover' && gameBoardState.permitido == true) {
            //console.log(casilla.obj);
            if (casilla.obj == undefined || casilla.obj == null) {
                toast.error('No existe una tropa para mover en la casilla!');
            } else {
                setItem('casillaMover', casilla);
                toast.success('Seleccione una casilla para mover el personaje.');
                changeGameboardSate({ accion: 'moviendo', permitido: true });
            }

        } else if (gameBoardState.accion == 'moviendo' && gameBoardState.permitido == true) {
            if (casilla.filled == true && casilla.obj == null) {
                let partida = getItem('Partida');
                let jugadorAplica = turn;
                let dataCasilla = getItem('casillaMover');//obtengo la casilla vieja
                let coordsviejas = getItem('casillaMover').row + '-' + getItem('casillaMover').column;
                let casillasavalidar = [coordsviejas, row + '-' + column];
                let validationResponse = false;
                let idPersonajeAplica = getItem('casillaMover');
                let idPersonajeAplicar = getItem('casillaMover');

                await CharacterService.realizarAccion(idPersonajeAplica.obj.id, idPersonajeAplicar.obj.id, partida.id, jugadorAplica, casillasavalidar, 'Mover').then((res => {
                    if (typeof res.data !== 'undefined') {
                        casilla.obj = res.data[0];
                        //  casilla.image = { uri: pj, idCharacter: res.data.id, dataCharacter: res.data, player: jugador };//QUITAR
                        //ActualizarPartida.getPartidaById(getItem("Partida").id);
                        setBoardState(boardCopy);
                        validationResponse = true;
                    } else {
                        toast.error(res.tituloExcepcion);
                    }

                })).catch((error => {
                    toast.error("Algo ocurrió mal, favor inténtelo nuevamente.");
                }));
                if (validationResponse) {
                    boardCopy[row][column].obj = dataCasilla.obj;//la seteo en la casilla nueva
                    boardCopy[dataCasilla.row][dataCasilla.column].obj = null;

                    setBoardState(boardCopy);
                    //RESTAR MOVIMIENTO BACKEND
                    
                    let arrayMov = [];
                    arrayMov = Array(1).fill({ tipo: "Movimiento" })
                    CofreService.quitarMov(arrayMov, partida.id, jugador)
                    .then((data) => {
                        ActualizarPartida.getPartidaById(getItem("Partida").id);
                    })
                    .catch(error => console.log(error));
                    //Termina restar

                    changeGameboardSate({ accion: '', permitido: false });
                }

            } else {
                toast.error('¡Seleccione una casilla válida para mover el personaje!');
            }

        } else if (gameBoardState.accion == 'Atacar' && gameBoardState.permitido == true) {

            if (casilla.obj == undefined || casilla.obj == null) {
                toast.error('No existe una tropa con la que atacar en esa casilla');
            } else {
                if (casilla.obj.jugador == turn) {
                    let jugadorEnemigo = getItem("Partida")["jugador" + (turn == 1 ? 2 : 1)];


                    if (row == jugadorEnemigo.row && column == jugadorEnemigo.column) {
                        // si el pj se encuentra en la casilla del castillo le resta una vida
                        let partidaNew = getItem("Partida");
                        partidaNew["jugador" + (turn == 1 ? 2 : 1)].vidaCastillo = partidaNew["jugador" + (turn == 1 ? 2 : 1)].vidaCastillo - 1;
                        await ActualizarPartida.actualizarPartida(partidaNew);


                        let idPartida = getItem("Partida").id;
                        let array = Array(1).fill({ tipo: "AtaqueNormal" });
                        CofreService.quitarDado(array, idPartida, turn)
                            .then((data) => {
                            })
                            .catch(error => console.log(error))
                            .finally(() => {
                                ActualizarPartida.getPartidaById(getItem("Partida").id);
                            });
                        setTimeout(() => {
                            fillBoard();
                        }, 1000);
                        toast.success('Atacó el castillo enemigo');
                    } else {

                        setItem('casillaAtacante', casilla);
                        toast.success('Seleccione un personaje para atacar');
                        changeGameboardSate({ accion: 'atacando', permitido: true });
                    }
                } else {
                    toast.error('No puede seleccionar esta casilla');
                    changeGameboardSate({ accion: '', permitido: false });
                }


            }

        } else if (gameBoardState.accion == 'atacando' && gameBoardState.permitido == true) {
            if (casilla.filled == true && casilla.obj != null && turn) {
                let partida = getItem('Partida');
                let jugador = turn;
                let dataCasilla = getItem('casillaAtacante');//obtengo la casilla vieja
                let coordsAtacante = getItem('casillaAtacante').row + '-' + getItem('casillaAtacante').column;
                let casillasavalidar = [coordsAtacante, row + '-' + column];
                let validationResponse = false;
                let personajeAplica = getItem('casillaAtacante');
                let personajeAplicar = casilla;
                await CharacterService.realizarAccion(personajeAplica.obj.id, personajeAplicar.obj.id, partida.id, jugador, casillasavalidar, 'Atacar').then((res => {
                    if (typeof res.data !== 'undefined') {
                        personajeAplicar = res.data[1];
                        personajeAplica = res.data[0];
                        //  casilla.image = { uri: pj, idCharacter: res.data.id, dataCharacter: res.data, player: jugador };//QUITAR

                        let idPartida = getItem("Partida").id;
                        let array = Array(1).fill({ tipo: "AtaqueNormal" });
                        CofreService.quitarDado(array, idPartida, jugador)
                            .then((data) => {
                            })
                            .catch(error => console.log(error))
                            .finally(() => {
                                ActualizarPartida.getPartidaById(getItem("Partida").id);
                            });
                        // ActualizarPartida.getPartidaById(getItem("Partida").id);
                        //setBoardState(boardCopy);
                        validationResponse = true;

                    } else {
                        if (typeof res.tituloExcepcion == 'undefined') {
                            casilla.obj = null;
                            validationResponse = true;
                        } else {
                            toast.error(res.tituloExcepcion);
                        }

                    }

                })).catch((error => {
                    toast.error("Algo ocurrió mal, porfavor inténtelo nuevamente.");
                }));
                if (validationResponse) {
                    boardCopy[row][column].obj = personajeAplicar;//la seteo en la casilla nueva
                    boardCopy[getItem('casillaAtacante').row][getItem('casillaAtacante').column].obj = personajeAplica;
                    //boardCopy[dataCasilla.row][dataCasilla.column].obj = null;
                    if (personajeAplicar == null) {
                        toast.success('¡Tropa enemiga Derrotada!');
                    } else {
                        toast.success('¡Tropa enemiga atacada!');
                    }
                    setBoardState(boardCopy);

                    //aqui llamar al metodo del backend y en el then de la promesa llamar al metodo de abajo para que actualice el local storage con el 
                    // ActualizarPartida.getPartidaById(getItem("Partida").id);
                    changeGameboardSate({ accion: '', permitido: false });

                }
            } else {
                toast.error('Selecione una tropa válida para atacar ');
            }
        } else {
            toast.error('¡Tablero bloqueado, seleccione una acción para ejecutar!');
        }
        setItem('casillaSelected', casilla);
    }
    /**
     *   Este método valida si la figura puede ser introducida sin que esta salga de los bordes permitidos
     *   y a su vez verifica que no se pueda añadir una figura encima de una ya existente
     * */
    const verifyPositions = (board, pos, row, column) => {
        for (let i = 0; i < pos.length; i++) {
            /**
             * En la línea del if se valora primero si la fila es válida ya que si se intenta acceder a una
             * columna de una fila que sea undefined la aplicación se cae.
             * Y luego se valora si la columna es válida
             * 
             * Y en el else if se valora si la casilla ya está ocupada para que no se pueda colocar 
             * una nueva figura encima sobreescribiendo la que ya había.
             */
            if (board[row + pos[i][0]] === undefined || board[row + pos[i][0]][column + pos[i][1]] === undefined) {
                toast.error('¡Posición inválida! Por favor seleccione otra casilla');
                return false;
            } else if (board[row + pos[i][0]][column + pos[i][1]].filled) {
                toast.error('¡Casilla ocupada! Por favor seleccione otra casilla');
                return false;
            }
        }

        /**
         * Aquí se valida que el jugador pueda poner su figura solo si está en el rango permitido
         * Ese rango se define apartir del camino creado, la figura se podrá colocar solo alrededor
         * de una casilla que esté llena.
         * También se valida que pueda colocar la figura en su propio camino.
         */

        // Estás son las coordenas a validar para ver si hay una casilla llena
        let isFilledList = [[0, +1], [0, -1], [+1, 0], [-1, 0]];
        let allowed = false;
        // Se toma el número de jugador en la ronda actual

        for (let i = 0; i < isFilledList.length; i++) {
            let boardRow = board[row + isFilledList[i][0]];
            // Se corrobora que la fila y la columna a validar no de undefined
            if (boardRow !== undefined && boardRow[column + isFilledList[i][1]] !== undefined) {
                // Y por último se valida si la casilla está llena y si le pertenece al jugador actual
                if (boardRow[column + isFilledList[i][1]].filled
                    && boardRow[column + isFilledList[i][1]].jugador == turn) {
                    allowed = true;
                    break;
                }
            }
        }

        /** 
         * En caso de que no se haya encontrado el rango permitido se envía un mensaje de alerta
         * Y a su vez valida que la fila no sean las iniciales para que no se envíe el mensaje de alerta
         * */
        if (!allowed && row != 19 && row != 0) {
            toast.error('¡Casilla no permitida! Por favor seleccione una casilla dentro del rango permitido');
            return false;
        } else if (allowed) {
            return true;
        } else if ((row == 19 && board[row][column].castle === undefined) || (row == 0 && board[row][column].castle === undefined)) {
            toast.error('¡Casilla no permitida! Debe colocar la figura en el castillo');
            return false;
        }

        return true;
    }

    return (
        <>
            {spinner}
            <div className="boardContainer">
                <div className="board" id="board" style={{ 'transform': 'rotateX(20deg) ' + (turn == 1 ? ' rotateZ(0deg)' : 'rotateZ(180deg)') }}>
                    <div className="back side"></div>
                    <div className="left side"></div>
                    <div className="front side"></div>
                    <div className="right side"></div>
                    <div className="top">
                        <div key={"red"} id={"red"} className="boardRow">
                            {castillosBoard[0] ?
                                castillosBoard[0].map((obj, j) =>
                                    <CellCastillo
                                        key={obj.row + '-' + obj.column}
                                        selectedCell={obj}
                                        onClick={() => { }}
                                    />
                                ) : ""
                            }
                        </div>
                        {
                            boardState.map((row, i) =>
                                <div key={i} id={i} className="boardRow">
                                    {
                                        row.map((obj, j) =>
                                            <CellComponent
                                                key={obj.row + '-' + obj.column}
                                                selectedCell={obj}
                                                onClick={() => markCell(obj)}
                                            />
                                        )
                                    }
                                </div>
                            )
                        }
                        <div key={"white"} id={"white"} className="boardRow">
                            {castillosBoard[1] ?
                                castillosBoard[1].map((obj, j) =>
                                    <CellCastillo
                                        key={obj.row + '-' + obj.column}
                                        selectedCell={obj}
                                        onClick={() => { }}
                                    />
                                ) : ""
                            }
                        </div>
                    </div>
                </div>
                {/* <div className="resetBtnContainer">
                    <button onClick={() => fillBoard([])}>Launch</button>
                </div> */}

            </div>
            {/* mensajes de alerta*/}
            <ToastContainer autoClose={2000} />
        </ >
    );
}


export default BoardComponent;