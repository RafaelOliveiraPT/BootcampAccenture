package pt.upacademy.jseproject.model;

public abstract class MyEntity {

	protected long ID;
	
	protected MyEntity() {
	
	}

	public long getId() {
		return ID;
	}
	
	public void setId(long ID) {
		this.ID = ID;
	}
}
