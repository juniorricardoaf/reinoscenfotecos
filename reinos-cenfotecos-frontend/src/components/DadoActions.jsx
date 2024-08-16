import React, { useState, useEffect } from 'react';
import Button from "./ButtonComponent";
import Images from "./ImagesComponent.jsx";
import { FragmentoDado } from "../config/Config.js";
import DadosService from "../services/Dados/DadosService";
import { getItem, setItem } from '../services/UseLocalStorage.js';
import useInterval from "../services/useInterval";
import CofreService from '../services/Cofres/CofreService';
import ActualizarPartida from '../services/ActualizarPartida.js';

const Img = () => {
  return (<>
    <div id="dado">
      <Images nombre={1} />
    </div>
    <div id="dado2">
      <Images nombre={2} />
    </div>
    <div id="dado3">
      <Images nombre={3} />
    </div>
  </>
  )
}
const Tabla = () => {
  return (
    <div>
      <div id="dados">
        <Img />
      </div>
      <Btn />
    </div>
  )
}

const Btn = () => {
  const [deshabilitadoGuardar, setDeshabilitadoGuardar] = useState(true);
  const [deshabilitadoTirar, setDeshabilitadoTirar] = useState(false);
  const [turn, setTurn] = useState(1);
  let dados = []; //Array del backend

  useInterval(() => {
    // console.log("verificación de turno")
    //if (getItem('Partida')) {
    if (turn != getItem("Partida").turno) {
      setTurn(getItem("Partida").turno);
      setDeshabilitadoTirar(false);
      setDeshabilitadoGuardar(true);
    }
    //}
  }, 1000)

  var change; var faceInfanteria = new Image();
  var faceTanque = new Image();
  var faceArtilleria = new Image();
  var faceAtaqueEspecial = new Image();
  var faceAtaqueNormal = new Image();
  var faceMovimiento = new Image();

  function changeImages(num) { //se cambian imagenes
    change = num;
    if (change == 1) {
      if (turn == 1) {
        faceInfanteria.src = FragmentoDado.infanteria;
        faceTanque.src = FragmentoDado.tanque
        faceArtilleria.src = FragmentoDado.artillero;
      } else if(turn == 2){
        faceInfanteria.src = FragmentoDado.infanteria2;
        faceTanque.src = FragmentoDado.tanque2;
        faceArtilleria.src = FragmentoDado.artillero2;
      }
      //DADOS DE INVOCACION
      
    } else if (change == 2) {
      //DADOS DE ACCION
      faceAtaqueEspecial.src = FragmentoDado.ataqueEspecial;
      faceAtaqueNormal.src = FragmentoDado.ataqueNormal;
      faceMovimiento.src = FragmentoDado.movimiento;
    }
  };

  async function tirarDados() {
    changeImages(1);
    await DadosService.obtenerdados()
      .then((data) => {
        dados = data.data;
        setItem("dados", dados);

        for (var i = 1; i < 4; i++) {//tira cada uno de los dados (3 dados)
          let dado = dados[i - 1].tipo;
          lanzar(i, dado);
        }

      })//trae los dados del backend
      .catch(error => console.error(error));
  }

  function randomImage() { //imagenes randoms
    //cambia los dados entre de accion y de invocación
    changeImages(Math.floor(Math.random() * (3 - 1)) + 1);
    var random = ((Math.round(Math.random() * 5)) + 1);
    switch (random) {
      case 1:
        random = "AtaqueEspecial"
        break;
      case 2:
        random = "AtaqueNormal"
        break;
      case 3:
        random = "Movimiento"
        break;
      case 4:
        random = "Artilleria"
        break;
      case 5:
        random = "Tanque"
        break;
      case 6:
        random = "Infanteria"
        break;
      default:
      // code block
    }
    //imprime las imagenes
    var randomDado = ((Math.round(Math.random() * 2)) + 1);
    var dado = document.images['dado' + randomDado].src = eval("face" + random + ".src");
    return dado;
  };

  function lanzar(numeroDado, tipo) {//se mueve una imagen al azar hasta llegar al numero final.
    var giro = [];//cada dado gira diferente
    if (numeroDado == 1) {
      giro = [10, 250, 500, 750, 1000]
    } else if (numeroDado == 2) {
      giro = [200, 350, 600, 950, 1200]
    }
    else { giro = [100, 450, 600, 800, 1100] }
    setTimeout(() => { randomImage(); document.images['dado' + numeroDado].style = "height: 18px"; document.images['dado' + numeroDado].style.transform = 'rotate(' + 72 + 'deg)'; }, giro[0]);
    setTimeout(() => { randomImage(); document.images['dado' + numeroDado].style = "height: 30px"; document.images['dado' + numeroDado].style.transform = 'rotate(' + 144 + 'deg)'; }, giro[1]);
    setTimeout(() => { randomImage(); document.images['dado' + numeroDado].style = "height: 50px"; document.images['dado' + numeroDado].style.transform = 'rotate(' + 216 + 'deg)'; }, giro[2]);
    setTimeout(() => { randomImage(); document.images['dado' + numeroDado].style = "height: 80px"; document.images['dado' + numeroDado].style.transform = 'rotate(' + 288 + 'deg)'; }, giro[3]);

    setTimeout(() => {
      if (numeroDado < 3) {
        //2 dados de invocación
        changeImages(1);
      }
      else {
        //un dado de acción
        changeImages(2);
      }
      document.images['dado' + numeroDado].src = eval("face" + tipo + ".src"); document.images['dado' + numeroDado].style = "height: 100px;"; document.images['dado' + numeroDado].style.transform = 'rotate(' + 360 + 'deg)';
    }, giro[4]);

    //  console.log('dado#' + numeroDado + '=' + tipo);//imprime el verdadero valor del dado
    setDeshabilitadoTirar(true)
    setDeshabilitadoGuardar(false);
  }

  //segundo boton COMPOSITE
  async function guardar() {
    let idPartida = getItem("Partida").id;
    let jugador = turn;

    await CofreService.mostrarCofre(getItem("dados"), idPartida, jugador)
      .then((data) => {
        //     console.log(data.data);
        ActualizarPartida.getPartidaById(getItem("Partida").id);
        //METER EN EL LOCAL STORAGE EL RESULTADO


        //Limpia todo
        localStorage.removeItem("dados");
        setDeshabilitadoGuardar(true);
      })//trae los dados del backend
      .catch(error => console.log(error));
  }

  return (
    <>
      <Button deshabilitado={deshabilitadoTirar} id={"dadobtn"} texto={"Lanzar dados"} onClick={i => tirarDados()} />
      <Button deshabilitado={deshabilitadoGuardar} id={"dadoguardar"} texto={"Guardar dados"} onClick={i => guardar()} />
    </>
  );

}

export default Tabla;