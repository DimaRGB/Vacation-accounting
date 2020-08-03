import React, {useEffect, useState} from "react";
import axios from 'axios';
import {Container} from "react-bootstrap";
import moment from "moment";
import Timeline from "react-calendar-timeline";


export const AllVacations = () => {
    const [userVacations, setUserVacations] = useState([]);

    useEffect(() => {
        axios
            .get(`/api/vacations`)
            .then(({data}) => {
                setUserVacations(data)
            });
    }, []);

    let groups = userVacations.map(function (uv) {
        let user = uv.user;
        return {
            id: user.email,
            title: user.name,
        }
    })

    let vacationsArr = userVacations.map(function (uv) {
        return uv.vacations;
    }).flat();

    let items = vacationsArr.map(function (vacation) {
        let id = vacation.userName;
        return {
            id: vacation.id,
            group: id,
            title: 'v',
            canMove: false,
            canResize: false,
            canChangeGroup: false,
            start_time: moment(vacation.recordDate).startOf('day'),
            end_time: moment(vacation.recordDate).endOf('day'),
            itemProps: {
                onDoubleClick: () => {
                    console.log('You clicked double!')
                },
                style: {
                    background: 'fuchsia'
                }
            }
        }
    })

    console.log("render all vacations");
    console.log(items);
    console.log(groups);

    return <Container fluid>
        <Timeline
            groups={groups}
            items={items}
            defaultTimeStart={moment().add(-15, 'day')}
            defaultTimeEnd={moment().add(15, 'day')}
            minZoom={24 * 60 * 60 * 1000}
            maxZoom={365 * 86400 * 1000}
        />
    </Container>

}

export default AllVacations;