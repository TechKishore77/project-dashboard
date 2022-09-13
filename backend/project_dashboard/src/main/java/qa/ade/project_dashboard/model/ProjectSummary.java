package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProjectSummary {
    private LocalDate forecastCompletionDate;
    private LocalDate plannedCompletionDate;
    private BigDecimal actualProgressPercent;
    private BigDecimal plannedProgressPercent;
    private LocalDate dataDate;
    private Invoice latestInvoice;
    private Invoice plannedInvoice;
    private BigDecimal plannedProfit;
    private BigDecimal actualProfit;
    private BigDecimal percentChangeFromLastMonth;

    public ProjectSummary(LocalDate forecastCompletionDate, LocalDate plannedCompletionDate, BigDecimal actualProgressPercent, BigDecimal plannedProgressPercent, LocalDate dataDate, Invoice latestInvoice, Invoice plannedInvoice, BigDecimal plannedProfit, BigDecimal actualProfit, BigDecimal percentChangeFromLastMonth) {
        this.forecastCompletionDate = forecastCompletionDate;
        this.plannedCompletionDate = plannedCompletionDate;
        this.actualProgressPercent = actualProgressPercent;
        this.plannedProgressPercent = plannedProgressPercent;
        this.dataDate = dataDate;
        this.latestInvoice = latestInvoice;
        this.plannedInvoice = plannedInvoice;
        this.plannedProfit = plannedProfit;
        this.actualProfit = actualProfit;
        this.percentChangeFromLastMonth = percentChangeFromLastMonth;
    }

    public LocalDate getForecastCompletionDate() {
        return forecastCompletionDate;
    }

    public LocalDate getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    public BigDecimal getActualProgressPercent() {
        return actualProgressPercent;
    }

    public BigDecimal getPlannedProgressPercent() {
        return plannedProgressPercent;
    }

    public LocalDate getDataDate() {
        return dataDate;
    }

    public Invoice getLatestInvoice() {
        return latestInvoice;
    }

    public Invoice getPlannedInvoice() {
        return plannedInvoice;
    }

    public BigDecimal getPlannedProfit() {
        return plannedProfit;
    }

    public BigDecimal getActualProfit() {
        return actualProfit;
    }

    public BigDecimal getPercentChangeFromLastMonth() {
        return percentChangeFromLastMonth;
    }

    public void setForecastCompletionDate(LocalDate forecastCompletionDate) {
        this.forecastCompletionDate = forecastCompletionDate;
    }

    public void setPlannedCompletionDate(LocalDate plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    public void setActualProgressPercent(BigDecimal actualProgressPercent) {
        this.actualProgressPercent = actualProgressPercent;
    }

    public void setPlannedProgressPercent(BigDecimal plannedProgressPercent) {
        this.plannedProgressPercent = plannedProgressPercent;
    }

    public void setDataDate(LocalDate dataDate) {
        this.dataDate = dataDate;
    }

    public void setLatestInvoice(Invoice latestInvoice) {
        this.latestInvoice = latestInvoice;
    }

    public void setPlannedInvoice(Invoice plannedInvoice) {
        this.plannedInvoice = plannedInvoice;
    }

    public void setPlannedProfit(BigDecimal plannedProfit) {
        this.plannedProfit = plannedProfit;
    }

    public void setActualProfit(BigDecimal actualProfit) {
        this.actualProfit = actualProfit;
    }

    public void setPercentChangeFromLastMonth(BigDecimal percentChangeFromLastMonth) {
        this.percentChangeFromLastMonth = percentChangeFromLastMonth;
    }

    public String toString() {
        return "ProjectSummary{" +
                "forecastCompletionDate=" + forecastCompletionDate +
                ", plannedCompletionDate=" + plannedCompletionDate +
                ", actualProgressPercent=" + actualProgressPercent +
                ", plannedProgressPercent=" + plannedProgressPercent +
                ", dataDate=" + dataDate +
                ", latestInvoice=" + latestInvoice +
                ", plannedInvoice=" + plannedInvoice +
                ", plannedProfit=" + plannedProfit +
                ", actualProfit=" + actualProfit +
                ", percentChangeFromLastMonth=" + percentChangeFromLastMonth +
                '}';
    }
}
