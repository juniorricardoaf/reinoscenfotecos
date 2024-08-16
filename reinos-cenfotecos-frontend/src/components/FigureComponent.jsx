import React, { Fragment, useEffect, useState, useRef } from 'react';
import "../assets/sass/FigureComponent.scss"
import figPosList from '../assets/figPosList';

const FigureComponent = ({ figureNumber, figurePosition, mirrorFigure, 
    setFigurePosition, setFigureSwitched }) => {

    const [rotationState, setRotationState] = useState(0);
    const [scaleState, setScaleState] = useState({ x: 1, y: 1 });
    /*  const [array, setArray] = useState([
          [["o"], ["o"], ["o"]],
          [["o"], ["o"], ["o"]],
          [["o"], ["o"], ["o"]]
      ]);
  */
    const mounted = useRef();
    useEffect(() => {
        if (!mounted.current) {
            mounted.current = true;
        } else {
            // Se aplican los estilos después de actualizar el componente

            document.getElementById('img').style.transform = `rotate(${rotationState * 90}deg) scaleX(${scaleState.x}) scaleY(${scaleState.y})`;
        }

    });

    // Método para hacer rotar la figura
    const rotate = (direction) => {
        /**
         *  Primero se establece la posición de la figura sumandole a la posición actual de 
         *  la figura (figurePosition) la dirección para que sepa hacia que dirección girar 
         *  (-1 = izquierda) o (1 = derecha)
         */
        let currentPosition = figurePosition + (direction == "left" ? (!mirrorFigure ? -1 : 1) : (!mirrorFigure ? 1 : -1));
        /**
         * Luego se verifica que si al sumar o restar la posición de la figura y esta
         * llega a estar en -1 o 4 que pase a ser 3 o 0 según corresponda.
         * Esto porque los indices de las posiciones van del 0 al 3 ya que solo hay 4 posiciones
         * 
         * posiciones = rotación
         */
        let position = currentPosition == -1 ? 3 : currentPosition == 4 ? 0 : currentPosition;
        //---------- Hasta aquí es para mostrar la figura en el tablero --------------//

        // Se establece la dirección de la rotación en el estado
        setRotationState(rotationState + (direction == "left" ? -1 : 1));

        // Se retorna la posición para que este dato sea capturado para el state de position
        return position;
    }

    const switchFigure = () => {
        /**
         * NOTA IMPORTANTE:
         * No sé que hice en esta validación así que mejor no tocar porque sí funciona
         */
        let rotation = Math.abs(rotationState % 2);

        if (mirrorFigure && rotation % 2 == 1 && scaleState.x == -1) {
            //console.log(1);
            setScaleState({ x: -1, y: -1 });
        } else if (!mirrorFigure && rotation % 2 == 1 && scaleState.y == -1) {
           // console.log(2);
            setScaleState({ x: -1, y: 1 });
        } else if (!mirrorFigure && rotation % 2 == 0 && scaleState.y == -1) {
           // console.log(3);
            setScaleState({ x: 1, y: -1 });
        } else if (mirrorFigure && rotation % 2 == 0 && scaleState.y == -1) {
           // console.log(4);
            setScaleState({ x: -1, y: -1 });
        } else if (!mirrorFigure && rotation % 2 == 0) {
          //  console.log(5);
            setScaleState({ x: -1, y: 1 });
        } else if (mirrorFigure && rotation % 2 == 0) {
           // console.log(6);
            setScaleState({ x: 1, y: 1 });
        } else if (!mirrorFigure && rotation % 2 == 1) {
           // console.log(7);
            setScaleState({ x: 1, y: -1 });
        } else if (mirrorFigure && rotation % 2 == 1) {
            //console.log(8);
            setScaleState({ x: 1, y: 1 });
        }

        // Se retorna el inverso del modo espejo actual
        return !mirrorFigure;
    }

    return (
        <>
            <div className="figureContainer">
                <div className="figureContainerTop">
                    <button
                        disabled={figureNumber == 1}
                        onClick={() => setFigurePosition(rotate("left"))}
                        className="btn btn-small bg-red btn-left btn-primary botonesfigurasrotacion"
                    ><i className="fas fa-undo"></i></button>

                    <div className="figureContainerTopImg">
                        <img id="img" src={`${process.env.PUBLIC_URL}/assets/images/figura${figureNumber}.png`} />
                    </div>
                    <button
                        disabled={figureNumber == 1}
                        onClick={() => setFigurePosition(rotate("right"))}
                        className="btn btn-small bg-blue btn-right btn-primary botonesfigurasrotacion"
                    ><i className="fas fa-redo"></i></button>
                </div>
                <button
                    disabled={figureNumber == 1}
                    onClick={() => setFigureSwitched(switchFigure())}
                    id ="botonInvertir"
                    className="btn btn-small bg-orange btnChangeFigure btn-primary botonesfigurasrotacion"
                ><i className="fas fa-exchange-alt"></i></button>
            </div>
        </>
    );
}


export default FigureComponent;