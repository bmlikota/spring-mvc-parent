package hr.bm.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import hr.bm.dto.MyData;
import hr.bm.error.MyDataNotFoundException;

@Controller
@RequestMapping("/bm-service")
public class RestController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";

	private List<MyData> repo = new ArrayList<MyData>();

	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MyData> getList(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		for (int i = 0; i < count; i++) {
			double num = Math.random() * 100;
			if (num < max) {
				MyData data = new MyData();
				data.setId(num);
				data.setMessage("Ovo je " + num);
				repo.add(data);
			}
		}
		return repo;
	}


	// Version 1
//	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
//	@ResponseStatus(HttpStatus.CREATED) // moze i bez ovog
//	public @ResponseBody MyData saveData(@RequestBody MyData myData) {
//		repo.add(myData);
//		return myData;
//	}

	// Version 2
//	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
//	@ResponseStatus(HttpStatus.CREATED) // moze i bez ovog
//	public ResponseEntity<MyData> saveData(@RequestBody MyData myData) {
//		repo.add(myData);
//		HttpHeaders headers = new HttpHeaders();
//		URI locationURI = URI.create("localhost:8080/service/rest/" + myData.getId());
//		headers.setLocation(locationURI);
//		return new ResponseEntity<MyData>(myData, headers, HttpStatus.CREATED);
//	}

	// Version 3
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED) // moze i bez ovog
	public ResponseEntity<MyData> saveData(@RequestBody MyData myData, UriComponentsBuilder ucb) {
		repo.add(myData);
		HttpHeaders headers = new HttpHeaders();
		URI locationURI = ucb.path("/bm-service/").path(String.valueOf(myData.getId())).build().toUri();
		headers.setLocation(locationURI);
		return new ResponseEntity<MyData>(myData, headers, HttpStatus.CREATED);
	}

	// Version 1
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public @ResponseBody MyData getById(@PathVariable long id) {
//		return findOne(id);
//	}

	// Version 2
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<MyData> getById(@PathVariable long id) {
//		MyData data = findOne(id);
//		HttpStatus status = data != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//		return new ResponseEntity<MyData>(data, status);
//	}

	// Version 3
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<?> getById(@PathVariable long id) {
//		MyData myData = findOne(id);
//		if (myData == null) {
//			Error error = new Error(4, "Spittle [" + id + "] not found");
//			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<MyData>(myData, HttpStatus.OK);
//	}

	// Version 4
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<MyData> getById(@PathVariable long id) {
//		MyData myData = findOne(id);
//		if (myData == null) {
//			throw new MyDataNotFoundException(id);
//		}
//		return new ResponseEntity<MyData>(myData, HttpStatus.OK);
//	}

	// Version 5
	/**
	 * Latest version (see upwards)
	 * 
	 * @param id
	 * @return {@link MyData}
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody MyData getById(@PathVariable long id) {
		MyData myData = findOne(id);
		if (myData == null) {
			throw new MyDataNotFoundException(id);
		}
		return myData;
	}

	private MyData findOne(long id) {
		for (MyData data : repo) {
			if (id == data.getId()) {
				return data;
			}
		}
		return null;
	}

}
