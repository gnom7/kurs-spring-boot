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
