import React from 'react';
import '../assets/sass/CellComponent.scss';
import { Personaje, Castillo } from '../config/Config.js';

const CellCastillo = ({ selectedCell, onClick }) => {


    let pj = "";

    if (selectedCell.obj != null) {
        //  console.log("obj",selectedCell.obj);
        switch (selectedCell.obj.tipoPersonaje) {
            case 'Artillero':
                pj = Personaje.artillero
                break;
            case 'Tanque':
                pj = Personaje.tanque
                break;
            case 'Infanteria':
                pj = Personaje.infanteria
                break;
        }

    } if (selectedCell.castle) {
        //   console.log("color", selectedCell.castle.color);
    }

    return (
        <>
            {selectedCell.castle ? (
                <>
                    <div className="cellContainer" >
                        {/* Se hace una validación en las clases para que el color de la casilla cambie dependiendo del jugador */}
                        <div type="text" className={['cell', selectedCell.filled && selectedCell.jugador == 1 ? 'filledPlayerOne' : selectedCell.filled && selectedCell.jugador == 2 ? 'filledPlayerTwo' : ''].join(' ')}
                            onClick={onClick} id={selectedCell.row + '-' + selectedCell.column}>


                        </div>


                        <img src={Castillo["castillo" + selectedCell.castle.color + "" + selectedCell.castle.vidas]} alt="" className="cellCastleImage"
                            style={{ marginLeft: selectedCell.castle.left, marginTop: selectedCell.castle.top, 'transform': (selectedCell.castle.color == 2 ? 'rotateZ(180deg)' : '') }} />
                        {/*"castillo" + selectedCell.castle.color + "" + selectedCell.castle.vidas*/}
                    </div>
                </>
            ) :
                <div className="cellContainer cellSinBorders" >
                </div>
            }
        </>
    );
}


export default CellCastillo;