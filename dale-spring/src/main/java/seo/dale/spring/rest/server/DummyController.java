package seo.dale.spring.rest.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummies")
public class DummyController {
	
	@Autowired
	private DummyService srvc;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Dummy> list() {
		return srvc.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Integer create(@RequestBody Dummy dummy) {
		srvc.save(dummy);
		return dummy.getId();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Dummy detail(@PathVariable Integer id) {
		return srvc.findOne(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public void modify(@PathVariable Integer id, @RequestBody Dummy dummy) {
		dummy.setId(id);
		srvc.save(dummy);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void remove(@PathVariable Integer id) {
		srvc.delete(id);
	}
	
	
	
}