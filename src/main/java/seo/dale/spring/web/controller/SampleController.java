package seo.dale.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seo.dale.spring.sample.domain.Sample;
import seo.dale.spring.sample.service.SampleService;

import java.util.List;

@RestController
@RequestMapping("/samples")
public class SampleController {

    private SampleService service;

    @Autowired
    public SampleController(SampleService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Sample> find(@RequestParam int count) {
        return service.find(count);
    }

}
