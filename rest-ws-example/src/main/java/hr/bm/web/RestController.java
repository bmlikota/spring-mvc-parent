package hr.bm.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.bm.dto.MyData;

@Controller
@RequestMapping("/service/rest")
public class RestController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";

	private List<MyData> repo = new ArrayList<MyData>();

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody MyData spittleById(@PathVariable double id) {
		for (MyData data : repo) {
			if (id == data.getId()) {
				return data;
			}
		}
		return null;
	}

}
