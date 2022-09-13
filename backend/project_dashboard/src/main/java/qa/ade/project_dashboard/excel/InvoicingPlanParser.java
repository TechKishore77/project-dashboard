package qa.ade.project_dashboard.excel;

import qa.ade.project_dashboard.model.InvoicingPlan;
import qa.ade.project_dashboard.model.InvoicingPlanItem;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class InvoicingPlanParser implements ExcelParser {
    public static InvoicingPlan parse(Object[][] sheet) {
        InvoicingPlan invoicingPlan = new InvoicingPlan();
        boolean exitOuterFor = false;
        List<InvoicingPlanItem> invoicingPlanItems = new ArrayList<>();
        for (int i = 1; i < sheet.length; i++) {
            Object[] row = sheet[i];
            if (row[0] == null || row[1] == null || row[2] == null || (row[0] instanceof String && ((String) row[0]).isBlank()))
                exitOuterFor = true;
            java.util.Date date = (java.util.Date) row[0];
            Double amountDouble = (Double) row[1];
            Double cumulativeAmount = (Double) row[2];
            InvoicingPlanItem invoicingPlanItem = new InvoicingPlanItem();
            invoicingPlanItem.setDay(new Date(date.getTime()));
            invoicingPlanItem.setAmount(BigDecimal.valueOf(amountDouble));
            invoicingPlanItem.setCumulativeAmount(BigDecimal.valueOf(cumulativeAmount));

            invoicingPlanItems.add(invoicingPlanItem);
            if (exitOuterFor) break;
        }
        invoicingPlan.setInvoices(invoicingPlanItems);
        return invoicingPlan;
    }
}
