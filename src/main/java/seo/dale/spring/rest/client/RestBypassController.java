package seo.dale.spring.rest.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestBypassController {

	@RequestMapping("/bypass")
	public String bypass(@RequestBody String req) {
		String res = req;
		return res;
	}
	
}
