package qa.ade.project_dashboard.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.sql.Date;

public class ProjectPayment {
    private long id;
    private long ipcNo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date valueDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submittedDate;
    private BigDecimal grossAmount;
    private BigDecimal accumulatedValAmount;
    private BigDecimal netPayableAmount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date certifiedDate;
    private BigDecimal certifiedAmount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paymentDueDate;
    private BigDecimal receivableAmount;
    private BigDecimal receivedAmount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receivedDate;
    private String remarks;
    private long projectId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIpcNo() {
        return ipcNo;
    }

    public void setIpcNo(long ipcNo) {
        this.ipcNo = ipcNo;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public BigDecimal getAccumulatedValAmount() {
        return accumulatedValAmount;
    }

    public void setAccumulatedValAmount(BigDecimal accumulatedValAmount) {
        this.accumulatedValAmount = accumulatedValAmount;
    }

    public BigDecimal getNetPayableAmount() {
        return netPayableAmount;
    }

    public void setNetPayableAmount(BigDecimal netPayableAmount) {
        this.netPayableAmount = netPayableAmount;
    }

    public Date getCertifiedDate() {
        return certifiedDate;
    }

    public void setCertifiedDate(Date certifiedDate) {
        this.certifiedDate = certifiedDate;
    }

    public BigDecimal getCertifiedAmount() {
        return certifiedAmount;
    }

    public void setCertifiedAmount(BigDecimal certifiedAmount) {
        this.certifiedAmount = certifiedAmount;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
