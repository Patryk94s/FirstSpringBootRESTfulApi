package info.stolarczyk.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event.ID;

import info.stolarczyk.domain.Notes;
import info.stolarczyk.repository.NotesRepository;



@Service
public class NotesService implements ServiceInterface {

	private NotesRepository notesRepository;

	@Autowired
	public NotesService(NotesRepository notesRepsoitory) {
		this.notesRepository = notesRepsoitory;
	}

	public Notes create(Notes obj) {
		return notesRepository.save(obj);
	}

	public Notes findId(Long id) {
		Notes notes = notesRepository.findOne(id);
		return notes;
	}

	public List<Notes> getListObj() {
		List<Notes> notesList = notesRepository.findAll();
		return notesList;
	}

	public List<Notes> getListObjMonth() {
		List<Notes> notesMonth = new ArrayList<Notes>();
		List<Notes> notesList = notesRepository.findAll();

		for (Notes not : notesList) {

		//	System.out.println(not.getDateModified().getTime());
			long milisDiff = new Date().getTime() - not.getDateModified().getTime();
			double daysDiff = milisDiff / 86400000;

			//System.out.println(daysDiff);
			if (daysDiff >= 30) {

				notesMonth.add(not);

			}

		}

		return notesMonth;
	}

	public void remove(Long id) {

		notesRepository.delete(id);
	}

	

}
