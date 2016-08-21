/*<![CDATA[*/
google.charts.load('current', {'packages':['table', 'corechart']});

function drawTable(tableData, id) {
    var data = new google.visualization.DataTable();

    tableData.grid.shift().forEach(function (cell) {
        data.addColumn('string', cell);
    });

    data.addRows(tableData.grid);

    var table = new google.visualization.Table(document.getElementById(id));

    table.draw(data, {width: '100%', height: '100%'});
}

function drawLine(linesData, id) {
    for(let i = 1; i < linesData.data.length; i++){
        for(let j = 1; j < linesData.data[i].length; j++){
            linesData.data[i][j] = parseInt(linesData.data[i][j]);
        }
    }
    var data = google.visualization.arrayToDataTable(linesData.data);

    var options = {
        title: (linesData.chartTitle || 'Default'),
        curveType: 'function',
        legend: { position: 'bottom' },
        hAxis: {
            title: linesData.data[0][0]
        }
    };

    var chart = new google.visualization.LineChart(document.getElementById(id));

    chart.draw(data, options);
}
/*]]>*/