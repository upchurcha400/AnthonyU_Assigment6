package com.coderscampus.Assignment6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class TeslaSalesData {
    private LocalDate dateOfSales;
    private Integer numberOfSales;
    private String model;

    public TeslaSalesData(String dateOfSales, Integer numberOfSales, String model) {
        this.dateOfSales = parseDate(dateOfSales);
        this.numberOfSales = numberOfSales;
        this.model = model;
    }

    private LocalDate parseDate(String dateOfSales) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
                return LocalDate.parse(dateOfSales, formatter);
    }

    public LocalDate getDateOfSales() {
        return dateOfSales;
    }

    public void setDateOfSales(String dateOfSales) {
        this.dateOfSales = LocalDate.parse(dateOfSales);
    }

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
