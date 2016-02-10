package seo.dale.spring.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example/exception")
public class ExceptionExampleController {

    @RequestMapping("/runtime")
    public String runtime() {
        throw new RuntimeException("example runtime exceptions");
    }

    @RequestMapping(value = "/methodNotSupported", method = RequestMethod.POST)
    public String methodNotSupported() {
        return "No exception";
    }


}
