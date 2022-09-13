package qa.ade.project_dashboard.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private long id;
    private String contractNo;
    private String contractTitle;
    private String client;
    private String consultant;
    private String typeOfContract;
    private String formOfContract;
    private BigDecimal contractValue;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date completionDate;
    private int duration;
    private int maintenancePeriod;
    private String status;
    private Timestamp createdOn;
    private String addedBy;
    private List<ProjectProperty> properties;

    public Project() {
        id = 0;
        contractNo = "";
        contractTitle = "";
        client = "";
        consultant = "";
        typeOfContract = "";
        formOfContract = "";
        contractValue = new BigDecimal("0");
        startDate = Date.valueOf(LocalDate.EPOCH);
        completionDate = Date.valueOf(LocalDate.EPOCH);
        duration = 0;
        maintenancePeriod = 0;
        status = "";
        properties = new ArrayList<>();
        this.addedBy = "";
    }

    public Project(long id, String contractNo, String contractTitle, String client, String consultant, String typeOfContract, String formOfContract, BigDecimal contractValue, Date startDate, Date completionDate, int duration, int maintenancePeriod, String status, List<ProjectProperty> properties, String addedBy) {
        this.id = id;
        this.contractNo = contractNo;
        this.contractTitle = contractTitle;
        this.client = client;
        this.consultant = consultant;
        this.typeOfContract = typeOfContract;
        this.formOfContract = formOfContract;
        this.contractValue = contractValue;
        this.startDate = startDate;
        this.completionDate = completionDate;
        this.duration = duration;
        this.maintenancePeriod = maintenancePeriod;
        this.status = status;
        this.properties = properties;
        this.addedBy = addedBy;
    }

    public long getId() {
        return id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public String getClient() {
        return client;
    }

    public String getConsultant() {
        return consultant;
    }

    public String getTypeOfContract() {
        return typeOfContract;
    }

    public String getFormOfContract() {
        return formOfContract;
    }

    public BigDecimal getContractValue() {
        return contractValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public int getDuration() {
        return duration;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public int getMaintenancePeriod() {
        return maintenancePeriod;
    }

    public String getStatus() {
        return status;
    }

    public List<ProjectProperty> getProperties() {
        return properties;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public void setTypeOfContract(String typeOfContract) {
        this.typeOfContract = typeOfContract;
    }

    public void setFormOfContract(String formOfContract) {
        this.formOfContract = formOfContract;
    }

    public void setContractValue(BigDecimal contractValue) {
        this.contractValue = contractValue;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMaintenancePeriod(int maintenancePeriod) {
        this.maintenancePeriod = maintenancePeriod;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public void setProperties(List<ProjectProperty> properties) {
        this.properties = properties;
    }

    public String toString() {
        return "Project{" +
                "id=" + id +
                ", contractNo='" + contractNo + '\'' +
                ", contractTitle='" + contractTitle + '\'' +
                ", client='" + client + '\'' +
                ", consultant='" + consultant + '\'' +
                ", typeOfContract='" + typeOfContract + '\'' +
                ", formOfContract='" + formOfContract + '\'' +
                ", contractValue=" + contractValue +
                ", startDate=" + startDate +
                ", completionDate=" + completionDate +
                ", duration=" + duration +
                ", maintenancePeriod=" + maintenancePeriod +
                ", status='" + status + '\'' +
                ", createdOn=" + createdOn +
                ", properties=" + properties +
                '}';
    }
}
