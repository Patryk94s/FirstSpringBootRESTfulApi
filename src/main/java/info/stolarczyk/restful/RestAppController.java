package info.stolarczyk.restful;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import info.stolarczyk.domain.Notes;
import info.stolarczyk.exception.HttpException;
import info.stolarczyk.service.NotesService;

@RestController
@RequestMapping("/api")
class RestAppController {

	
	private final NotesService notesService;
	
	@Autowired
	public RestAppController(NotesService notesService) {
		this.notesService = notesService;
	}

	// get all Notes

	@RequestMapping(method = RequestMethod.GET, value = ("/getNotes"))
	@ResponseBody
	public List<Notes> findAll() {

		return notesService.getListObj();

	}

	// get Notes updated for more than a month

	@RequestMapping(method = RequestMethod.GET, value = ("/getNotesMonth"))
	@ResponseBody
	public List<Notes> findAllMonth() {

		return notesService.getListObjMonth();

	}

	// create a new Note

	@RequestMapping(method = RequestMethod.POST, value = ("/saveNotes"))
	@ResponseBody
	public Notes create(@RequestBody Notes notesEntity) {

		return notesService.create(notesEntity);

	}

	// create a new Note and return a map of errors

	@RequestMapping(method = RequestMethod.POST, value = ("/savemapNotes"))

	@ResponseBody
	public Map<String, Object> create(@Valid @RequestBody Notes notesEntity, BindingResult bindingResult) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();

		if (bindingResult.hasErrors()) {

			List<FieldError> errors = bindingResult.getFieldErrors();
			response.put("Message", "Error");

			for (FieldError err : errors) {

				response.put(err.getDefaultMessage(), err.getField());
			}

		} else {
			notesService.create(notesEntity);
			response.put("Message", "Notes created successfully");

		}
		return response;

	}

	// get a single Note by id

	@RequestMapping(method = RequestMethod.GET, value = ("/getNotesById/{id}"))
	@ResponseBody
	public Notes findById(@Valid @PathVariable Long id) {
		Notes notes = notesService.findId(id);

		if (notes == null) {
			throw new HttpException("Note", "id", id);

		}

		return notes;

	}

	// update a Note

	@RequestMapping(method = RequestMethod.PUT, value = ("/getNotesById/{id}"))
	@ResponseBody
	public Notes updateNotes(@PathVariable Long id, @Valid @RequestBody Notes not) {

		Notes notes = notesService.findId(id);

		if (notes == null) {
			throw new HttpException("Note", "id", id);
		}

		notes.setTitle(not.getTitle());
		notes.setContent(not.getContent());

		Notes updateNotes = notesService.create(notes);
		return updateNotes;

	}

	// delete a Note

	@RequestMapping(method = RequestMethod.DELETE, value = ("/getNotesById/{id}"))
	@ResponseBody
	public void deleteById(@Valid @PathVariable Long id) {
		Notes notes = notesService.findId(id);

		if (notes == null) {
			throw new HttpException("Note", "id", id);
		}

		notesService.remove(id);

	}

}
