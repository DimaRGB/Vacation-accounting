import React from 'react'
import '../../css/initial.css'
import {Alert} from "react-bootstrap";
import { toast } from 'react-toastify';

export class StartContent extends React.Component {

    isLogout() {
        try {
            return location.search.indexOf("q=logout") > 0;
        } catch (e) {
            console.log(this.props);
            toast.error("Uups! Something went wrong 🤷‍♂");
            return false;
        }
    }

    render() {
        return <div className='container'>
            {this.isLogout() ? <Alert variant="info">
                You are successfully logout! To login again{' '}
                <Alert.Link href="/login">Click Here</Alert.Link>.
            </Alert> : <p/>
            }
            <h1 className='central-content'>Welcome</h1>
        </div>
    }
}

export class Unauthorized extends React.Component {
    render() {
        return <div className='container'>
            <h1 className='central-content'>You are not authorized</h1>
        </div>
    }
}