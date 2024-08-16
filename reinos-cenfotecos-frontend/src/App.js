import React from 'react';
import { Route, Switch } from "react-router-dom";
import Layout from "./components/layout/Layout";
//pages
import Home from './views/Home';
import Game from './views/Game';
import Registro2Jugadores from './views/Registro2Jugadores';
//import Login from './views/Login';
//estilos
import "bootstrap";
import "./assets/css/bootstrap.min.css";//general
import './assets/sass/App.scss';
import './assets/css/App.css';
import './assets/css/Cofre.css';
import './assets/css/infoPersonaje.css';

const App = () => {

    return (
        <Layout>
            <Route exact path='/' component={Home} />
            <Route exact path='/game' component={Game} />
            <Route exact path='/registroLocal' component={Registro2Jugadores} />
        </Layout>
    );

}
export default App;
