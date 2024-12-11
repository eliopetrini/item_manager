package com.itemmanager.itemmanager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Entity
@Table(name ="stored_items")
public class StoredItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonInclude
    private long id;
    private String itemName;
    private String itemDescription;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date itemBought;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date itemExpires;
    private int itemAmount;
    private String itemCategory;

    @OneToMany(mappedBy="storedItems")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ChangeLog> changeLog = new ArrayList<>();

    @ManyToOne
    private Location itemLocation;

    private String itemComment;

    public StoredItems(String itemName, String itemDescription, Date itemBought, Date itemExpires, int itemAmount, Location itemLocation) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemBought = itemBought;
        this.itemExpires = itemExpires;
        this.itemAmount = itemAmount;
        this.itemLocation = itemLocation;
    }

    public StoredItems() {
    }

    public List<ChangeLog> getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(List<ChangeLog> changeLog) {
        this.changeLog.addAll(changeLog);
        this.changeLog.forEach(changeLog1 -> changeLog1.setStoredItems(this));

    }

    public String getItemComment() {
        return itemComment;
    }

    public void setItemComment(String itemComment) {
        this.itemComment = itemComment;
    }

    public long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Date getItemBought() {
        return itemBought;
    }

    public void setItemBought(Date itemBought) {
        this.itemBought = itemBought;
    }

    public Date getItemExpires() {
        return itemExpires;
    }

    public void setItemExpires(Date itemExpires) {
        this.itemExpires = itemExpires;
    }

    public long getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Location getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(Location itemLocation) {
        this.itemLocation = itemLocation;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    @Override
    public String toString() {
        return "Utenti{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
