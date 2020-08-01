import React from 'react'
import {toast} from "react-toastify";

function App() {

    toast.configure({
        autoClose: 8000,
        draggable: false,
        pauseOnHover: true
    });

    return (
        <div className="App">
           <p>HELLO!</p>
        </div>
    );
}

export default App;
