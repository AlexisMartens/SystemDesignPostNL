package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.SorteerItem;
import be.ugent.systemdesign.group16.domain.SorteerItemRepository;

@Transactional
@Service
public class SorteerItemServiceImpl implements SorteerItemService{
	
	@Autowired
	SorteerItemRepository repo;

	@Override
	public Response maakNieuwSorteerItem(SorteerItem _s) {
		try {
			repo.save(_s);
		}
		catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "id: "+_s.getSorteerItemId());
		}
		return new Response(ResponseStatus.SUCCESS, "id: "+_s.getSorteerItemId());
	}

	
}
