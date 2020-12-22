package be.ugent.systemdesign.group16.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.group16.domain.SorteerItemRepository;

@Transactional
@Service
public class SorteerItemServiceImpl implements SorteerItemService{
	
	@Autowired
	SorteerItemRepository repo;

	@Override
	public Response maakBriefSorteerItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response sorteerItemAangekomenOpNieuweLocatie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response sorteerItemAangekomenOpLaatsteLocatie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response sorteerItemGesorteerd() {
		// TODO Auto-generated method stub
		return null;
	}

}
