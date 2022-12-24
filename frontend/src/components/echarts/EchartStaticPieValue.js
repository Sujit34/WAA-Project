const EchartStaticPieValue = (props, name) => {

    let newData = props.reduce((acc, item) => {
        if (!acc[item]) acc[item] = 0;
        acc[item]++;
        return acc;
    }, {})


    let finalData = [];
    for (const [key, value] of Object.entries(newData)) {
        finalData.push({

            value: value,
            name: key,

        })
    }

    const chartName = name;
    let options = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: chartName,
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: finalData
            }
        ]
    };
    return (options);
}
export default EchartStaticPieValue;