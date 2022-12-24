
import ReactECharts from 'echarts-for-react';


export const EchartComponent = (props) => {
    return (
        <div >
            <ReactECharts
                option={props.optionDta}
                style={{ width: "100%", height: "290px" }}
            ></ReactECharts>
        </div>
    );
}