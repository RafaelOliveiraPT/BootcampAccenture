package pt.upacademy.jseproject.model;

import java.util.ArrayList;
import java.util.List;

public class Product extends MyEntity {

	private List<Shelf> shelves;
	private String name;
	private double discount;
	private double iva;
	private double pvp;
	
	public Product() {
		this.shelves = new ArrayList<Shelf>();
	}
	
	public List<Shelf> getShelves() {
		return shelves;
	}
	
	public void addPrateleira(Shelf prateleira) {
		this.shelves.add(prateleira);
	}

	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getIva() {
		return iva;
	}
	
	public void setIva(double iva) {
		this.iva = iva;
	}
	
	public double getPvp() {
		return pvp;
	}
	
	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void clearShelves() {
		for (Shelf shelf : shelves) {
			shelf.clearShelf();
		}
		shelves.clear();
	}


	public void showProduct() {
		StringBuilder auxStr = new StringBuilder();
		shelves.sort((a,b) -> { return (a.ID < b.ID ? -1 : 1);});
		auxStr.append("Produto [ID=" + this.ID + ", name=" + name + ", pvp=" + pvp + ", iva=" + iva +", discount=" + discount + ", shelves=" );  
		for (Shelf shelf : shelves) {
			auxStr.append("[" + shelf.ID + "] ");
		} 
		auxStr.append("]");
		System.out.println(auxStr);
	}

	public void removeShelf(long shelfId) {
		for (Shelf shelf : shelves) {
			if(shelf.getId() == shelfId) {
				shelves.remove(shelf);
			}
		}
	}
}

/*

Um produto tem:
○ Um código (ID)
○ Uma lista de prateleiras onde está exposto
○ Valor unitário de desconto
○ IVA (Imposto de Valor Acrescentado em percentagem)
○ PVP (Preço de Venda ao Público)

*/