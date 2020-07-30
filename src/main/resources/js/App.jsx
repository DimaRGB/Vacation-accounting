import React from 'react'
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom";
import {isAuth} from "./Auth";
import {toast} from "react-toastify";

function App() {

    toast.configure({
        autoClose: 8000,
        draggable: false,
        pauseOnHover: true
    });

    return (
        <div className="App">
            <Router>

            </Router>
        </div>
    );
}

export default App;
