import React, { useState, useEffect } from "react";
import Headroom from "react-headroom";
import "../../assets/css/NavMenu.css";
import { Link, useHistory } from "react-router-dom";
import Button from "../ButtonComponent";
import useInterval from "../../services/useInterval";
import TimerService from "../../services/Timer/TimerService";
import ActualizarPartida from '../../services/ActualizarPartida.js';
import { getItem, setItem } from '../../services/UseLocalStorage.js';
import { SweetAlert } from "../../services/ControlActions";
import { Estados } from "../../config/Config";
import sonido0 from "../../assets/audio/storm.mp3"
import sonido1 from "../../assets/audio/tree.mp3"
import sonido2 from "../../assets/audio/snow.mp3"
import sonido3 from "../../assets/audio/mega.mp3"
import sonido4 from "../../assets/audio/gloomy.mp3"
import sonido5 from "../../assets/audio/fate.mp3"
import sonido6 from "../../assets/audio/dakiti.mp3"

import {
  Play,
  Pause,
  Headphones
} from "react-feather";
import { Howl, Howler } from "howler"

const NavBar = ({ }) => {
  const [duration, setDuration] = useState(0);
  const [primero, setPrimero] = useState(true);
  const [mensajeTime, setMensajeTime] = useState("");
  const [turno, setTurno] = useState();
  const [jugador, setJugador] = useState("");
  const [reproducir, setReproducir] = useState(false);
  const [sound, setSound] = useState();
  let history = useHistory();

  useEffect(() => {

    nuevoSonido();
  }, [])

  const cambiarEstadoSonido = () => {
    if (reproducir) {
      sound.pause();
      setReproducir(false)
      nuevoSonido();
    } else {
      sound.play();
      setReproducir(true)
    }
  }

  const nuevoSonido = () => {
    let array = [sonido0, sonido1, sonido2, sonido4, sonido5, sonido3/*,sonido6*/];
    var random = ((Math.round(Math.random() * 5)) + 0);
    setSound(new Howl({

      src: [array[random]],
      //autoplay: true,
      loop: true,
      volume: 0.1,
      onend: function () {
        console.log('Finished!');
      }
    }))

  }

  useInterval(() => {
    //  console.log("se recargó")
    if (window.location.href == "http://localhost:3000/#/game" || window.location.href == "https://reinos-cenfotecos-develop.herokuapp.com/#/game") {
      if (getItem('Partida').estado == 1) {
        if (primero) {
          setMensajeTime('Iniciando...');
          iniciarTiempo();
          // console.log("empieza", duration);
          setJugador(getItem("Partida")["jugador" + getItem("Partida").turno].nombre);
          setPrimero(false);
        } else {
          setMensajeTime("Tiempo restante: " + (duration) + ' s');
          updateCountdown();
        }
      }
    }
  }, 1000)

  useInterval(() => {
    // console.log("verificación de turno")
    if (window.location.href == "http://localhost:3000/#/game" || window.location.href == "https://reinos-cenfotecos-develop.herokuapp.com/#/game") {
      if (getItem('Partida')) {
        if (getItem('Partida').estado == 1) {

          if (turno != getItem("Partida").turno) {
            setTurno(getItem("Partida").turno);
            setJugador(getItem("Partida")["jugador" + getItem("Partida").turno].nombre);
          }
        } else {
          setJugador("");
          setPrimero(true);
          setMensajeTime("");
        }
      }
    } else {
      setJugador("");
      setPrimero(true);
      setMensajeTime("");
    }
  }, 1000)

  const updateCountdown = () => {
    //console.log(duration);
    if (duration > 0) {
      setMensajeTime("Tiempo restante: " + (duration) + ' s');
      setDuration(duration - 1)
    } else if (duration == 0) {
      // Aquí se acaba el interval y se pasa de turno
      setMensajeTime('Ahora es turno del otro jugador');
      //Observador
      cambiarTurno();
      //girar tablero 127 BOARD COMPONENT.

    }
  }

  const terminar = () => {
    localStorage.clear();
    history.push("/");
  };

  const FFal15 = async () => {
    let ganador;
    let partidaNew = getItem("Partida");
    if (turno == 2) {
      ganador = getItem("Partida").jugador1.nombre;
      //  partidaNew.ganador = 1;
    } else {
      ganador = getItem("Partida").jugador2.nombre;
      //  partidaNew.ganador = 2;
    }
    SweetAlert(3, "Desea rendirse?", "Ganará " + ganador, "Rendirse", async () => {

      partidaNew["jugador" + turno].vidaCastillo = 0;//baja de vida
      //    partidaNew.estado = 0;//baja de vida

      await ActualizarPartida.actualizarPartida(partidaNew);

    }, Estados.rendirse);
  };

  async function iniciarTiempo() {
    // console.log("inicio tiempo")
    await TimerService.iniciarTimer()
      .then(data => setDuration((data.data)))//envia el valor del dado y a cual dado le va a asignar el valor
      .catch(error => console.log(error));
  }

  async function cambiarTurno() {
    setDuration(-1);
    if (getItem('Partida')) {
      var faceVacio = new Image();
      let partidaNew = getItem("Partida");
      partidaNew.turno = partidaNew.turno == 1 ? 2 : 1;
      await ActualizarPartida.actualizarPartida(partidaNew);
      await iniciarTiempo();

      for (let i = 1; i < 4; i++) {
        document.images['dado' + i].src = eval("faceVacio.src");
      }
    }

  }


  return (
    <Headroom
      style={{
        zIndex: 9999,
      }}
    >
      <header id="header" className="headroom">
        <div className="startp-nav">
          <div className="container-fluid">
            <nav className="navbar navbar-expand-md navbar-light d-flex justify-content-between">
              <Link to="/" className="navbar-brand">
                <div className="logoGame img-fluid" alt="Responsive image"></div>
              </Link>
              <div className="others-option ml-0">


                <div className="form-group">

                  {reproducir ?
                    <button className="btn btn-primary p-0" onClick={() => cambiarEstadoSonido()}>
                      <Headphones /> <Pause />
                    </button>
                    :

                    <button className="btn btn-primary p-0" onClick={() => cambiarEstadoSonido()}>
                      <Headphones /><Play />
                    </button>
                  }

                </div>



              </div>
              <h5 id="time">
                {mensajeTime}
              </h5>
              <div className="others-option ml-0">
                <div className="dropdown dropdownUser">
                  {mensajeTime != "Ahora es turno del otro jugador" && primero != true ?
                    <Button id={"cambiarTurno"} texto={"Terminar Turno"} onClick={() => cambiarTurno()} /> : null
                  }


                </div>
              </div>

              <div className="others-option ml-0">
                <div className="dropdown dropdownUser">
                  {jugador != "" ? (<><button
                    className="btn btn-light dropdown-toogle"
                    type="button"
                    id="dropdownUser"
                    data-toggle="dropdown"
                  >
                    {jugador}

                  </button>

                    <ul
                      className="dropdown-menu"
                      role="menu"
                      arial-labelledby="dropdownUser"
                    >
                      <li className="dropdown-item">
                        <div role="button" onClick={() => FFal15()}>
                          Rendirse
                          </div>
                      </li>
                      <li className="dropdown-item">
                        <div role="button" onClick={() => terminar()}>
                          Terminar
                          </div>
                      </li>

                    </ul>
                  </>) : null}
                </div>
              </div>
            </nav>
          </div>
        </div>
      </header>
    </Headroom>
  );
};

export default NavBar;
