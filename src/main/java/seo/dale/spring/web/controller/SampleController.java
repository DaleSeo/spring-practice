package seo.dale.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import seo.dale.spring.sample.domain.Sample;
import seo.dale.spring.sample.service.SampleService;

import javax.validation.Valid;
import java.util.Date;
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
    public List<Sample> list(@RequestParam(defaultValue = "5") int count) {
        return service.find(count);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Sample detail(@PathVariable long id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Sample create(@Validated Sample sample, Errors errors) {
        if (errors.hasErrors()) {
            return new Sample(-1, "error", errors.getAllErrors().toString(), new Date());
        }

        sample = service.save(sample);
        return sample;
    }

}
