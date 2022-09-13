package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qa.ade.project_dashboard.excel.*;
import qa.ade.project_dashboard.exceptions.ConflictException;
import qa.ade.project_dashboard.model.*;
import qa.ade.project_dashboard.service.PlanUploadService;
import qa.ade.project_dashboard.storage.StorageFileNotFoundException;
import qa.ade.project_dashboard.storage.StorageService;

import java.io.File;
import java.util.Map;

@ComponentScan("qa.ade.project_dashboard")
@RestController
public class ExcelValuesUploadController {
    @Autowired
    private StorageService storageService;

    @Autowired
    private PlanUploadService planUploadService;

    @PostMapping("/project/new")
    public void createNewProject(@RequestParam("file") MultipartFile excelFile) {
        UserDetails userDetails = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails) auth.getPrincipal();
        }
        if (userDetails != null) {
            File uploadedFile = storageService.store(excelFile);
            if (excelFile.getOriginalFilename().endsWith(".xls") || excelFile.getOriginalFilename().endsWith(".xlsx")) {
                Map<String, Object[][]> sheetValues = ExcelReadHelper.getSheetValues(uploadedFile);
                InvoicingPlan invoicingPlan = InvoicingPlanParser.parse(sheetValues.get("Invoicing Plan"));
                ProjectPlan projectPlan = ProjectPlanParser.parse(sheetValues.get("PV"));
                WBS wbs = WBSParser.parse(sheetValues.get("WBS"));
                Project parsedProject = ProjectParser.parse(sheetValues.get("ProjectInfo"));
                parsedProject.setId(0); // Create new project
                parsedProject.setAddedBy(userDetails.getUsername());
                OperatingBudget budget = OperatingBudgetParser.parse(sheetValues.get("CommercialBudgetProfit"));
                try {
                    planUploadService.updatePlan(parsedProject, invoicingPlan, projectPlan, wbs, budget);
                } catch (DuplicateKeyException exception) {
                    throw new ConflictException("Project with same Contract No. already exists.");
                }
            }
        }
    }

    @PostMapping("/project/{projectId}/uploadExcel")
    public void handleFileUpload(@PathVariable(value = "projectId") long projectId,
                                 @RequestParam("file") MultipartFile file) {

        File uploadedFile = storageService.store(file);
        if (file.getOriginalFilename().endsWith(".xls") || file.getOriginalFilename().endsWith(".xlsx")) {
            Map<String, Object[][]> sheetValues = ExcelReadHelper.getSheetValues(uploadedFile);
            InvoicingPlan invoicingPlan = InvoicingPlanParser.parse(sheetValues.get("Invoicing Plan"));
            ProjectPlan projectPlan = ProjectPlanParser.parse(sheetValues.get("PV"));
            WBS wbs = WBSParser.parse(sheetValues.get("WBS"));
            Project project = new Project();
            Project parsedProject = ProjectParser.parse(sheetValues.get("ProjectInfo"));
            project.setId(projectId);
            OperatingBudget budget = OperatingBudgetParser.parse(sheetValues.get("CommercialBudgetProfit"));
            planUploadService.updatePlan(project, invoicingPlan, projectPlan, wbs, budget);
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
