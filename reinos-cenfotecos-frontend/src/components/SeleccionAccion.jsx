import React, { Fragment, useEffect, useState, useRef } from 'react';
import "../assets/sass/FigureComponent.scss"
import FigureComponent from "../components/FigureComponent";
import SelectFigureComponent from '../components/SelectFigureComponent';
import SelectPersonaje from "../components/SelectPersonaje";
import InfoPersonaje from "../components/InfoPersonajeComponent";

const SeleccionAccion = ({ figureNumberState,figurePositionState,mirrorFigureState,setFigurePositionState,setMirrorFigureState,gameBoardState,changeGameboardSate,setPjNumberState,pjNumberState,setFigureNumberState }) => {

    return (
        <>
            {gameBoardState.accion == 'Invocar' && gameBoardState.permitido == true ? <div className="mainContainerContentLeftDetails row">
                <div className="col-1"></div>
                <div className="mainContainerContentLeftDetailsLeft col-4">
                    <FigureComponent
                        figureNumber={figureNumberState}
                        figurePosition={figurePositionState}
                        mirrorFigure={mirrorFigureState}
                        setFigurePosition={(position) => setFigurePositionState(position)}
                        setFigureSwitched={(switched) => setMirrorFigureState(switched)}
                     
                    />
                </div>
                <div className="mainContainerContentLeftDetailsRight col-7">
                    <SelectFigureComponent
                        setFigureNumber={(number) => setFigureNumberState(number)}
                        figureNumber={figureNumberState}
                 
                    />
                </div>
            </div> :
                <>{gameBoardState.accion == 'SelectPJ' && gameBoardState.permitido == false ? <div className="mainContainerContentLeftDetails row">
                    <div className="col-1"></div>
                    <div className="mainContainerContentLeftDetailsLeft col-11">
                        <SelectPersonaje
                            setPjNumberState={(number) => setPjNumberState(number)}
                            pjNumber={pjNumberState}
                            gameBoardState={gameBoardState}
                            changeGameboardSate={(board) => changeGameboardSate(board)}
                        />
                    </div>

                </div> :

                    <div className="mainContainerContentLeftDetails">
                        <InfoPersonaje   gameBoard={gameBoardState}/>
                    </div>

                }
                </>
            }
        </>
    );
}


export default SeleccionAccion;