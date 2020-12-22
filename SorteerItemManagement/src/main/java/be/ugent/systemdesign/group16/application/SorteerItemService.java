package be.ugent.systemdesign.group16.application;

public interface SorteerItemService {

	Response maakBriefSorteerItem();
	Response sorteerItemAangekomenOpNieuweLocatie();
	Response sorteerItemAangekomenOpLaatsteLocatie();
	Response sorteerItemGesorteerd();
}