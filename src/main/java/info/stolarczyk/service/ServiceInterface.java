package info.stolarczyk.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import info.stolarczyk.domain.Notes;


public interface ServiceInterface {

	public Notes create(Notes obj);

	public void remove(Long id);

	public List<Notes> getListObj();

	public List<Notes> getListObjMonth();

	public Notes findId(Long id);

}
