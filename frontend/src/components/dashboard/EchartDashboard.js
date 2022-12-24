
import axios from 'axios';

import { useEffect, useState } from 'react';
import { EchartComponent } from '../echarts/EchartComponent';
import EchartStaticPieValue from '../echarts/EchartStaticPieValue';
import EchartStaticBarValue from '../echarts/EchartStaticBarValue';

export const EchartDashboard = () => {

    let options = {};

    //================ student by State =================================
    const [studentByStateState, setStudentByStateState] = useState(options);

    const fetchStudentByState = async () => {
        const studentByState = await axios.get('/echarts/student-by-state');
        var result = studentByState.data;
        const chartName = "Students by State"
        const options = EchartStaticPieValue(result, chartName);
        setStudentByStateState(options)

    }

    //================ student by City =================================
    const [studentByCityState, setStudentByCityState] = useState(options);

    const fetchStudentByCity = async () => {
        const studentBycity = await axios.get('/echarts/student-by-city');
        var result = studentBycity.data;
        const chartName = "Students by City"
        const options = EchartStaticPieValue(result, chartName);
        setStudentByCityState(options)

    }

    //================ Job by State =================================
    const [jobByStateState, setJobByStateState] = useState(options);

    const fetchJobByState = async () => {
        const jobByState = await axios.get('/echarts/job-by-state');
        var result = jobByState.data;
        const chartName = "Jobs by State"
        const options = EchartStaticPieValue(result, chartName);
        setJobByStateState(options)

    }

    //================ Job by City =================================
    const [jobByCityState, setJobByCityState] = useState(options);

    const fetchJobByCity = async () => {
        const jobByCity = await axios.get('/echarts/job-by-city');
        var result = jobByCity.data;
        const chartName = "Jobs by city"
        const options = EchartStaticPieValue(result, chartName);
        setJobByCityState(options)

    }

    //================ Job by tags =================================
    const [jobByTagState, setJobByTagState] = useState(options);

    const fetchJobByTag = async () => {
        const jobByTag = await axios.get('/echarts/job-by-tag');
        var result = jobByTag.data;
        const chartName = "Jobs by Tags"
        const options = EchartStaticBarValue(result, chartName);
        setJobByTagState(options)
    }


    useEffect(() => {
        fetchStudentByState();
        fetchStudentByCity();
        fetchJobByState();
        fetchJobByCity();
        fetchJobByTag();
    }, [])

    const renderEchart = (optionData) => {
        if (studentByStateState.tooltip) {
            return <EchartComponent EchartComponent optionDta={optionData} > </EchartComponent >;
        }
    }

    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="col">
                        <h6>Students by state</h6>
                        {renderEchart(studentByStateState)}
                    </div>
                    <div className="col">
                        <h6>Students by City</h6>
                        {renderEchart(studentByCityState)}
                    </div>
                    <div className="col-sm">
                        <h6>Jobs by state</h6>
                        {renderEchart(jobByStateState)}
                    </div>
                </div>

                <div className="row">
                    <div className="col-4">
                        <h6>Jobs by City</h6>
                        {renderEchart(jobByCityState)}
                    </div>
                    <div className="col-sm">
                        <h6>Jobs by Tags</h6>
                        {renderEchart(jobByTagState)}
                    </div>
                </div>


            </div>
        </div>
    );

}

