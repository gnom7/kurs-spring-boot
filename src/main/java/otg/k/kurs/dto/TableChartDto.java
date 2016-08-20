package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.TableChart;

@Data
public class TableChartDto {

    private long id;

    private String[][] grid;

    private int position;

    public TableChartDto() {
    }

    public TableChartDto(TableChart tableChart) {
        this.id = tableChart.getId();
        this.grid = tableChart.getGrid();
        this.position = tableChart.getPosition();
    }
}
