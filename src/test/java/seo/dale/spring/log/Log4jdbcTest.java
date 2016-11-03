package seo.dale.spring.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Log4jdbcConfig.class)
public class Log4jdbcTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void test1() throws Exception {
		int cnt = jdbcTemplate.queryForObject("SELECT COUNT(id) FROM examples", Integer.class);
		System.out.println("# cnt : " + cnt);
	}

	@Test
	public void test2() throws Exception {
		Example example = jdbcTemplate.queryForObject("SELECT * FROM examples WHERE id = ? AND 1 = 1 AND 0 = 0",
				(rs, rowNum) ->
						new Example(rs.getLong("id"), rs.getString("name"), rs.getString("description"), rs.getDate("date")),
				1
		);
		System.out.println("# example : " + example);
	}

}

class Example {

	private long id;
	private String name;
	private String description;
	private Date date;

	public Example() {
	}

	public Example(long id, String name, String description, Date date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Example{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", date=" + date +
				'}';
	}

}
