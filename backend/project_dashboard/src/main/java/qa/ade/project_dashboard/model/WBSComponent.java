package qa.ade.project_dashboard.model;

import java.math.BigDecimal;

public class WBSComponent {
    private String name;
    private BigDecimal percent;

    public WBSComponent() {
        this.name = "";
        this.percent = new BigDecimal("0");
    }

    public WBSComponent(String name, BigDecimal percent) {
        this.name = name;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public String toString() {
        return "WBSComponent{" +
                "name='" + name + '\'' +
                ", percent=" + percent +
                '}';
    }
}
