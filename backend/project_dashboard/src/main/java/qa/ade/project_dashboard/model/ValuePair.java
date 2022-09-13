package qa.ade.project_dashboard.model;

import java.math.BigDecimal;

public class ValuePair {
    BigDecimal first;
    BigDecimal second;


    public ValuePair(BigDecimal first, BigDecimal second) {
        this.first = first;
        this.second = second;
    }

    public ValuePair() {
    }

    public BigDecimal getFirst() {
        return first;
    }

    public void setFirst(BigDecimal first) {
        this.first = first;
    }

    public BigDecimal getSecond() {
        return second;
    }

    public void setSecond(BigDecimal second) {
        this.second = second;
    }

    public String toString() {
        return "ValuePair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
