package be.ugent.systemdesign.group16.domain;

import java.util.List;

public interface SorteerderRepository {
	Integer save(Sorteerder _s);
	List<Sorteerder> findIdleSorteerdersAtCentrum(Locatie _l);
}