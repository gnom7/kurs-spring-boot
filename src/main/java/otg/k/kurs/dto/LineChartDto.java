package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.LineChart;

@Data
public class LineChartDto {

    private long id;

    private int position;

    private String[][] data;

    public LineChartDto() {
    }

    public LineChartDto(LineChart lineChart) {
        this.id = lineChart.getId();
        this.position = lineChart.getPosition();
        this.data = lineChart.getData();
    }
}