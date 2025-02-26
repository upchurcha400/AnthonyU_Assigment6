package com.coderscampus.Assignment6;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;

public class TeslaSalesApplication {

    private final FileService fileService;

    public TeslaSalesApplication() {
        fileService = new FileService();
    }

    public static void main(String[] args) {
        TeslaSalesApplication reportApp =  new TeslaSalesApplication();
        reportApp.analyzeSalesData();
    }

    private void analyzeSalesData() {
        List<TeslaSalesData> model3Sales = fileService.readTeslaSalesData("model3.csv");
        List<TeslaSalesData> modelSSales = fileService.readTeslaSalesData("modelS.csv");
        List<TeslaSalesData> modelXSales = fileService.readTeslaSalesData("modelX.csv");

        analyzeModelSales(model3Sales, "Model 3");
        analyzeModelSales(modelSSales, "Model S");
        analyzeModelSales(modelXSales, "Model X");
    }

    private void analyzeModelSales(List<TeslaSalesData> salesData, String modelName) {
        Map<Integer, Integer> yearlySales = salesData.stream()
                .collect(Collectors.groupingBy(
                        data -> data.getDateOfSales().getYear(),
                        Collectors.summingInt(TeslaSalesData::getNumberOfSales)
                ));

        Map<String, Integer> monthlySales = salesData.stream()
                .collect(Collectors.groupingBy(
                        data -> data.getDateOfSales().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                        Collectors.summingInt(TeslaSalesData::getNumberOfSales)
                ));

        Optional<Map.Entry<String, Integer>> bestMonth = monthlySales.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        Optional<Map.Entry<String, Integer>> worstMonth = monthlySales.entrySet().stream()
                .min(Map.Entry.comparingByValue());

        System.out.println(modelName + " Yearly Sales Report");
        System.out.println("---------------------------");
        yearlySales.forEach((year, sales) -> System.out.println(year + " -> " + sales));

        System.out.println("The best month for " + modelName + " was: " + bestMonth.map(Map.Entry::getKey).orElse("N/A"));
        System.out.println("The worst month for " + modelName + " was: " + worstMonth.map(Map.Entry::getKey).orElse("N/A") + "\n");
    }
}
