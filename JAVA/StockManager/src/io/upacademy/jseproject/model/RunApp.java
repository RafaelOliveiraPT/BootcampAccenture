package io.upacademy.jseproject.model;

import java.util.ArrayList;
import java.util.Scanner;
import io.upacademy.jseproject.test.SampleData;
import io.upacademy.jseproject.textinterface.TextInterface;

public class RunApp {
	private ArrayList<Product> productsList = new ArrayList<Product>();
	private ArrayList<Shelf> shelvesList = new ArrayList<Shelf>();
	private Scanner reader = new Scanner(System.in);

	ArrayList<Product> getProductsList() {
		return productsList;
	}

	ArrayList<Shelf> getShelvesList() {
		return shelvesList;
	}

	Scanner getReader() {
		return reader;
	}

	public static void beginApp() {
		RunApp myApplication = new RunApp();
		loadSampleData(myApplication);
		TextInterface.showMenu(myApplication);
	}

	private static void loadSampleData(RunApp myApplication) {
		ArrayList<Product> productsList = SampleData.getSampleDataProducts();
		ArrayList<Shelf> shelvesList = SampleData.getSampleDataShelves();
		
		for (Shelf shelf : shelvesList) {
			myApplication.shelvesList.add(shelf);
		}
		for (Product product : productsList) {
			myApplication.productsList.add(product);
		}
	}
}
