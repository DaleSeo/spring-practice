package com.example.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/urlEncoded")
	public String urlEncoded(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		return "OK";
	}

}
