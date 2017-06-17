package banco;

import java.util.List;

public interface GenericDao<E> {
	
	List<E> all();
	boolean insert(E e);
	boolean update(E e);
	boolean delete(int i);
	
}
