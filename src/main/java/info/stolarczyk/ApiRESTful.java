package info.stolarczyk;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import info.stolarczyk.domain.Notes;
import info.stolarczyk.service.NotesService;


@SpringBootApplication
@EnableJpaAuditing
@Configuration
@ComponentScan
public class ApiRESTful implements CommandLineRunner {

	private NotesService notesService;
	
	
	@Autowired
	public ApiRESTful(NotesService notesService) {
		super();
		this.notesService = notesService;
	}





	public static void main( String[] args ) {
   SpringApplication.run(ApiRESTful.class, args);
    }

	
	
	public void run(String... arg0) throws Exception {
	
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String dateString = "04.03.2018";
		Date date = dateFormat.parse(dateString);
		
		
		Notes notes = new Notes("title", "content", date, date);
		
		notesService.create(notes);
		
	}

}
