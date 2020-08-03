import React from 'react'
import '../../css/footer.css'
import {Link} from "react-router-dom";

class Footer extends React.Component {
    render() {
        return <footer>
            <div className='container'>
                <section className='sn-section'>
                    <nav>
                        <a href="#"><img src='img/sm/facebook.svg'/></a>
                        <a href="#"><img src='img/sm/skype.svg'/></a>
                        <a href="#"><img src='img/sm/telegram.svg'/></a>
                        <a href="#"><img src='img/sm/youtube.svg'/></a>
                    </nav>
                </section>

                <Link className="copyright" to={'/policy'}>
                    Copyright &copy; 2020 Vacations accounting
                </Link>
            </div>
        </footer>
    }
}

export default Footer