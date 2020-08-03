import React from 'react'
import {toast} from "react-toastify";

export function isAuth() {
    try {
        return document.getElementById('isAuth').value === 'true';
    } catch (e) {
        toast.error("Uups! Something went wrong 🤷‍♂")
        console.log(e);
        return false;
    }
}