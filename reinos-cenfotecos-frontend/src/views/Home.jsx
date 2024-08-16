import React, { useEffect, useState } from 'react';
import {
    User,
    Users,
    WifiOff,
    Wifi,
    Play,
    Pause
} from "react-feather";
import { Link, useHistory } from "react-router-dom";
import PartidaService from "../services/Partida/PartidaService";
import { setItem } from "../services/UseLocalStorage";

const Home = () => {
    let history = useHistory();

    const iniciarJuego = () => {
        history.push("/registroLocal");
    };

    const iniciarJuegoRapido = () => {

        PartidaService.fachadaCrearPartidaRapida().then((res) => {
            setItem("Partida", res.data)
            history.push("/game");

        }).catch((e) => {
            console.log("Error", e)

        });
    };



    return (

        <section className="contact-area pagina">
            <div id="menu" className="row justify-content-center align-items-center mt-5 mb-5 mx-0">
                <div className="col-xl-4  col-lg-6 col-md-10 col-xs-12  formContainer">
                    <div className="container-fluid my-3 col-md-12 d-flex">
                        <h2 className="">Bienvenido</h2>
                    </div>
                    <section>

                        <div className="container-fluid  justify-content-center align-items-center mt-2 mb-5">




                            <div className="form-group row mx-0">
                                <button className="btn btn-primary col-12 my-1 " onClick={() => iniciarJuegoRapido()}> Juego Rápido <Users /><WifiOff /></button>
                                <button className="btn btn-primary col-12 my-1" onClick={() => iniciarJuego()}> Juego Local <Users /><WifiOff /></button>
                                {/*<button className="btn btn-primary col-12 my-1" onClick={() => playSonido()}> Multijugador <User /> <Wifi /></button>
                                <button className="btn btn-primary col-12 my-1" onClick={() => iniciarJuegoRapido()}> Unirse <User /><Wifi /></button>*/}
                               
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>

    );

}
export default Home;
