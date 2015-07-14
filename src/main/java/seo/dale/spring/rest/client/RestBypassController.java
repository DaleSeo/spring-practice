package seo.dale.spring.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestBypassController {
	
	@Autowired
	private BypassServiceImpl srvc;

	@RequestMapping("/bypass")
	public ResponseEntity<String> bypass(@RequestHeader("url") String url, HttpMethod method, @RequestBody(required=false) String requestBody) {
		String responseBody = srvc.bypass(url, method, requestBody);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseBody, headers, HttpStatus.OK);
		
		return responseEntity;
	}
	
}
