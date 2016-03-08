package seo.dale.spring.core.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import seo.dale.spring.core.security.model.User;

/**
 * @author Dale Seo
 */
@Controller
public class SecurityController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public User login() {
		return new User();
	}

}
