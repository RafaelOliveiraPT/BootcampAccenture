package pt.upacademy.jseproject.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pt.upacademy.jseproject.model.MyEntity;


public abstract class EntityRepository <T extends MyEntity> {
	protected Map<Long, T> map = new HashMap<Long, T>();
	protected long currentId = 0;
	private static long globalID = 0;

	private long getNextId() {
		return globalID++;
	}
	
	public T get(long id) {
		return map.get(id);
	}
	
	public Collection<T> getAll(){
		return map.values();
	}
	
	public Long add(T t) {
		long nextId = getNextId();
		t.setId(nextId);
		map.put(nextId, t);
		return ++currentId;
	}
	
	public void update(T t) {
		t.setId(getNextId());
		map.put(t.getId(), t);
	}
	
	public void remove(long id) {
		map.remove(id);
	}
	
	public int size() {
		return map.size();
	}
}
