import React from 'react'
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Vacations from "./components/Vacations";
import {toast} from "react-toastify";
import {isAuth} from "./Auth";
import {StartContent, Unauthorized} from "./components/Initial";

function App() {

    toast.configure({
        autoClose: 8000,
        draggable: false,
        pauseOnHover: true
    });

    return (
        <div className="App">
            <Router>
                <Header/>
                <Route exact path="/" render={() => (isAuth() ? <Redirect to="/vacations"/>: <StartContent/>)}/>
                <Route exact path="/unauthorized" component={Unauthorized}/>
                <Route path="/vacations" component={Vacations}/>
                {/*<Route path="/settings" component={Settings}/>*/}
                <Footer/>
            </Router>
        </div>
    );
}

export default App;
