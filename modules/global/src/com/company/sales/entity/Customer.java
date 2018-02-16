package com.company.sales.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "SALES_CUSTOMER")
@Entity(name = "sales$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = 1824096256929875373L;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    @Column(name = "EMAIL", length = 50)
    protected String email;

    @Column(name = "LOCATION_LAT")
    protected Double locationLat;

    @Column(name = "LOCATION_LNG")
    protected Double locationLng;

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public Double getLocationLat() {
        return locationLat;
    }

    public void setLocationLng(Double locationLng) {
        this.locationLng = locationLng;
    }

    public Double getLocationLng() {
        return locationLng;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


}