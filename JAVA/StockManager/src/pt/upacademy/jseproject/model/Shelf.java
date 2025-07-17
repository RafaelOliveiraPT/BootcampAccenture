package pt.upacademy.jseproject.model;

public class Shelf extends MyEntity{
	private Product product;
	private int capacity = 1;
	private double diariaAluguer;

	public Shelf() {
	}

	public Product getProduto() {
		return product;
	}

	public void setProduto(Product produto) {
		this.product = produto;
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
		auxStr.append("Prateleira:\n");
		auxStr.append("ID=" + this.ID + "\n");
		auxStr.append("Capacity=" + this.capacity + "\n");
		if(this.product != null) {
			auxStr.append("Produto:\n");
			auxStr.append(product.getName()+ "\n");
		}
		auxStr.append("----------/----------");
		System.out.println(auxStr);
	}
}