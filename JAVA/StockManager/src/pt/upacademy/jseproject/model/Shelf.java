package pt.upacademy.jseproject.model;

public class Shelf extends MyEntity{
	private Product product;
	private int capacity = 1;
	private double diariaAluguer;

	public Shelf() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getDiariaAluguer() {
		return diariaAluguer;
	}

	public void setDiariaAluguer(double diariaAluguer) {
		this.diariaAluguer = diariaAluguer;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public void clearShelf() {
		this.capacity = 1;
		this.product = null;
	}

	public void showShelf() {
		StringBuilder auxStr = new StringBuilder();
		auxStr.append("Prateleira " + this.ID + "\n");
		auxStr.append("Diaria de aluguer: " + this.diariaAluguer + "\n");
		if(this.capacity == 1) {
			auxStr.append("Prateleira está vazia \n");			
		}
		if(this.product != null) {
			auxStr.append("Produto:\n");
			auxStr.append(product.getName()+ "\n");
		}
		auxStr.append("----------/----------");
		System.out.println(auxStr);
	}
}