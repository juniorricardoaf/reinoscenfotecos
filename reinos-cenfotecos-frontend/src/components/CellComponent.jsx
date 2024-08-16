import React from 'react';
import '../assets/sass/CellComponent.scss';
import { Personaje, Castillo } from '../config/Config.js';

const CellComponent = ({ selectedCell, onClick }) => {


    let pj = "";

    if (selectedCell.obj != null) {
        //  console.log("obj",selectedCell.obj);
        if (selectedCell.obj.jugador == 1) {
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
        } else if (selectedCell.obj.jugador == 2) {
            switch (selectedCell.obj.tipoPersonaje) {
                case 'Artillero':
                    pj = Personaje.artillero2
                    break;
                case 'Tanque':
                    pj = Personaje.tanque2
                    break;
                case 'Infanteria':
                    pj = Personaje.infanteria2
                    break;
            }
        }


    } if (selectedCell.castle) {
        //   console.log("color", selectedCell.castle.color);
    }

    return (

        <div className="cellContainer" >
            {/* Se hace una validación en las clases para que el color de la casilla cambie dependiendo del jugador */}
            <div type="text" className={['cell', selectedCell.filled && selectedCell.jugador == 1 ? 'filledPlayerOne' : selectedCell.filled && selectedCell.jugador == 2 ? 'filledPlayerTwo' : ''].join(' ')}
                onClick={onClick} id={selectedCell.row + '-' + selectedCell.column}>
                {selectedCell.obj != null && pj != "" ?
                    <img src={pj} className="cellcharacterImage" style={{ 'transform': (selectedCell.obj.jugador == 2 ? 'rotateZ(180deg)' : '') }} />
                    : null
                }
            </div>
        </div>

    );
}


export default CellComponent;