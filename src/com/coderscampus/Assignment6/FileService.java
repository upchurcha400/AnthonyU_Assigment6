package com.coderscampus.Assignment6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

	public List<TeslaSalesData> read(String filename) throws IOException {
		List<TeslaSalesData> data = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			reader.readLine();
			String line;

			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");

				try {
					String dateString = row[0];
					String salesString = row[1];

					YearMonth date = YearMonth.parse(dateString, formatter);
					int sales = Integer.parseInt(salesString);
					String model = determineModel(filename);
					data.add(new TeslaSalesData(date, sales, model));
				} catch (DateTimeParseException | NumberFormatException e) {
					System.out.println(
							"There was an error is parsing the data in line: " + line + ", due to " + e.getMessage());

				}
			}
		}
		return data;
	}

	private String determineModel(String filename) {
		if (filename.contentEquals("model3")) {
			return "Model 3";
		} else if (filename.contains("modelS")) {
			return "Model S";
		} else if (filename.contains("modelX")) {
			return "Model X";
		} else {
			return "ummmm, we don't know that model... awkward";
		}
	}
}
