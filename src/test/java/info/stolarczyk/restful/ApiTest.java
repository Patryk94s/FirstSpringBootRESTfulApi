package info.stolarczyk.restful;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.databind.ObjectMapper;

import info.stolarczyk.ApiRESTful;
import info.stolarczyk.repository.NotesRepository;





@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiRESTful.class)
@WebAppConfiguration


public class ApiTest {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final RestTemplate restTemplate = new RestTemplate();
	
	
	@Autowired
	public ApiTest(NotesRepository notesRepository) {
		this.notesRepository = notesRepository;
	}


	private NotesRepository notesRepository;
	
	
	
	@Test
	public void createNotes() throws IOException {
		
		Map<String, Object> requestBody = new HashMap();
		
		requestBody.put("title", "Tytul");
		requestBody.put("content", "Kontent");
		requestBody.put("dateCreated", new Date());
		requestBody.put("dateModyfied", new Date());
		
		HttpHeaders httpheaders = new HttpHeaders();
		
		httpheaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), httpheaders);
	
		Map<String,Object> apiResonse = restTemplate.postForObject("http://localhost:8080/api/savemapNotes", httpEntity, Map.class, Collections.emptyMap());
		assertNotNull(apiResonse);
	
	}

}
