package hr.bm.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import hr.bm.dto.BmRestData;

@Controller
@RequestMapping(value = "/rest")
public class RestConsumeController {

	private static final String URL_SAVE = "http://localhost:8094/rest-ws-example/bm-service/save";

	@RequestMapping(value = "/consume")
	public String consumeRest() {
		RestTemplate rest = new RestTemplate();
		URI targetUrl = UriComponentsBuilder.fromUriString("http://localhost:8094/rest-ws-example/bm-service/get")
				.queryParam("count", 10)
				.queryParam("max", 50)
				.build()
				.toUri();
		Object response =  rest.getForObject(targetUrl, List.class);
		System.out.println(response);
		return "home/home";
	}

	@RequestMapping(value = "/consume/{id}")
	public String getById(@PathVariable("id") int id) {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<BmRestData> response =  (ResponseEntity<BmRestData>) rest.getForEntity("http://localhost:8094/rest-ws-example/bm-service/" + id, BmRestData.class);
		if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Bad request!");
		}
		BmRestData data = (BmRestData) response.getBody(); // java.lang.NoClassDefFoundError - maven update!?
		System.out.println(data);

		return "home/home";
	}

	@RequestMapping(value = "/save")
	public String saveData() {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<BmRestData> response = rest.postForEntity(URL_SAVE, new BmRestData("Podaci poslani iz spring-mvc-web", 2), BmRestData.class);
		BmRestData data = response.getBody();
		URI urlResponse = response.getHeaders().getLocation();
		System.out.println("Spremljen je podatak: " + data);
		System.out.println("Na lokaciji: " + urlResponse);
		return "home/home";
	}

	@RequestMapping(value = "/save2")
	public String saveData2() {

		RestTemplate rest = new RestTemplate();
		String url = rest.postForLocation(URL_SAVE, new BmRestData("Podaci poslani iz spring-mvc-web save2", 3)).toString();
		System.out.println(url);

		return "home/home";
	}

}
