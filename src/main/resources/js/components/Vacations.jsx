import React from 'react'
import {Row, Col} from "react-bootstrap";
import AllVacations from "./AllVacations";

import 'react-calendar-timeline/lib/Timeline.css'


class Vacations extends React.Component {

    render() {
        return <div className='container'>
            <Row>
                <Col>
                    <AllVacations/>
                </Col>
            </Row>

        </div>
    }
}

export default Vacations