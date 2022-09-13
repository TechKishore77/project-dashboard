package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.util.List;

public class SPIVarianceData {
    private List<String> months;
    private List<BigDecimal> spiData;
    private List<BigDecimal> varianceData;

    public SPIVarianceData() {
    }

    public SPIVarianceData(List<String> months, List<BigDecimal> spiData, List<BigDecimal> varianceData) {
        this.months = months;
        this.spiData = spiData;
        this.varianceData = varianceData;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public List<BigDecimal> getSpiData() {
        return spiData;
    }

    public void setSpiData(List<BigDecimal> spiData) {
        this.spiData = spiData;
    }

    public List<BigDecimal> getVarianceData() {
        return varianceData;
    }

    public void setVarianceData(List<BigDecimal> varianceData) {
        this.varianceData = varianceData;
    }

    public String toString() {
        return "SPIVarianceData{" +
                "months=" + months +
                ", spiData=" + spiData +
                ", varianceData=" + varianceData +
                '}';
    }
}
