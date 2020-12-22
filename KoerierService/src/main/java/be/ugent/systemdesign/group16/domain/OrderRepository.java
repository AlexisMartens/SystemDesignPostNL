package be.ugent.systemdesign.group16.domain;

public interface OrderRepository {
	public Order findOne(Integer id);	
	public void save(Order o);
	public Integer countByKoerier(Koerier k);
}
