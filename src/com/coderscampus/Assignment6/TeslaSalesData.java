package com.coderscampus.Assignment6;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TeslaSalesData {
    private YearMonth dateOfSales;
    private Integer numberOfSales;
    private String model;

    public TeslaSalesData(YearMonth dateOfSales, Integer numberOfSales, String model) {
        this.dateOfSales = dateOfSales;
        this.numberOfSales = numberOfSales;
        this.model = model;
    }

    private YearMonth parseDate(String dateOfSales) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MMM");

        try {
            return YearMonth.parse(dateOfSales, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + dateOfSales);
            e.printStackTrace();
            return null;
        }
    }

    public YearMonth getDateOfSales() {
        return dateOfSales;
    }

    public void setDateOfSales(String dateOfSales) {
        this.dateOfSales = YearMonth.parse(dateOfSales);
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