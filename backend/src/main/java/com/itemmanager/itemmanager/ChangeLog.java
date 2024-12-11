package com.itemmanager.itemmanager;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name ="change_log")
public class ChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logid;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date changeDate;
    private int currentAmount;
    private int changeAmount;
    private String logMessage;

    @ManyToOne(optional = false)
    @JoinColumn(name="id")
    @JsonIgnore
    private StoredItems storedItems;

    public ChangeLog(Date changeDate, int currentAmount, int changeAmount) {
        this.changeDate = new Date();
        this.currentAmount = currentAmount;
        this.changeAmount = changeAmount;
    }

    public ChangeLog() {

    }

    public StoredItems getStoredItems() {
        return storedItems;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public int isChangeAmount() {
        return changeAmount;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setStoredItems(StoredItems storedItems) {
        this.storedItems = storedItems;
    }

    public long getLogid() {
        return logid;
    }

    public void setId(long id) {
        this.logid = id;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public int getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(int changeAmount) {
        this.changeAmount = changeAmount;
    }
}

