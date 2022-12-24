const EchartStaticBarValue = (props, name) => {

    let newData = props.reduce((acc, item) => {
        if (!acc[item]) acc[item] = 0;
        acc[item]++;
        return acc;
    }, {})


    console.log("newData" + JSON.stringify(newData))

    let finalData = [];
    for (const [key, value] of Object.entries(newData)) {
        finalData.push({

            value: value,
            name: key,

        })
    }

    console.log("finalData" + JSON.stringify(finalData));
    const chartName = name;

    let data1 = [];
    let data2 = []

    finalData.forEach(element => {
        data1.push(element.name);
        data2.push(element.value);
        console.log(element.key1, element.key2)
    }
    );
    console.log(data1)
    console.log(data2)

    const options = {
        xAxis: {
            type: 'category',
            data: data1
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: data2,
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                }
            }
        ]
    };
    return (options);
}
export default EchartStaticBarValue;