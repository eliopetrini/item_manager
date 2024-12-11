package com.itemmanager.itemmanager;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long locationId;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedDate;
    private String name;
    private String description;
    private int itemAmount;


    private int totalItemAmount;

    @OneToMany(mappedBy = "itemLocation", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<StoredItems> storedItems = new ArrayList<>();

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Location() {

    }

    public int getItemAmount() {
        return storedItems.size();
    }

    public int getTotalItemAmount() {
        return storedItems.stream().mapToInt(o -> (int) o.getItemAmount()).sum();
    }

    public List<StoredItems> getStoredItems() {
        return storedItems;
    }

    public void setStoredItems(List<StoredItems> storedItems) {
        this.storedItems.addAll(storedItems);
        this.storedItems.forEach(storedItems1 -> storedItems1.setItemLocation(this));
    }

    public long getLocationId() {
        return locationId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = new Date();
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

