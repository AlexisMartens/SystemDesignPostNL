package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface VervoerderRepository {
	Integer save(Vervoerder _v);
	List<Vervoerder> findIdleVervoerders();
}
