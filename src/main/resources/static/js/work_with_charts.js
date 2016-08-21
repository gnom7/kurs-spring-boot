function renderLineModalTables(linesData) {
    var linesCount;
    if(linesData){
        linesCount = linesData.length;
    } else {
        linesCount = parseInt($('#lines-count').val());
    }
    let tables = $('#line-tables');
    tables.children().each(function (index, child) {
        child.remove();
    });

    for(let i = 0; i < linesCount; i++){
        tables.append($('<div><table id="' + 'line-id-' + i + '" class="table-hover">' +
            '<tr><th colspan="2"><label>Line name: <input class="line-name"  /></label></th></tr>' +
            '<tr><td colspan="2">' +
            '<button class="btn btn-default btn-success btn-xs" onclick="addRow(this)">Add row</button>' +
            '<button class="btn btn-default btn-danger btn-xs" onclick="deleteRow(this)">Delete row</button>' +
            '<button class="btn btn-default btn-danger btn-xs" onclick="deleteLineTable(this)">Delete</button>' +
            '</td></tr>' +
            '<tr><th>Horizontal</th><th>Vertical</th></tr></table></div><br/>'));
    }



}
function addRow(self) {
    self = $(self);
    self.closest('table').append($('<tr><td><input class="x"/></td><td><input class="y"/></td></tr>'))
}
function deleteRow(self) {
    self = $(self);
    self.closest('table').find('tr').last().remove();
}
function deleteLineTable(self) {
    self.closest('div').remove();
}



function renderModalTable(tableData) {
    var columnsCount;
    var rowsCount;
    if(tableData){
        columnsCount = tableData.grid[0].length;
        rowsCount = tableData.grid.length;
    } else {
        columnsCount = parseInt($('#columns-count').val());
        rowsCount = parseInt($('#rows-count').val());
    }
    let table = $('#table-from-modal');
    table.children().each(function (index, child) {
        child.remove();
    });

    for(let i = 0; i < rowsCount; i++){
        table.append($('<tr></tr>'));

        for(let j = 0; j< columnsCount; j++){
            table.find('tr').last().append($('<td><input  /></td>'));
        }
    }
    if( !tableData) return;

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
    let table = $('#table-from-modal');

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
