import React, { useState } from 'react';
import { useForm } from "react-hook-form";
import ErrorForm from "../components/visuales/form/ErrorForm";
import { withRouter } from "react-router-dom";
import { setItem } from "../services/UseLocalStorage";
import Spinner from '../components/visuales/Spinner';
import { Dados, Players } from "../config/Config"
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import PartidaService from "../services/Partida/PartidaService"


const Registro2Jugadores = (props) => {
    const { history, accion } = props;
    const { register, handleSubmit, errors, reset } = useForm();
    const [spinner, showSpinner, hideSpinner] = Spinner();
    const [cargando, setCargando] = useState(false);
    const [enviado, setEnviado] = useState(false);
    const [mensajeError, setMensajeError] = useState({
        error: false,
        mensaje: "",
    });
    const [dado1, setdado1] = useState(Dados.dado1);
    const [dado2, setdado2] = useState(Dados.dado2);
    const [player1, setplayer1] = useState(Players.player);
    const [player2, setplayer2] = useState(Players.player);
    const [ganador, setGanador] = useState(0);
    const onSubmit = (data) => {
        // consultarAPI(data);
        if (ganador > 0) {
            data.ganador = ganador;
            // console.log("datos", data)
            PartidaService.crearPartida(data.nombre1, data.nombre2, data.ganador).then((res) => {
                setItem("Partida", res.data)
                history.push("/game");
                reset();
            }).catch((e) => {
                console.log("Error", e)
                setMensajeError({
                    error: true,
                    mensaje: "Ocurrió un error",
                });

            });


        } else {
            toast.error("Debe declarse un ganador");
        }

    };

    const get1al6 = () => { return Math.floor(Math.random() * 6) + 1; };

    const tirarDados = () => {
        for (let index = 1; index < 8; index++) {
            let imgD = Dados.diceGif;
            setdado1(imgD)
            setdado2(imgD)
            setCargando(true);
            setTimeout(() => {
                setCargando(false);
                let d1 = get1al6();
                let d2 = get1al6();
                while (d1 == d2) {
                    d1 = get1al6();
                    d2 = get1al6();
                }

                setplayer2(Players.playerRed)
                setplayer1(Players.playerWhite)
                if (d1 > d2) {
                  //  setplayer2(Players.playerRed)
                  //  setplayer1(Players.playerWhite)
                    setGanador(1);
                } else {
                  //  setplayer2(Players.playerWhite)
                 //   setplayer1(Players.playerRed)
                    setGanador(2);
                }
                setdado1(Dados["dado" + d1])
                setdado2(Dados["dado" + d2])

            }, 1500);
        }
    };


    return (

        <section className="contact-area pagina">
            {spinner}
            <form onSubmit={handleSubmit(onSubmit)} id="login" className="row justify-content-center align-items-center mt-5 mb-5 mx-0">
                <div className="col-xl-6 col-lg-8 col-md-10 col-xs-12   formContainer row">

                    <div className="container-fluid my-3 col-6 mt-5 ">
                        <img src={player1} className="players-img d-flex justify-content-start" />
                        <h2 className="">Jugador 1</h2>
                    </div>
                    <div className="container-fluid my-3 col-6 mt-5 ">
                        <img src={player2} className="players-img d-flex justify-content-start" />
                        <h2 className="">Jugador 2</h2>
                    </div>

                    <div className="container-fluid  mt-2 mb-5 col-12">
                        <section className="row">

                            <div className="container-fluid  justify-content-center align-items-center mt-2 mb-5  col-6">
                                <div className="row h-100 justify-content-center align-items-center">
                                    <div className="col-md-12">
                                        <fieldset>
                                            <div className="form-row">
                                                <div className="form-group col-md-12">
                                                    <label>Nombre</label>
                                                    <input
                                                        type="text"
                                                        className={
                                                            "form-control " +
                                                            (errors.nombre1 ? "input-error" : null)
                                                        }
                                                        name="nombre1"
                                                        placeholder="Nombre"
                                                        ref={register({ required: "Campo requerido." })}
                                                    />

                                                    {errors.nombre1 ? (
                                                        <ErrorForm mensaje={errors.nombre1.message} />
                                                    ) : null}
                                                </div>


                                            </div>
                                        </fieldset>
                                    </div>
                                </div>
                            </div>
                            <div className="container-fluid  justify-content-center align-items-center mt-2 mb-5  col-6">
                                <div className="row h-100 justify-content-center align-items-center">
                                    <div className="col-md-12">
                                        <fieldset>
                                            <div className="form-row">
                                                <div className="form-group col-md-12">
                                                    <label>Nombre</label>
                                                    <input
                                                        type="text"
                                                        className={
                                                            "form-control " +
                                                            (errors.nombre2 ? "input-error" : null)
                                                        }
                                                        name="nombre2"
                                                        placeholder="Nombre"
                                                        ref={register({ required: "Campo requerido." })}
                                                    />

                                                    {errors.nombre2 ? (
                                                        <ErrorForm mensaje={errors.nombre2.message} />
                                                    ) : null}
                                                </div>



                                            </div>
                                        </fieldset>
                                    </div>
                                </div>
                            </div>
                            <div className="container-fluid  justify-content-center align-items-center mt-2 mb-1  col-xl-10 col-lg-12 col-md-12 col-xs-12 ">
                                <div className="row h-100 justify-content-center align-items-center">
                                    <div className="col-md-12">
                                        <fieldset>
                                            <div className="form-row">
                                                <div className="form-group col-md-12 ">
                                                    <label className="col-md-12 text-center">Empieza en que tire el número mayor</label>
                                                    <div className="col-md-12 text-center">

                                                        <img src={dado1} className="dados-img" />
                                                        <button

                                                            className="d-inline btn btn-primary p-2"
                                                            disabled={cargando}
                                                            onClick={() => tirarDados()}
                                                        >
                                                            Tirar
                                                        </button>
                                                        <img src={dado2} className="dados-img" />
                                                    
                                                        {ganador > 0 ? (
                                                            <div className="alert alert-success p-2 animated fadeInUp faster mb-3">
                                                                {"Ganó el jugador " + ganador}
                                                            </div>
                                                        ) : null}
                                                    </div>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-12 text-center">
                                {enviado ? (
                                    mensajeError.error ? (
                                        <div className="alert alert-danger  p-2 animated fadeInUp faster mb-3">
                                            {mensajeError.mensaje}
                                        </div>
                                    ) : (
                                        <div className="alert alert-success p-2 animated fadeInUp faster mb-3">
                                            Iniciando...
                                        </div>
                                    )
                                ) : null}
                                <button
                                    type="submit"
                                    className="d-inline btn btn-primary "
                                    disabled={cargando}
                                >
                                    Iniciar
                                </button>
                            </div>

                        </section>

                    </div>


                </div>
            </form>
        </section>

    );

}
export default withRouter(Registro2Jugadores);
