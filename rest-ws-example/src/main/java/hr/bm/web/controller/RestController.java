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

import hr.bm.dto.BmRestData;
import hr.bm.error.BmRestDataNotFoundException;



@Controller
@RequestMapping("/bm-service")
public class RestController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";

	private List<BmRestData> repo = new ArrayList<BmRestData>();

	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<BmRestData> getList(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		repo = new ArrayList<BmRestData>();
		for (int i = 0; i < count; i++) {
			double num = Math.random() * 100;
			if (num < max) {
				BmRestData data = new BmRestData();
				data.setId((int) num);
				data.setMessage("Ovo je " + num);
				repo.add(data);
			}
		}
		return repo;
	}


	// Version 1
//	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
//	@ResponseStatus(HttpStatus.CREATED) // moze i bez ovog
//	public @ResponseBody BmRestData saveData(@RequestBody BmRestData myData) {
//		repo.add(myData);
//		return myData;
//	}

	// Version 2
//	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
//	@ResponseStatus(HttpStatus.CREATED) // moze i bez ovog
//	public ResponseEntity<BmRestData> saveData(@RequestBody BmRestData myData) {
//		repo.add(myData);
//		HttpHeaders headers = new HttpHeaders();
//		URI locationURI = URI.create("localhost:8080/service/rest/" + myData.getId());
//		headers.setLocation(locationURI);
//		return new ResponseEntity<BmRestData>(myData, headers, HttpStatus.CREATED);
//	}

	// Version 3
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED) // moze i bez ovog
	public ResponseEntity<BmRestData> saveData(@RequestBody BmRestData myData, UriComponentsBuilder ucb) {
		repo.add(myData);
		HttpHeaders headers = new HttpHeaders();
		URI locationURI = ucb.path("/bm-service/save/").path(String.valueOf(myData.getId())).build().toUri();
		headers.setLocation(locationURI);
		return new ResponseEntity<BmRestData>(myData, headers, HttpStatus.CREATED);
	}

	// Version 1
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public @ResponseBody BmRestData getById(@PathVariable long id) {
//		return findOne(id);
//	}

	// Version 2
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<BmRestData> getById(@PathVariable long id) {
//		BmRestData data = findOne(id);
//		HttpStatus status = data != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//		return new ResponseEntity<BmRestData>(data, status);
//	}

	// Version 3
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<?> getById(@PathVariable long id) {
//		BmRestData myData = findOne(id);
//		if (myData == null) {
//			Error error = new Error(4, "Spittle [" + id + "] not found");
//			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<BmRestData>(myData, HttpStatus.OK);
//	}

	// Version 4
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<BmRestData> getById(@PathVariable long id) {
//		BmRestData myData = findOne(id);
//		if (myData == null) {
//			throw new BmRestDataNotFoundException(id);
//		}
//		return new ResponseEntity<BmRestData>(myData, HttpStatus.OK);
//	}

	// Version 5
	/**
	 * Latest version (see upwards)
	 * 
	 * @param id
	 * @return {@link BmRestData}
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody BmRestData getById(@PathVariable long id) {
		BmRestData myData = findOne(id);
		if (myData == null) {
			throw new BmRestDataNotFoundException(id);
		}
		return myData;
	}

	private BmRestData findOne(long id) {
		for (BmRestData data : repo) {
			if (id == data.getId()) {
				return data;
			}
		}
		return null;
	}

}
