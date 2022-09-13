package qa.ade.project_dashboard.excel;

import qa.ade.project_dashboard.model.WBS;
import qa.ade.project_dashboard.model.WBSComponent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WBSParser implements ExcelParser {
    public static WBS parse(Object[][] sheet) {
        WBS wbs = new WBS();
        List<WBSComponent> wbsComponents = new ArrayList<>();
        for (int i = 1; i < sheet.length; i++) {
            Object[] row = sheet[i];
            String name = (String) row[0];
            Double weightagePercent = (Double) row[1];
            WBSComponent wbsComponent = new WBSComponent();
            wbsComponent.setName(name);
            wbsComponent.setPercent(BigDecimal.valueOf(weightagePercent));

            wbsComponents.add(wbsComponent);
        }
        wbs.setComponents(wbsComponents);
        return wbs;
    }
}
