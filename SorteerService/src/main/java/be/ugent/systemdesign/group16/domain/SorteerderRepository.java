package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface SorteerderRepository {
	Integer save(Sorteerder _s);
	Integer save(SorteerCentrum _c);
	List<Sorteerder> findIdleSorteerdersAtCentrum(Adres a);
	List<Sorteerder> findAll();
}