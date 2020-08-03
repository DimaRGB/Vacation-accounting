import React from 'react'
import '../../css/header.css'
import {isAuth} from "../Auth";
import {Button} from "react-bootstrap";

class Header extends React.Component {
    render() {
        return <header>
            <div className='container'>
                <section className='company-logo'>
                    <img src="img/logo.svg" alt="logo"/>
                </section>

                <section className='logout-section'>
                    <nav>
                        {
                            isAuth() ?
                                <Button variant="secondary" onClick={() => {
                                    location.href = '/logout';
                                }}>Logout</Button> :

                                <Button variant="primary" onClick={() => {
                                    location.href = '/login';
                                }}>Login</Button>
                        }
                    </nav>
                </section>

                {isAuth() ? <>
                    <section className='nav-links'>
                        <nav>
                            <a href="settings">Settings</a>
                        </nav>
                    </section>
                    <section className='nav-links'>
                        <nav>
                            <a href="vacations">Vacations accounting</a>
                        </nav>
                    </section>
                </> : <p/>}
            </div>
        </header>
    }
}

export default Header