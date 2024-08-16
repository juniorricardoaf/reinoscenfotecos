import React, { Fragment, useState } from 'react';
import '../assets/sass/SelectFigureComponent.scss';
import { getItem, setItem } from '../services/UseLocalStorage.js'
const SelectFigureComponent = ({ setFigureNumber, figureNumber}) => {
    const array = Array(6).fill(0);
   // const [hide, sethide] = useState('hideSelectedFigure');
    return (
        <Fragment>
            <div className='selectFigureContainer' id="ContenedorFiguras">
                {
                    array.map((item, index) => (
                        <button
                            className={['selectFigureBtn', figureNumber == index ? 'selectedFigure' : ''].join(' ')}
                            key={index}
                            onClick={() => setFigureNumber(index)}
                        >
                            <img src={`${process.env.PUBLIC_URL}/assets/images/figura${index}.png`} />
                        </button>
                    ))
                }
            </div>
        </Fragment>
    );
}

export default SelectFigureComponent;