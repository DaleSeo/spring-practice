package seo.dale.spring.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/string")
public class StringController {

    @RequestMapping("/reverse")
    public String reverse(@RequestParam(required = false) String str, @RequestBody(required = false) String body) {
        if (str == null) {
            str = body;
            if (body == null) {
                return "No Input";
            }
        }
        return new StringBuffer(str).reverse().toString();
    }

}
