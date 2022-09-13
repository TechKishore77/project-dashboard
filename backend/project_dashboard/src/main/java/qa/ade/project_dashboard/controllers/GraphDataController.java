package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import qa.ade.project_dashboard.model.*;
import qa.ade.project_dashboard.service.PlannedAndActualValuesService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@ComponentScan("qa.ade.project_dashboard")
@RestController
public class GraphDataController {

    @Autowired
    PlannedAndActualValuesService dataService;

    @GetMapping("/project/{projectId}/sCurveData")
    SCurveData getSCurveData(@PathVariable(value = "projectId") long projectId) {
        Project project = new Project();
        project.setId(projectId);
        List<MonthlyPlannedValue> plannedValues = dataService.getMonthlyPlannedValues(project);
        List<MonthlyActualValue> actualValues = dataService.getMonthlyActualValues(project);
        List<String> months = plannedValues.stream().map(MonthlyPlannedValue::getMonth).collect(Collectors.toList());

        Map<String, ValuePair> monthlyData = new LinkedHashMap<String, ValuePair>();
        for (String month : months) {
            monthlyData.put(month, new ValuePair(null, null));
        }
        Map<String, ValuePair> monthlyPercentData = new LinkedHashMap<String, ValuePair>();
        for (String month : months) {
            monthlyPercentData.put(month, new ValuePair(null, null));
        }

        List<BigDecimal> cumulativePlannedValues = plannedValues.stream().map(MonthlyPlannedValue::getPlannedValue).collect(Collectors.toList());
        if (cumulativePlannedValues.size() > 0) {
            BigDecimal previous = cumulativePlannedValues.get(0);
            monthlyData.get(plannedValues.get(0).getMonth()).setFirst(previous.setScale(2, RoundingMode.HALF_UP));
            for (int i = 1; i < cumulativePlannedValues.size(); i++) {
                previous = cumulativePlannedValues.get(i - 1);
                monthlyData.get(plannedValues.get(i).getMonth()).setFirst(cumulativePlannedValues.get(i).subtract(previous).setScale(2, RoundingMode.HALF_UP));
            }
        }

        List<BigDecimal> cumulativeActualValues = actualValues.stream().map(MonthlyActualValue::getActualValue).collect(Collectors.toList());
        if (cumulativeActualValues.size() > 0) {
            BigDecimal previous = cumulativeActualValues.get(0);
            monthlyData.get(actualValues.get(0).getMonth()).setSecond(previous.setScale(2, RoundingMode.HALF_UP));
            for (int i = 1; i < cumulativeActualValues.size(); i++) {
                previous = cumulativeActualValues.get(i - 1);
                monthlyData.get(actualValues.get(i).getMonth()).setSecond(cumulativeActualValues.get(i).subtract(previous).setScale(2, RoundingMode.HALF_UP));
            }
        }

        List<BigDecimal> planned = monthlyData.values().stream().map(ValuePair::getFirst).collect(Collectors.toList());
        List<BigDecimal> actual = monthlyData.values().stream().map(ValuePair::getSecond).collect(Collectors.toList());

        plannedValues.forEach((MonthlyPlannedValue p) -> {
            monthlyPercentData.get(p.getMonth()).setFirst(p.getPlannedPercentage().setScale(2, RoundingMode.HALF_UP));
        });
        actualValues.forEach((MonthlyActualValue p) -> {
            monthlyPercentData.get(p.getMonth()).setSecond(p.getActualPercentage().setScale(2, RoundingMode.HALF_UP));
        });

        List<BigDecimal> plannedPercent = monthlyPercentData.values().stream().map(ValuePair::getFirst).collect(Collectors.toList());
        List<BigDecimal> actualPercent = monthlyPercentData.values().stream().map(ValuePair::getSecond).collect(Collectors.toList());

        List<BigDecimal> plannedWithZeroes = planned.stream().map(p -> p == null ? new BigDecimal(0) : p).collect(Collectors.toList());
        List<BigDecimal> actualWithZeroes = planned.stream().map(p -> p == null ? new BigDecimal(0) : p).collect(Collectors.toList());

        Optional<BigDecimal> pvMax = plannedWithZeroes.stream()
                .max(Comparator.naturalOrder());
        Optional<BigDecimal> avMax = actualWithZeroes.stream().max(Comparator.naturalOrder());
        BigDecimal maxValue = new BigDecimal("0");
        if (pvMax.isPresent()) {
            if (avMax.isPresent()) {
                maxValue = pvMax.get().max(avMax.get());
            } else {
                maxValue = pvMax.get();
            }
        } else {
            if (avMax.isPresent()) maxValue = avMax.get();
        }

        maxValue = maxValue.add(new BigDecimal("999"));
        maxValue = maxValue.divideToIntegralValue(new BigDecimal("1000"));
        maxValue = maxValue.multiply(new BigDecimal("1000"));

        return new SCurveData(months, planned, actual, plannedPercent, actualPercent, maxValue);
    }

    @GetMapping("/project/{projectId}/spiVarianceData")
    SPIVarianceData getSPIVarianceData(@PathVariable(value = "projectId") long projectId) {

        SCurveData data = getSCurveData(projectId);
        int len = data.getPlannedValues().size();
        ArrayList<BigDecimal> spiData = new ArrayList<>();
        ArrayList<BigDecimal> varianceData = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (data.getActualValues().get(i) != null)
                spiData.add(data.getActualValues().get(i).divide(data.getPlannedValues().get(i), RoundingMode.HALF_UP));
            else
                spiData.add(null);
        }

        for (int i = 0; i < len; i++) {
            if (data.getCumulativeActualPercentValues().get(i) != null)
                varianceData.add(data.getCumulativeActualPercentValues().get(i).subtract(data.getCumulativePlannedPercentValues().get(i)).setScale(2, RoundingMode.HALF_UP));
            else
                varianceData.add(null);
        }
        return new SPIVarianceData(data.getMonths(), spiData, varianceData);
    }
}
