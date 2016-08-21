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

function drawLine(lineData, id) {
    var data = google.visualization.arrayToDataTable(lineData.data);

    var options = {
        title: lineData.title,
        curveType: 'function',
        legend: { position: 'bottom' }
    };

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

    chart.draw(data, options);
}
/*]]>*/