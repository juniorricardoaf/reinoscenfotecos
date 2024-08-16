// Configuración de Conexión
export const PUERTO = "63764";
const protocol = window.location.protocol;
const location = window.location.hostname;
let apiDomain = '';
let publicDomain = '';

if (location.includes('localhost')) {
  apiDomain = protocol + '//localhost:4000';
  publicDomain = protocol + '//localhost:3000';
} else if (location.includes('develop.herokuapp')) {
  apiDomain = protocol + '//reinos-cenfotecos-service-dev.herokuapp.com';
  publicDomain = protocol + '//reinos-cenfotecos-develop.herokuapp.com';
} else {
  apiDomain = protocol + '//reinos-cenfotecos.herokuapp.com';
  publicDomain = protocol + '//reinos-cenfotecos-service.herokuapp.com';
}


export const BASEURL = apiDomain;

export class ListaEndpoints {
  static get SERVICIO() {
    return "Servicio";
  }
}

export class ListaMetodos {
  static get GET() {
    return "Get";
  }

  static get POST() {
    return "Post";
  }
  static get PUT() {
    return "Put";
  }

  static get DELETE() {
    return "Delete";
  }
}

export class Dados {
  //imagen sola del dado
  static get dado1() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618208190/dados/dado1_qmajny.png";
  }

  static get dado2() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618208190/dados/dado2_qses3d.png";
  }

  static get dado3() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618208190/dados/dado3_adidtt.png";
  }

  static get dado4() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618208190/dados/dado4_flpkzx.png";
  }

  static get dado5() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618208190/dados/dado5_iwklxn.png";
  }

  static get dado6() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618208190/dados/dado6_ww5iyy.png";
  }

  static get diceGif() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618208239/dados/dadogif_lweohx.gif";
  }

}
export class Players {
  //imagen sola del dado
  static get player() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618210101/players/player_uobv7l.png";
  }

  static get playerWhite() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618210101/players/player1_pgk4rs.png";
  }

  static get playerRed() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618210101/players/player2_ifi4o0.png";
  }
}
export class Castillo {
  //imagen sola del dado
  static get castillo13() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618817719/Castillos/castillo1-3_ctbyqj.png";
  }

  static get castillo12() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618817719/Castillos/castillo1-2_jq2fqf.png";
  }
  static get castillo11() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618817719/Castillos/castillo1-1_si3kay.png";
  }

  static get castillo10() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618990755/Castillos/kisspng-deviantart-rendering-poser-ruins-03-5b3d3b5bb54a24.7316993715307395477426_fcfrm7.png";
  }
  static get castillo23() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618817719/Castillos/castillo2-3_qphrbd.png";
  }

  static get castillo22() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618817719/Castillos/castillo2-2_ccsd4m.png";
  }
  static get castillo21() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618817719/Castillos/castillo2-1_y4rkjg.png";
  }

  static get castillo20() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618990755/Castillos/kisspng-deviantart-rendering-poser-ruins-03-5b3d3b5bb54a24.7316993715307395477426_fcfrm7.png";
  }
}

export class FragmentoDado {

  static get tanque() {
    return "https://res.cloudinary.com/donpdw8lt/image/upload/v1618613907/dados/tanque_ilbcug.jpg";
  }
  static get infanteria() {
    return 'https://res.cloudinary.com/donpdw8lt/image/upload/v1618613907/dados/Infanteria_fi8bsc.jpg';
  }
  static get artillero() {
    return "https://res.cloudinary.com/donpdw8lt/image/upload/v1618613907/dados/artillero_fhtk9n.jpg";
  }

  static get tanque2() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1619294954/diceInvocacion/Tanque2_pybg7w.png";
  }
  static get infanteria2() {
    return 'https://res.cloudinary.com/hi5patrones2021/image/upload/v1619294954/diceInvocacion/Infanteria2_ejm2o5.png';
  }
  static get artillero2() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1619294954/diceInvocacion/artillero2_csd6me.png";
  }

  static get ataqueNormal() {
    return "https://res.cloudinary.com/donpdw8lt/image/upload/v1618614654/dados/ataquenormal_yxaxyj.jpg";
  }

  static get ataqueEspecial() {
    return 'https://res.cloudinary.com/donpdw8lt/image/upload/v1618614654/dados/ataqueespecial_uwd6jp.jpg';
  }
  static get movimiento() {
    return "https://res.cloudinary.com/donpdw8lt/image/upload/v1618614921/dados/movimiento_bf6bqw.jpg";
  }



}


export class Personaje {
  static get tanque() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618616817/pj/Tanque_m9dspw.png";
  }
  static get infanteria() {
    return 'https://res.cloudinary.com/hi5patrones2021/image/upload/v1618616817/pj/Infanteria_bp4cr0.png';
  }
  static get artillero() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618616817/pj/Artillero_raiaba.png";
  }
  static get tanque2(){
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1619293992/pj/Tanque2_ofhaps.png"
  }
  static get infanteria2() {
    return 'https://res.cloudinary.com/hi5patrones2021/image/upload/v1619293992/pj/Infanteria2_l4xbio.png';
  }
  static get artillero2() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1619293993/pj/artillero2_eunxqj.png";
  }
  static get tanque_info() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618875869/pj/tanque_jaq07j.png";
  }
  static get infanteria_info() {
    return 'https://res.cloudinary.com/hi5patrones2021/image/upload/v1618875869/pj/Infanteria_vmeyty.png';
  }
  static get artillero_info() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618875868/pj/Artillero_n3dqps.png";
  }
  static get tanque2_info() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1619297305/pj/tanque2_info_f1mqas.png";
  }
  static get infanteria2_info() {
    return 'https://res.cloudinary.com/hi5patrones2021/image/upload/v1619297305/pj/infanteria2_info_f8dquv.png';
  }
  static get artillero2_info() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1619297305/pj/artillero2_info_zo9dw0.png";
  }
}

export const MODAL_STYLES = {

  overlay: {
    backgroundColor: "#0000008c",
    zIndex: 99998,
    overflowY: 'scroll'
  },
  content: {
    backgroundColor: "#bc2424",
    zIndex: 99998,
    maxWidth: "30%",
    // top: '15vh',
    marginTop: "10%",
    transform: 'translate (-50%, -50%) ',
    minHeigh: "5vh",
    // maxHeight: '5vh ',  // <- Esto establece la altura 


  },


}
export class Estados {

  static get ganador() {
    return 'https://res.cloudinary.com/hi5patrones2021/image/upload/v1618887100/players/Trompas-Medievales-55348_d9ne78.gif';
  }
  static get rendirse() {
    return "https://res.cloudinary.com/hi5patrones2021/image/upload/v1618886778/players/surrender-gif-2_vkyjov.gif";
  }
}
  