function renderLineModalTable(linesData) {
    let table = $('#line-info-table');
    table.children().each(function (index, child) {
        child.remove();
    });

    table.append($('<label>Chart title: <input id="chart-title"/></label><br/>' +
        '<button class="btn btn-default btn-success btn-xs" id="add-line-row" onclick="addLineRow(this)">Add row</button>' +
        '<button class="btn btn-default btn-danger btn-xs" id="delete-line-row" onclick="deleteRow(this)">Delete row</button>' +
        '<button class="btn btn-default btn-success btn-xs" id="add-line-line" onclick="addLine(this)">Add line</button>' +
        '<button class="btn btn-default btn-danger btn-xs" id="delete-line-line" onclick="deleteLine(this)">Delete line</button>' +
        '<table class="table-hover line-info-table">' +
        '<tr><th>Argument name</th><th>Line names</th></tr>' +
        '<tr><td><input/></td><td><input/></td></tr>' +
        '<tr><th>Argument value</th><th>Lines value</th></tr>' +
        '<tr><td><input/></td><td><input/></td>' +
        '</tr></table>'));

    if( !linesData) return;

    for(let i = 2; i < linesData.data.length; i++){
        addLineRow($('#add-line-row'));
    }
    for(let i = 2; i < linesData.data[0].length; i++){
        addColumn($('#add-line-line'));
    }

    $('#chart-title').val(linesData.chartTitle);
    let data = linesData.data;
    table.find('tr').eq(1).find('input').each(function (i, input) {
        input.value = data[0][i];
    });
    table.find('tr').each(function (i, tr) {
        if(i < 3) return;
        tr = $(tr);
        tr.find('input').each(function (j, input) {
            input.value = data[i - 2][j];
        });
    });

}

function addLineRow(self) {
    self = $(self);
    let row = '<tr><td><input/></td>';
    let linesCount = self.siblings('table').find('tr').eq(1).find('td').length - 1;
    for(let i = 0; i < linesCount; i++){
        row += '<td><input/></td>';
    }
    self.siblings('table').append($(row + '</tr>'))
}
function deleteRow(self) {
    self = $(self);
    self.siblings('table').find('tr').last().remove();
}
function addLine(self) {
    self = $(self);
    let linesCount = self.siblings('table').find('tr').eq(1).length;
    self.siblings('table').find('tr').eq(1).append($('<td><input/></td>'));
    self.siblings('table').find('tr').each(function (index, tr) {
        if( index < 3 ) return;
        tr = $(tr);
        tr.append($('<td><input/></td>'));
    });
}
function deleteLine(self) {
    self = $(self);
    self.siblings('table').find('tr').eq(1).find('td').last().remove();
    self.siblings('table').find('tr').each(function (index, tr) {
        if(index < 3) return;
        tr = $(tr);
        tr.find('td').last().remove();
    });
}

function collectLineModalTableData(){
    let linesData = {
        data: []
    };
    let table = $('#line-info-table');

    linesData.data.push([]);
    table.find('tr').eq(1).find('input').each(function (index, input) {
        linesData.data[0].push(input.value);
    });
    table.find('tr').each(function (i, tr) {
        if( i < 3 ) return;
        tr = $(tr);
        let row = [];
        tr.find('input').each(function (j, input) {
            if(j == 0) {
                row.push(input.value);
            } else {
                row.push(parseInt(input.value));
            }
        });
        linesData.data.push(row);
    });
    linesData.chartTitle = $('#chart-title').val();

    return linesData;
}




function renderModalTable(tableData) {
    let table = $('#table-info-table');
    table.children().each(function (index, child) {
        child.remove();
    });

    table.append($(
        '<button class="btn btn-default btn-success btn-xs" id="add-table-row" onclick="addTableRow(this)">Add row</button>' +
        '<button class="btn btn-default btn-danger btn-xs" id="delete-table-row" onclick="deleteRow(this)">Delete row</button>' +
        '<button class="btn btn-default btn-success btn-xs" id="add-table-column" onclick="addColumn(this)">Add column</button>' +
        '<button class="btn btn-default btn-danger btn-xs" id="delete-table-column" onclick="deleteColumn(this)">Delete column</button>' +
        '<table class="table-hover table-info-table"><tr><td><input/></td></tr></table>'));

    if( !tableData) return;

    for(let i = 1; i < tableData.grid.length; i++){
        addTableRow($('#add-table-row'));
    }
    for(let i = 1; i < tableData.grid[0].length; i++){
        addColumn($('#add-table-column'));
    }

    table.find('tr').each(function (i, tr) {
        tr = $(tr);
        tr.find('input').each(function (j, input) {
            input.value = tableData.grid[i][j];
        });
    });
}

function collectModalTableData(){
    let tableData = {
        grid: []
    };
    let table = $('#table-info-table');

    table.find('tr').each(function (i, tr) {
        tr = $(tr);
        let row = [];
        tr.find('input').each(function (j, input) {
            row.push(input.value);
        });
        tableData.grid.push(row);
    });

    return tableData;
}

function addColumn(self) {
    self = $(self);
    self.siblings('table').find('tr').each(function (index, tr) {
        tr = $(tr);
        tr.append($('<td><input/></td>'));
    });
}
function deleteColumn(self) {
    self = $(self);
    self.siblings('table').find('tr').each(function (index, tr) {
        tr = $(tr);
        tr.find('td').last().remove();
    });
}
function addTableRow(self) {
    self = $(self);
    let row = '<tr>';
    let linesCount = self.siblings('table').find('tr').eq(0).find('td').length;
    for(let i = 0; i < linesCount; i++){
        row += '<td><input/></td>';
    }
    self.siblings('table').append($(row + '</tr>'))
}