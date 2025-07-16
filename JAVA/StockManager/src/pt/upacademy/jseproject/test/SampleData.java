package pt.upacademy.jseproject.test;

import java.util.ArrayList;

import pt.upacademy.jseproject.model.Product;
import pt.upacademy.jseproject.model.Shelf;
import pt.upacademy.jseproject.repositories.ProductRepository;
import pt.upacademy.jseproject.repositories.ShelfRepository;

public class SampleData {
	private ProductRepository productRepository;
	private ShelfRepository shelfRepository;
	
	public SampleData() {
		productRepository = ProductRepository.getInstance();
		shelfRepository = ShelfRepository.getInstance();
	}
	
	private ArrayList<Product> getSampleDataProducts(){
		ArrayList<Product> dataSampleProductsList = new ArrayList<Product>(); 
		// dados para teste
		Product a1 = new Product();
		a1.setDiscount(2);
		a1.setIva(23);
		a1.setPvp(22);
		a1.setName("Sapato");
		dataSampleProductsList.add(a1);
		
		Product a2 = new Product();
		a2.setDiscount(2);
		a2.setIva(23);
		a2.setPvp(22);
		a2.setName("Camisola");
		dataSampleProductsList.add(a2);
		
		Product a3 = new Product();
		a3.setDiscount(5);
		a3.setIva(23);
		a3.setPvp(40);
		a3.setName("Calças");
		dataSampleProductsList.add(a3);

		Product a4 = new Product();
		a4.setDiscount(10);
		a4.setIva(23);
		a4.setPvp(15);
		a4.setName("Boné");
		dataSampleProductsList.add(a4);

		Product a5 = new Product();
		a5.setDiscount(0);
		a5.setIva(23);
		a5.setPvp(50);
		a5.setName("Relógio");
		dataSampleProductsList.add(a5);
		
		return dataSampleProductsList;
	}
	
	private ArrayList<Shelf> getSampleDataShelves(){
		ArrayList<Shelf> dataSampleShelvesList = new ArrayList<Shelf>();
		Shelf p1 = new Shelf();
		p1.setCapacity(1);
		p1.setDiariaAluguer(.20);
		dataSampleShelvesList.add(p1);
		Shelf p2 = new Shelf();
		p2.setCapacity(1);
		p2.setDiariaAluguer(0.30);
		dataSampleShelvesList.add(p2);

		Shelf p3 = new Shelf();
		p3.setCapacity(1);
		p3.setDiariaAluguer(0.40);
		dataSampleShelvesList.add(p3);

		Shelf p4 = new Shelf();
		p4.setCapacity(1);
		p4.setDiariaAluguer(0.25);
		dataSampleShelvesList.add(p4);

		Shelf p5 = new Shelf();
		p5.setCapacity(1);
		p5.setDiariaAluguer(0.50);
		dataSampleShelvesList.add(p5);
		
		return dataSampleShelvesList;
	}
	
	public void loadSampleData() {
		for (Product product : getSampleDataProducts()) {
			productRepository.add(product);
		}
		for (Shelf shelf : getSampleDataShelves()) {
			shelfRepository.add(shelf);
		}
	}
}
