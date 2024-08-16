import React from 'react';
//implementado por Junior

const Button = (props) => {
    return (
        <button disabled={props.deshabilitado} id={props.id} className="dado btn btn-primary p-1"  onClick={props.onClick}>
            {props.texto}
        </button>
    )
}
export default Button;