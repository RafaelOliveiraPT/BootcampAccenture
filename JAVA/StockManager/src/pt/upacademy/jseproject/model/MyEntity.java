package pt.upacademy.jseproject.model;

public abstract class MyEntity {

	private static long globalID = 0;
	private Long ID;
	
	public MyEntity() {
		ID = globalID++;
	}

	public long getId() {
		return ID;
	}
	
	public void setId(long id) {
		this.ID = id;
	}
}
