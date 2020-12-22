package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface OrderRepository {
	public Order findOne(Integer id);	
	public void save(Order o);
}
