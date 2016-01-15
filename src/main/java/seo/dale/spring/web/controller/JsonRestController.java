package seo.dale.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import seo.dale.spring.domain.User;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
@RestController
@RequestMapping("/json")
public class JsonRestController {

	private static final String BASE_URL = "http://jsonplaceholder.typicode.com";

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getForObjectUsingUriTemplate(@PathVariable Integer id) {
		User res = restTemplate.getForObject(BASE_URL + "/users/{id}", User.class, id);
		System.out.println("# getForObject : " + res);
		return res;
	}

}
