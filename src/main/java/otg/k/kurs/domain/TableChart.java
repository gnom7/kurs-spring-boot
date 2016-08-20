package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import otg.k.kurs.dto.TableChartDto;

import javax.persistence.*;

@Data
@ToString(exclude = "site")
@Entity
@Table(name = "table_charts")
public class TableChart {

    @Id
    @GeneratedValue
    private long id;

    private int position;

    private String[][] grid;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    public TableChart() {
    }

    public TableChart(TableChartDto tableChartDto, Site site) {
        this.position = tableChartDto.getPosition();
        this.grid = tableChartDto.getGrid();
        this.id = tableChartDto.getId();
        this.site = site;
    }
}
