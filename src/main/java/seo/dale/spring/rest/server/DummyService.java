package seo.dale.spring.rest.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class DummyService {

	private static final AtomicInteger COUNTER;
	
	public static Map<Integer, Dummy> map;
	
	static {
		COUNTER = new AtomicInteger();
		map = new HashMap<Integer, Dummy>();
		
		for (Integer id = 0; id < 25;) {
			id = COUNTER.incrementAndGet();
			Dummy dummy = new Dummy(id, "Dummy " + id, new Date());
			map.put(id,  dummy);
		}
	}
	
	public List<Dummy> findAll() {
		return new ArrayList<Dummy>(map.values());
	}
	
	public Dummy findOne(Integer id) {
		return map.get(id);
	}
	
	public void save(Dummy dummy) {
		Integer id = dummy.getId();
		if (id == null || !map.containsKey(id)) {
			id = COUNTER.incrementAndGet();
			dummy.setId(id);
		}
		map.put(id, dummy);
	}

	public void delete(Integer id) {
		map.remove(id);
	}
	
}
